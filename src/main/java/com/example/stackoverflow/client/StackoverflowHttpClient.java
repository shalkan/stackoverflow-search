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

  public ItemList doSearch(String searchString, Integer pageSize, Integer pageNumber) {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(apiUrl + "/search");
    httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

    //value for test
    //{"items":[{"tags":["java","maven","soapui"],"owner":{"reputation":29,"user_id":5969683,"user_type":"registered","accept_rate":62,"profile_image":"https://www.gravatar.com/avatar/1ad78ac2414201da3a8b32381e7a6453?s=128&d=identicon&r=PG&f=1","display_name":"Happy","link":"https://stackoverflow.com/users/5969683/happy"},"is_answered":true,"view_count":968,"accepted_answer_id":35745868,"answer_count":3,"score":1,"last_activity_date":1456917960,"creation_date":1456827804,"last_edit_date":1456828054,"question_id":35720329,"link":"https://stackoverflow.com/questions/35720329/error-soapui-an-error-occurred-no-suitable-driver-found-for-jdbcoraclethin","title":"ERROR [SoapUI] An error occurred [No suitable driver found for jdbc:oracle:thin:@//174.23.0.187:1111/qwe]"}],"has_more":true,"quota_max":300,"quota_remaining":270}
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
      Map<String, Object> jsonMap = mapper.readValue(resEntityToString, Map.class);
      return mapItemsList(jsonMap);

    }
    catch (URISyntaxException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private Question mapQuestion(Map<String, Object> parsedObject) {
    Question question = new Question();
//    question.setAuthor();
//    question.setTitle();
    question.setPostDate(OffsetDateTime.ofInstant(
        Instant.ofEpochMilli(Long.valueOf(parsedObject.get("creation_date").toString())),
        ZoneId.systemDefault()));
    question.setIsAnswered(Boolean.valueOf(parsedObject.get("is_answered").toString()));
    return question;
  }

  private ItemList mapItemsList(Map<String, Object> parsedObject) {
    ItemList itemList = new ItemList();
    itemList.setTotalItems(Integer.parseInt(parsedObject.get("quota_remaining").toString()));
    itemList.setItems(((Collection<Map<String, Object>>) parsedObject.get("items")).stream()
        .map(this::mapQuestion)
        .collect(
            Collectors.toList()));
    return itemList;
  }
}
