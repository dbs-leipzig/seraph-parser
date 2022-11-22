package org.seraph;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.seraph.syntax.ContinuousQuery;
import org.seraph.syntax.SeraphLexer;
import org.seraph.syntax.SeraphParser;
import org.seraph.syntax.SeraphVisitorImpl;

public class Example {
  public static void main(String[] args) {

    String seraphQuery = "REGISTER QUERY x STARTING AT { " +
      "MATCH (a)-[x]->(b) WITHIN PT1H " +
      "EMIT a, x, b " +
      "ON SNAPSHOT " +
      "EVERY PT10M " +
      "}";

    SeraphLexer seraphLexer = new SeraphLexer(CharStreams.fromString(seraphQuery));
    CommonTokenStream tokens = new CommonTokenStream(seraphLexer);
    SeraphParser parser = new SeraphParser(tokens);
    parser.setErrorHandler(new DefaultErrorStrategy());

    SeraphVisitorImpl seraphVisitor = new SeraphVisitorImpl();

    ContinuousQuery query = seraphVisitor.visit(parser.oC_Seraph());

    System.out.println(query);
  }
}
