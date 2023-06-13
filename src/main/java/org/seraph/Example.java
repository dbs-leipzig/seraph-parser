package org.seraph;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.seraph.syntax.Clauses.MatchClause;
import org.seraph.syntax.Clauses.UnwindClause;
import org.seraph.syntax.Clauses.WithClause;
import org.seraph.syntax.ContinuousQuery;
import org.seraph.syntax.SeraphLexer;
import org.seraph.syntax.SeraphParser;
import org.seraph.syntax.SeraphVisitorImpl;

public class Example {
  public static void main(String[] args) {

    String seraphQuery = "REGISTER QUERY <x> STARTING AT 2019-09-07T15:50:30.022+01:00 {" +
            "            MATCH (a) - [b] -> (c) WITHIN P1D WHERE a > b  OR a = 0 OR a = 0 MATCH (b) <- [c] - (a) " +
            "            WITHIN PT2H3M UNWIND expr As A WITH ret WHERE a > b MATCH (c) - [a] -> (b) WITHIN PT25M WHERE a > b RETURN * ON EXIT EVERY PT10M }";

    SeraphLexer seraphLexer = new SeraphLexer(CharStreams.fromString(seraphQuery));
    CommonTokenStream tokens = new CommonTokenStream(seraphLexer);
    SeraphParser parser = new SeraphParser(tokens);
    parser.setErrorHandler(new DefaultErrorStrategy());

    SeraphVisitorImpl seraphVisitor = new SeraphVisitorImpl();

    ContinuousQuery query = seraphVisitor.visit(parser.oC_Seraph());

    System.out.println("-------------QUERY-------------");
    System.out.println(query.getId() + "\n");

    System.out.println("---------STARTING TIME---------");
    System.out.println(query.getStartTime().toString() + "\n");

    System.out.println("-------------MATCH-------------");
    for (MatchClause matchClause : query.getMatchClauses()) {
      System.out.print("Pattern: ");
      System.out.println(matchClause.getPattern());
      System.out.print("    Duration: ");
      System.out.println(matchClause.getDuration() != null ? matchClause.getDuration() : "not defined");
      System.out.print("    Where: ");
      System.out.println(matchClause.getWhereExpression() != null ? matchClause.getWhereExpression() : "not defined");
    }
    System.out.println();

    System.out.println("-------------UNWIND------------");
    for (UnwindClause unwindClause : query.getUnwindClauses()) {
      System.out.print("Expression: ");
      System.out.println(unwindClause.getExpression());
      System.out.print("Alias: ");
      System.out.println(unwindClause.getAlias() != null ? unwindClause.getAlias() : "not defined");
    }
    System.out.println();

    System.out.println("--------------WITH-------------");
    for (WithClause withClause : query.getWithClauses()) {
      System.out.print("Projection: ");
      System.out.println(withClause.getProjection());
      System.out.print("Where: ");
      System.out.println(withClause.getWhereClause() != null ? withClause.getWhereClause() : "not defined");
    }
    System.out.println();

    System.out.println("-------------RETURN------------");
    System.out.println(query.getReturnClause().getProjection() + "\n");

    System.out.println("--------STREAM OPERATOR--------");
    System.out.println(query.getStreamOperator() + "\n");

    System.out.println("------------DURATION-----------");
    System.out.println(query.getDuration() + "\n");


  }
}
