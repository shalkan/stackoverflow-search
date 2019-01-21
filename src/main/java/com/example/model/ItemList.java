package com.example.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * ItemList
 */
@Validated
@javax.annotation.Generated(value = "com.example.codegen.v3.generators.java.SpringCodegen", date = "2019-01-21T14:54:46.412Z[GMT]")
public class ItemList   {
  @JsonProperty("totalItems")
  private Integer totalItems = null;

  @JsonProperty("items")
  @Valid
  private List<Question> items = null;

  public ItemList totalItems(Integer totalItems) {
    this.totalItems = totalItems;
    return this;
  }

  /**
   * Get totalItems
   * @return totalItems
  **/
  @ApiModelProperty(example = "758", value = "")

  public Integer getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(Integer totalItems) {
    this.totalItems = totalItems;
  }

  public ItemList items(List<Question> items) {
    this.items = items;
    return this;
  }

  public ItemList addItemsItem(Question itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<Question>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  **/
  @ApiModelProperty(value = "")
  @Valid
  public List<Question> getItems() {
    return items;
  }

  public void setItems(List<Question> items) {
    this.items = items;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemList itemList = (ItemList) o;
    return Objects.equals(this.totalItems, itemList.totalItems) &&
        Objects.equals(this.items, itemList.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalItems, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemList {\n");
    
    sb.append("    totalItems: ").append(toIndentedString(totalItems)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
