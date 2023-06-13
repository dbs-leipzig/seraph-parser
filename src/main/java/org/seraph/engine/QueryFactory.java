package org.seraph.engine;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.seraph.syntax.ContinuousQuery;
import org.seraph.syntax.SeraphLexer;
import org.seraph.syntax.SeraphParser;
import org.seraph.syntax.SeraphVisitorImpl;

public interface QueryFactory {
    static ContinuousQuery parse(String queryString) {
        SeraphLexer seraphLexer = new SeraphLexer(CharStreams.fromString(queryString));
        CommonTokenStream tokens = new CommonTokenStream(seraphLexer);
        SeraphParser parser = new SeraphParser(tokens);
        parser.setErrorHandler(new DefaultErrorStrategy());
        SeraphVisitorImpl seraphVisitor = new SeraphVisitorImpl();

        return seraphVisitor.visit(parser.oC_Seraph());
    }
}
