package org.seraph.syntax;

public class R2R {

    String query;

    public R2R(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
