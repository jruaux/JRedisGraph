package com.redislabs.redisgraph;

import lombok.Data;

@Data
public class Statistics {

    private int nodesCreated;
    private int nodesDeleted;
    private int indicesAdded;
    private int indicesDeleted;
    private int labelsAdded;
    private int relationshipsDeleted;
    private int relationshipsCreated;
    private int propertiesSet;
    private boolean cachedExecution;

}
