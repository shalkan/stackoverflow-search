package com.example.stackoverflow.client;

import java.net.URI;
import java.util.Map;

import com.example.exception.ApiCallException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * @author gilfanovrenat
 */

@Component
public class StackoverflowHttpClient {

  @Value("${stackoverflow.api.url}")
  private String apiUrl;

  private ObjectMapper objectMapper = new ObjectMapper();

  private static final String SEARCH_METHOD_PATH = "/search";

  private static final String PAGE_PARAM_NAME = "page";
  private static final String PAGE_SIZE_PARAM_NAME = "pageSize";
  private static final String INTITLE_PARAM_NAME = "intitle";
  private static final String ORDER_PARAM_NAME = "order";
  private static final String SORT_PARAM_NAME = "sort";
  private static final String SITE_PARAM_NAME = "site";

  private static final Logger LOGGER = Logger.getLogger(StackoverflowHttpClient.class);


  public Map<String, Object> doSearch(String searchString, Integer pageNumber, Integer pageSize) {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(apiUrl + SEARCH_METHOD_PATH);
      httpGet.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

      URI uri = new URIBuilder(httpGet.getURI())
          .addParameter(PAGE_PARAM_NAME, pageNumber.toString())
          .addParameter(PAGE_SIZE_PARAM_NAME, pageSize.toString())
          .addParameter(INTITLE_PARAM_NAME, searchString)
          //TODO: replace hardcoded values with method params
          .addParameter(ORDER_PARAM_NAME, "desc")
          .addParameter(SORT_PARAM_NAME, "activity")
          .addParameter(SITE_PARAM_NAME, "stackoverflow")
          .build();

      httpGet.setURI(uri);
      CloseableHttpResponse response = httpclient.execute(httpGet);
      return objectMapper.readValue(EntityUtils.toString(response.getEntity()), Map.class);

    }
    catch (Exception e) {
      LOGGER.error(e);
      throw new ApiCallException(e.getMessage());
    }
  }
}
