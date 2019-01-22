package com.example.stackoverflow.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.model.ItemList;
import com.example.model.Question;
import com.example.stackoverflow.client.StackoverflowHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;

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

  public ItemList doSearch(String searcgString, Integer pageNumber, Integer pageSize) {
    return mapItemsList(stackoverflowHttpClient.doSearch(searcgString, pageNumber, pageSize));
  }

  private Question mapQuestion(Map<String, Object> parsedObject) {
    Question question = new Question();
    Map<String, Object> owner = (Map<String, Object>) parsedObject.get("owner");
    if (owner != null) {
      question.setAuthor(owner.get("display_name").toString());
    }
    question.setTitle(parsedObject.get("title").toString());
    question.setPostDate(new Timestamp(Long.valueOf(parsedObject.get("creation_date").toString()) * 1000));
    question.setIsAnswered(Boolean.valueOf(parsedObject.get("is_answered").toString()));
    question.setOriginalLink(parsedObject.get("link").toString());
    return question;
  }

  private ItemList mapItemsList(Map<String, Object> parsedObject) {
    ItemList itemList = new ItemList();
    itemList.setHasMore(Boolean.valueOf(parsedObject.get("has_more").toString()));
    itemList.setItems(((Collection<Map<String, Object>>) parsedObject.get("items")).stream()
        .map(this::mapQuestion)
        .collect(
            Collectors.toList()));
    return itemList;
  }
}
