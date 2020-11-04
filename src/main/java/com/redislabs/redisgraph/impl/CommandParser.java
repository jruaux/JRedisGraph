package com.redislabs.redisgraph.impl;

import java.util.List;

import com.redislabs.redisgraph.Label;
import com.redislabs.redisgraph.Statistics;

import redis.clients.jedis.util.SafeEncoder;

/**
 * Query result statistics interface implementation
 */
public class CommandParser {

    /**
     * A raw representation of query execution statistics is a list of strings (byte arrays which need to be de-serialized).
     * Each string is built in the form of "K:V" where K is statistics label and V is its value.
     * 
     * @param raw a raw representation of the query execution statistics
     */
    public static Statistics statistics(List<byte[]> raw) {
        Statistics statistics = new Statistics();
        for (byte[] tuple : raw) {
            String text = SafeEncoder.encode(tuple);
            String[] rowTuple = text.split(":");
            if (rowTuple.length == 2) {
                Label label = Label.valueOf(rowTuple[0].toUpperCase());
                String string = rowTuple[1].trim();
                switch (label) {
                    case NODES_CREATED:
                        statistics.setNodesCreated(toInt(string));
                        break;
                    case NODES_DELETED:
                        statistics.setNodesDeleted(toInt(string));
                        break;
                    case INDICES_ADDED:
                        statistics.setIndicesAdded(toInt(string));
                        break;
                    case INDICES_DELETED:
                        statistics.setIndicesDeleted(toInt(string));
                        break;
                    case LABELS_ADDED:
                        statistics.setLabelsAdded(toInt(string));
                        break;
                    case RELATIONSHIPS_CREATED:
                        statistics.setRelationshipsCreated(toInt(string));
                        break;
                    case RELATIONSHIPS_DELETED:
                        statistics.setRelationshipsDeleted(toInt(string));
                        break;
                    case PROPERTIES_SET:
                        statistics.setPropertiesSet(toInt(string));
                        break;
                    case CACHED_EXECUTION:
                        statistics.setCachedExecution(toInt(string) == 1);
                        break;
                }
            }
        }
        return statistics;
    }

    private static int toInt(String string) {
        if (string == null) {
            return 0;
        }
        return Integer.parseInt(string.trim());
    }

}
