package org.seraph.syntax;

import org.seraph.syntax.Clauses.MatchClause;
import org.seraph.syntax.Clauses.ReturnClause;
import org.seraph.syntax.Clauses.UnwindClause;
import org.seraph.syntax.Clauses.WithClause;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SeraphVisitorImpl extends SeraphBaseVisitor<ContinuousQuery> {

    ContinuousQuery query = new ContinuousQuery();
    @Override
    public ContinuousQuery visitOC_Seraph(SeraphParser.OC_SeraphContext ctx) {
        query.setId(ctx.id.getText());
        query.setStartTime(getISO8601AsDate(ctx.ISO8601_DATE_TIME().getText()));
        query.setDuration(ctx.ISO8601_DURATION().getText());
        visitOC_Statement(ctx.oC_Statement());
        visitOS_StreamOp(ctx.oS_StreamOp());
        return query;
    }
    public Date getISO8601AsDate(String dateString) {
        Calendar dateTimeAsCalendar = DatatypeConverter.parseDateTime(dateString);
        return dateTimeAsCalendar.getTime();
    }
    @Override
    public ContinuousQuery visitOC_Statement(SeraphParser.OC_StatementContext ctx) {

        if(ctx.oC_Query().oC_RegularQuery().oC_SingleQuery().oC_MultiPartQuery() != null) {
            visitOC_MultiPartQuery(ctx.oC_Query().oC_RegularQuery().oC_SingleQuery().oC_MultiPartQuery());
        }
        else if (ctx.oC_Query().oC_RegularQuery().oC_SingleQuery().oC_SinglePartQuery() != null) {
            visitOC_SinglePartQuery(ctx.oC_Query().oC_RegularQuery().oC_SingleQuery().oC_SinglePartQuery());
        }

        return query;
    }
    @Override
    public ContinuousQuery visitOC_SinglePartQuery(SeraphParser.OC_SinglePartQueryContext ctx) {
        List<SeraphParser.OC_ReadingClauseContext> readingClauses = ctx.oC_ReadingClause();
        if (readingClauses.size() > 0) {
            for (SeraphParser.OC_ReadingClauseContext readingClause : readingClauses) {
                visitOC_ReadingClause(readingClause);
            }
        }
        if (ctx.oC_Return() != null) {
            visitOC_Return(ctx.oC_Return());
        }
        return query;
    }

    @Override
    public ContinuousQuery visitOC_MultiPartQuery(SeraphParser.OC_MultiPartQueryContext ctx) {
        //first check if SinglePartQuery then check if Readingclause immediately
        if (ctx.oC_SinglePartQuery() != null) {
            visitOC_SinglePartQuery(ctx.oC_SinglePartQuery());
        }
        if (ctx.oC_ReadingClause().size() > 0) {
            List<SeraphParser.OC_ReadingClauseContext> readingClauses = ctx.oC_ReadingClause();
            for (SeraphParser.OC_ReadingClauseContext readingClause : readingClauses) {
                visitOC_ReadingClause(readingClause);
            }
        }
        if (ctx.oC_With().size() > 0) {
            List<SeraphParser.OC_WithContext> withClauses = ctx.oC_With();
            for (SeraphParser.OC_WithContext withClause : withClauses) {
                visitOC_With(withClause);
            }
        }


        return query;
    }
    @Override
    public ContinuousQuery visitOC_Match(SeraphParser.OC_MatchContext ctx) {
        MatchClause matchClause;
        String duration = ctx.ISO8601_DURATION().getText();
        String pattern = "";
        List<SeraphParser.OC_PatternPartContext> patternPartContexts = ctx.oC_Pattern()
                .oC_PatternPart();
        for (SeraphParser.OC_PatternPartContext patternPartContext : patternPartContexts) {
            pattern = patternPartContext.oC_AnonymousPatternPart().oC_PatternElement().getText();
        }
        if (ctx.oC_Where() != null) {
            String whereClause = ctx.oC_Where().oC_Expression().getText();
            matchClause = new MatchClause(pattern, duration, whereClause);
        }
        else {
            matchClause = new MatchClause(pattern, duration);
        }
        query.addMatchClause(matchClause);
        return query;
    }
    @Override
    public ContinuousQuery visitOC_Unwind(SeraphParser.OC_UnwindContext ctx) {
        String expression = ctx.oC_Expression().getText();
        String alias = ctx.oC_Variable().getText();
        UnwindClause unwindClause = new UnwindClause(expression, alias);
        query.addUnwindClause(unwindClause);
        return query;
    }
    @Override
    public ContinuousQuery visitOC_With(SeraphParser.OC_WithContext ctx) {
        WithClause withClause;
        String projection = ctx.oC_ProjectionBody().getText();
        if (ctx.oC_Where() != null) {
            String whereClause = ctx.oC_Where().oC_Expression().getText();
            withClause = new WithClause(projection, whereClause);
        }
        else {
            withClause = new WithClause(projection);
        }
        query.addWithClause(withClause);
        return query;
    }

    @Override
    public ContinuousQuery visitOC_ReadingClause(SeraphParser.OC_ReadingClauseContext ctx) {
        if (ctx.oC_Match() != null) {
            visitOC_Match(ctx.oC_Match());
        }
        else if (ctx.oC_Unwind() != null) {
            visitOC_Unwind(ctx.oC_Unwind());
        }
        return query;
    }

    @Override
    public ContinuousQuery visitOC_Return(SeraphParser.OC_ReturnContext ctx) {
        String projection = ctx.oC_ProjectionBody().getText();
        ReturnClause returnClause = new ReturnClause(projection);
        query.setReturnClause(returnClause);
        return query;
    }

    @Override
    public ContinuousQuery visitOS_StreamOp(SeraphParser.OS_StreamOpContext ctx) {
        if (ctx.ON() != null) {
            if (ctx.EXIT() != null) {
                query.setStreamOperator("ON EXIT");
            }
            else if (ctx.ENTERING() != null) {
                query.setStreamOperator("ON ENTERING");
            }
        }
        else if (ctx.SNAPSHOT() != null) {
            query.setStreamOperator("SNAPSHOT");
        }
        return query;
    }
}
