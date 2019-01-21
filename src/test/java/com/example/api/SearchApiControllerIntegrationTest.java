package com.example.api;

import com.example.model.ItemList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApiControllerIntegrationTest {

    @Autowired
    private SearchApi api;

    @Test
    public void searchGetTest() throws Exception {
        String searchString = "searchString_example";
        Integer pageNumber = 56;
        Integer pageSize = 56;
        ResponseEntity<ItemList> responseEntity = api.searchGet(searchString, pageNumber, pageSize);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
