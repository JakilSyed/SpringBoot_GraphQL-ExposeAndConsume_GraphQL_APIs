package com.jakil.graphql_demo.model;

public class GraphQLResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private Player findOne;

        public Player getFindOne() {
            return findOne;
        }

        public void setFindOne(Player findOne) {
            this.findOne = findOne;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "findOne=" + findOne +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GraphQLResponse{" +
                "data=" + data +
                '}';
    }
}

