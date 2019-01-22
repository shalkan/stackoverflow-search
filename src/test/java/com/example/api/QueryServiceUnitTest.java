package com.example.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import com.example.model.ItemList;
import com.example.model.Question;
import com.example.stackoverflow.client.StackoverflowHttpClient;
import com.example.stackoverflow.service.QueryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author gilfanovrenat
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryServiceUnitTest {

  @Autowired
  private QueryService queryService;

  @Test
  public void queryServiceTest() {
    StackoverflowHttpClient stackoverflowHttpClient = mock(StackoverflowHttpClient.class);

    Map<String, Object> parsedObject = null;
    try {
      ObjectMapper mapper = new ObjectMapper();
      parsedObject = mapper.readValue("{\"items\":[{\"tags\":[\"java\",\"maven\",\"soapui\"],\"owner\":{\"reputation\":29,\"user_id\":5969683,\"user_type\":\"registered\",\"accept_rate\":62,\"profile_image\":\"https://www.gravatar.com/avatar/1ad78ac2414201da3a8b32381e7a6453?s=128&d=identicon&r=PG&f=1\",\"display_name\":\"Happy\",\"link\":\"https://stackoverflow.com/users/5969683/happy\"},\"is_answered\":true,\"view_count\":968,\"accepted_answer_id\":35745868,\"answer_count\":3,\"score\":1,\"last_activity_date\":1456917960,\"creation_date\":1456827804,\"last_edit_date\":1456828054,\"question_id\":35720329,\"link\":\"https://stackoverflow.com/questions/35720329/error-soapui-an-error-occurred-no-suitable-driver-found-for-jdbcoraclethin\",\"title\":\"ERROR [SoapUI] An error occurred [No suitable driver found for jdbc:oracle:thin:@//174.23.0.187:1111/qwe]\"}],\"has_more\":true,\"quota_max\":300,\"quota_remaining\":270}", Map.class);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    when(stackoverflowHttpClient.doSearch("testString", 1, 10)).thenReturn(parsedObject);

    queryService.setStackoverflowHttpClient(stackoverflowHttpClient);

    ItemList itemList = queryService.doSearch("testString", 1, 10);
    Assert.assertNotNull(itemList);
    Assert.assertTrue(itemList.getHasMore());
    Assert.assertTrue(itemList.getItems().size() == 1);
    Question question = itemList.getItems().get(0);
    Assert.assertEquals(question.getTitle(), "ERROR [SoapUI] An error occurred [No suitable driver found for jdbc:oracle:thin:@//174.23.0.187:1111/qwe]");
    Assert.assertEquals(question.getAuthor(), "Happy");
    Assert.assertEquals(question.getPostDate(), new Timestamp(1456827804L));
    Assert.assertTrue(question.isIsAnswered());
    Assert.assertEquals(question.getOriginalLink(), "https://stackoverflow.com/questions/35720329/error-soapui-an-error-occurred-no-suitable-driver-found-for-jdbcoraclethin");
  }
}
