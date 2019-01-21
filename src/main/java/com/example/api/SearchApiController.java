package com.example.api;

import com.example.model.ItemList;
import com.example.stackoverflow.client.StackoverflowHttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "com.example.codegen.v3.generators.java.SpringCodegen", date = "2019-01-21T14:54:46.412Z[GMT]")
@Controller
public class SearchApiController implements SearchApi {

    @Autowired
    private StackoverflowHttpClient stackoverflowHttpClient;

    private static final Logger log = LoggerFactory.getLogger(SearchApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SearchApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ItemList> searchGet(@NotNull @ApiParam(value = "string for search query", required = true) @Valid @RequestParam(value = "searchString", required = true) String searchString, @NotNull @ApiParam(value = "number of page", required = true) @Valid @RequestParam(value = "pageNumber", required = true) Integer pageNumber, @NotNull @ApiParam(value = "size of page to return", required = true) @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<ItemList>(stackoverflowHttpClient.doSearch(searchString, pageSize, pageNumber), HttpStatus.OK);
    }

}
