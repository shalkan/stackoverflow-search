package com.example.controller;

import javax.validation.constraints.NotNull;

import com.example.model.ItemList;
import com.example.stackoverflow.service.QueryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gilfanovrenat
 */

@RestController
@RequestMapping("/search")
public class SearchController {

  @Autowired
  private QueryService queryService;

  private static final Logger LOGGER = Logger.getLogger(QueryService.class);

  @GetMapping
  public ItemList doSearch(
      @NotNull @RequestParam(value = "searchString") String searchString,
      @NotNull @RequestParam(value = "pageNumber") Integer pageNumber,
      @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
    if (StringUtils.isEmpty(searchString) || pageNumber < 1 || pageSize < 1) {
      LOGGER.error(String.format("Invalid params searchString: %s, pageNumber: %d, PageSize: %d",
          searchString, pageNumber, pageSize));
      throw new IllegalArgumentException("Invalid params");
    }
    return queryService.doSearch(searchString, pageNumber, pageSize);
  }
}
