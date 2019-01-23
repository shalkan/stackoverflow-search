package com.example.api;

import java.sql.Timestamp;
import java.util.Collections;

import com.example.controller.SearchController;
import com.example.model.ItemList;

import com.example.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class SearchApiControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchController searchController;

    @Test
    public void doSearchTest() throws Exception {

      String searchString = "ERROR";
      String title = "ERROR [SoapUI] An error occurred [No suitable driver found for jdbc:oracle:thin:@//174.23.0.187:1111/qwe]";
      String author = "Happy";
      Timestamp postDate = new Timestamp(1456827804000L);
      String link = "https://stackoverflow.com/questions/35720329/error-soapui-an-error-occurred-no-suitable-driver-found-for-jdbcoraclethin";

      Question question = new Question();
      question.setTitle(title);
      question.setAuthor(author);
      question.setPostDate(postDate);
      question.setIsAnswered(true);
      question.setOriginalLink(link);

      ItemList itemList = new ItemList();
      itemList.setHasMore(true);
      itemList.setItems(Collections.singletonList(question));


      given(searchController.doSearch(searchString, 1, 10)).willReturn(
          itemList);

      mockMvc.perform(get("/search")
          .param("searchString", searchString)
          .param("pageNumber", "1")
          .param("pageSize", "10")
          .contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.hasMore", is(true)))
          .andExpect(jsonPath("$.items", hasSize(1)))
          .andExpect(jsonPath("$.items[0].title", is(title)))
          .andExpect(jsonPath("$.items[0].author", is(author)))
          .andExpect(jsonPath("$.items[0].postDate", is(postDate.getTime())))
          .andExpect(jsonPath("$.items[0].isAnswered", is(true)))
          .andExpect(jsonPath("$.items[0].originalLink", is(link)));
    }

    @Test
    public void invalidParamsTest() throws Exception {
      String searchString = "ERROR";

      mockMvc.perform(get("/search")
          .contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().is4xxClientError());

      mockMvc.perform(get("/search")
          .param("searchString", searchString)
          .param("pageNumber", "1")
          .contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().is4xxClientError());

      mockMvc.perform(get("/search")
          .param("searchString", searchString)
          .param("pageSize", "10")
          .contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().is4xxClientError());

      mockMvc.perform(get("/search")
          .param("pageNumber", "1")
          .param("pageSize", "10")
          .contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().is4xxClientError());

      mockMvc.perform(get("/search")
          .param("searchString", searchString)
          .param("pageNumber", "1")
          .param("pageSize", "10")
          .contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk());
    }
}
