package de.iplytics.codingchallenge_backend_webapp.search;

import de.iplytics.codingchallenge_backend_webapp.patents.PatentSearchProvider;
import de.iplytics.codingchallenge_backend_webapp.standards.StandardsSearchProvider;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;


import static de.iplytics.codingchallenge_backend_webapp.constants.Constants.PATENTS_CORE;
import static de.iplytics.codingchallenge_backend_webapp.constants.Constants.STANDARDS_CORE;

// TODO refactor to spring managed bean
public abstract class SearchProvider {

    public static SearchProvider create(String core) {
        if (PATENTS_CORE.equals(core))
                return new PatentSearchProvider();
        else if (STANDARDS_CORE.equals(core))
                return new StandardsSearchProvider();
        else
            throw new IllegalArgumentException("Core " + core + " is not supported");
    }

    public abstract QueryResponse search(JsonQueryRequest jsonQueryRequest);

}
