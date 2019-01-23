package com.example.stackoverflow.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.exception.InvalidApiResponseException;
import com.example.model.ItemList;
import com.example.model.Question;
import com.example.stackoverflow.client.StackoverflowHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gilfanovrenat
 */

@Service
public class QueryService {

  private StackoverflowHttpClient stackoverflowHttpClient;

  @Autowired
  public void setStackoverflowHttpClient(
      StackoverflowHttpClient stackoverflowHttpClient) {
    this.stackoverflowHttpClient = stackoverflowHttpClient;
  }

  private static final String HAS_MORE_FIELD = "has_more";
  private static final String ITEMS_FIELD = "items";
  private static final String OWNER_FIELD = "owner";
  private static final String DISPLAY_NAME_FIELD = "display_name";
  private static final String TITLE_FIELD = "title";
  private static final String CREATION_DATE_FIELD = "creation_date";
  private static final String IS_ANSWERED_FIELD = "is_answered";
  private static final String LINK_FIELD = "link";

  private static final Long ONE_SECOND_MS = 1000L;

  private static final Logger LOGGER = Logger.getLogger(QueryService.class);

  public ItemList doSearch(String searcgString, Integer pageNumber, Integer pageSize) {
    try {
      return mapItemsList(stackoverflowHttpClient.doSearch(searcgString, pageNumber, pageSize));
    }
    catch (Exception e) {
      LOGGER.error(e);
      throw new InvalidApiResponseException(e.getMessage());
    }
  }

  private ItemList mapItemsList(Map<String, Object> parsedObject) {
    ItemList itemList = new ItemList();
    itemList.setHasMore(Boolean.valueOf(parsedObject.get(HAS_MORE_FIELD).toString()));
    itemList.setItems(((Collection<Map<String, Object>>) parsedObject.get(ITEMS_FIELD)).stream()
        .map(this::mapQuestion)
        .collect(Collectors.toList()));
    return itemList;
  }

  private Question mapQuestion(Map<String, Object> parsedObject) {
    Question question = new Question();
    Map<String, Object> owner = (Map<String, Object>) parsedObject.get(OWNER_FIELD);
    if (owner != null) {
      question.setAuthor(owner.get(DISPLAY_NAME_FIELD).toString());
    }
    question.setTitle(parsedObject.get(TITLE_FIELD).toString());
    question.setPostDate(
        new Timestamp(Long.valueOf(parsedObject.get(CREATION_DATE_FIELD).toString()) * ONE_SECOND_MS));
    question.setIsAnswered(Boolean.valueOf(parsedObject.get(IS_ANSWERED_FIELD).toString()));
    question.setOriginalLink(parsedObject.get(LINK_FIELD).toString());
    return question;
  }
}
