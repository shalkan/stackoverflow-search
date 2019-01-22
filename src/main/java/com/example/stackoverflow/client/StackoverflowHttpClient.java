package com.example.stackoverflow.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.model.ItemList;
import com.example.model.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;

/**
 * @author gilfanovrenat
 */

@Component
public class StackoverflowHttpClient {

  @Value("${stackoverflow.api.url}")
  private String apiUrl;

  public Map<String, Object> doSearch(String searchString, Integer pageNumber, Integer pageSize) {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(apiUrl + "/search");
    httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

    try {
      URI uri = new URIBuilder(httpGet.getURI())
          .addParameter("page", pageNumber.toString())
          .addParameter("pageSize", pageSize.toString())
          .addParameter("intitle", searchString)
          .addParameter("order", "desc")
          .addParameter("sort", "activity")
          .addParameter("site", "stackoverflow")
          .build();

      httpGet.setURI(uri);
      CloseableHttpResponse response = httpclient.execute(httpGet);
      String resEntityToString = EntityUtils.toString(response.getEntity());
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(resEntityToString, Map.class);

    }
    catch (URISyntaxException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
