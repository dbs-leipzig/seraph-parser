package org.seraph.syntax.Clauses;

public class UnwindClause {
    private String expression;
    private String alias;

    public UnwindClause(String expression, String alias) {
        this.expression = expression;
        this.alias = alias;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
