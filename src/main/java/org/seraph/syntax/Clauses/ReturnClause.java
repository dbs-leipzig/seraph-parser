package org.seraph.syntax.Clauses;

public class ReturnClause {
    private String projection;

    public ReturnClause(String projection) {
        this.projection = projection;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }
}
