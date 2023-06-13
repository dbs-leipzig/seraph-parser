package org.seraph.syntax.Clauses;

public class WithClause {
    private String projection;
    private String whereClause;

    public WithClause(String projection) {
        this.projection = projection;
    }

    public WithClause(String projection, String whereClause) {
        this.projection = projection;
        this.whereClause = whereClause;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }
}
