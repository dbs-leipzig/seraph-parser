package org.seraph.syntax.Clauses;

import java.util.ArrayList;

public class MatchClause {

    private String duration;
    private String pattern;
    private String whereExpression;

    public MatchClause (String pattern, String duration) {
        this.pattern = pattern;
        this.duration = duration;
    }

    public MatchClause (String pattern, String duration, String whereExpression) {
        this.pattern = pattern;
        this.duration = duration;
        this.whereExpression = whereExpression;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getWhereExpression() {
        return whereExpression;
    }

    public void setWhereExpression(String whereExpression) {
        this.whereExpression = whereExpression;
    }
}
