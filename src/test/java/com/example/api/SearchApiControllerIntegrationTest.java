package com.example.api;

import com.example.model.ItemList;

import com.example.model.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApiControllerIntegrationTest {

    @Autowired
    private SearchApi api;

    @Test
    public void searchGetTest() throws Exception {
        String searchString = "code";
        Integer pageNumber = 56;
        Integer pageSize = 56;
        ResponseEntity<ItemList> responseEntity = api.searchGet(searchString, pageNumber, pageSize);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ItemList itemList = responseEntity.getBody();
        Assert.assertNotNull(itemList.getHasMore());
        Assert.assertTrue(!CollectionUtils.isEmpty(itemList.getItems()));
        Question question = itemList.getItems().get(0);
        Assert.assertNotNull(question.getOriginalLink());
        Assert.assertNotNull(question.getPostDate());
        Assert.assertNotNull(question.getAuthor());
        Assert.assertNotNull(question.getTitle());
        Assert.assertNotNull(question.isIsAnswered());
    }

}
