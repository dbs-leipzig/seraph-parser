package org.seraph;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.seraph.syntax.Clauses.MatchClause;
import org.seraph.syntax.Clauses.ReturnClause;
import org.seraph.syntax.Clauses.UnwindClause;
import org.seraph.syntax.Clauses.WithClause;
import org.seraph.syntax.ContinuousQuery;
import org.seraph.syntax.SeraphLexer;
import org.seraph.syntax.SeraphParser;
import org.seraph.syntax.SeraphVisitorImpl;

public class Example {
  public static void main(String[] args) {

    String seraphQuery = "REGISTER QUERY <x> STARTING AT 2019-09-07T15:50:30.022+01:00 {" +
            "            MATCH (a) --> (b) --> (c) WITHIN PT1H WHERE a > b  OR a = 0 OR a = 0 MATCH (a) --> (b) --> (c) " +
            "            WITHIN PT1H UNWIND expr As A WITH ret WHERE a > b MATCH (a) --> (b) --> (c) WITHIN PT1H WHERE a > b RETURN * ON EXIT EVERY PT10M }";

    SeraphLexer seraphLexer = new SeraphLexer(CharStreams.fromString(seraphQuery));
    CommonTokenStream tokens = new CommonTokenStream(seraphLexer);
    SeraphParser parser = new SeraphParser(tokens);
    parser.setErrorHandler(new DefaultErrorStrategy());

    SeraphVisitorImpl seraphVisitor = new SeraphVisitorImpl();

    ContinuousQuery query = seraphVisitor.visit(parser.oC_Seraph());

    System.out.println("MATCH---------");
    for (MatchClause matchClause : query.getMatchClauses()) {
      System.out.println(matchClause);
    }

    System.out.println("UNWIND---------");
    for (UnwindClause unwindClause : query.getUnwindClauses()) {
      System.out.println(unwindClause);
    }

    System.out.println("WITH-----------");
    for (WithClause withClause : query.getWithClauses()) {
      System.out.println(withClause);
    }

    System.out.println("RETURN----------");
    System.out.println(query.getReturnClause());

    System.out.println("STREAM OPERATOR ---");
    System.out.println(query.getStreamOperator());

    System.out.println("DURATION----------");
    System.out.println(query.getDuration());

    System.out.println("TIME--------------");
    System.out.println(query.getStartTime().toString());

  }
}
