package de.iplytics.codingchallenge_backend_webapp.standards;

import de.iplytics.codingchallenge_backend_webapp.config.SolrClientConfig;
import de.iplytics.codingchallenge_backend_webapp.constants.Constants;
import de.iplytics.codingchallenge_backend_webapp.search.SearchProvider;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;

public class StandardsSearchProvider extends SearchProvider {

    // TODO convert to spring bean instead of using the new keyword
    private SolrClient solrClient = new SolrClientConfig().solrClient();

    public QueryResponse search(JsonQueryRequest jsonQueryRequest) {
        try{
            return jsonQueryRequest.process(solrClient, Constants.STANDARDS_CORE);
        }
        catch (IOException | SolrServerException exception){
            // TODO handle exception
        }
        return new QueryResponse();
    }

}
