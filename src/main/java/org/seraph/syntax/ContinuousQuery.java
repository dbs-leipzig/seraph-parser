package org.seraph.syntax;

import org.seraph.syntax.Clauses.MatchClause;
import org.seraph.syntax.Clauses.ReturnClause;
import org.seraph.syntax.Clauses.UnwindClause;
import org.seraph.syntax.Clauses.WithClause;

import java.util.ArrayList;
import java.util.Date;

public class ContinuousQuery {

    String id;
    Date startTime;
    ArrayList<MatchClause> matchClauses = new ArrayList<>();
    ArrayList<UnwindClause> unwindClauses = new ArrayList<>();
    ArrayList<WithClause> withClauses = new ArrayList<>();
    ReturnClause returnClause;
    String streamOperator;
    String duration;

    public ContinuousQuery() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getStartTime() {
        return this.startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public ArrayList<MatchClause> getMatchClauses() {
        return matchClauses;
    }
    public void addMatchClause(MatchClause matchClause){
        this.matchClauses.add(matchClause);
    }
    public ArrayList<UnwindClause> getUnwindClauses() {
        return this.unwindClauses;
    }
    public void addUnwindClause(UnwindClause unwindClause) {
        this.unwindClauses.add(unwindClause);
    }
    public ArrayList<WithClause> getWithClauses() {
        return this.withClauses;
    }
    public void addWithClause (WithClause withClause) {
        this.withClauses.add(withClause);
    }
    public ReturnClause getReturnClause() {
        return this.returnClause;
    }
    public void setReturnClause(ReturnClause returnClause) {
        this.returnClause = returnClause;
    }
    public String getStreamOperator() {
        return streamOperator;
    }
    public void setStreamOperator(String streamOperator) {
        this.streamOperator = streamOperator;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
}
