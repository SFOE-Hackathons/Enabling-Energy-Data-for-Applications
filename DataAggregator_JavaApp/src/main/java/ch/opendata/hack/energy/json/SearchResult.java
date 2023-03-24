package ch.opendata.hack.energy.json;

import ch.opendata.hack.energy.model.DatabaseObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Search result.
 */
public class SearchResult {

    private final Map<String, Object> metaData = new HashMap<>();

    private final List<JsonObject> data = new ArrayList<>();

    private SearchResult() {
    }

    /**
     * Create search result.
     *
     * @param databaseObjectsList the database objects list
     * @return the search result
     */
    public static SearchResult create(final List<DatabaseObject> databaseObjectsList) {
        final SearchResult ret = new SearchResult();
        ret.data.addAll(databaseObjectsList.stream().map(databaseObject -> new JsonObject(databaseObject)).toList());
        ret.metaData.put("size", ret.data.size());
        return ret;
    }
}
