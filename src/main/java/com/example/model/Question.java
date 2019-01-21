package com.example.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Question
 */
@Validated
@javax.annotation.Generated(value = "com.example.codegen.v3.generators.java.SpringCodegen",
                            date = "2019-01-21T14:54:46.412Z[GMT]")
public class Question {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("postDate")
  private OffsetDateTime postDate = null;

  @JsonProperty("isAnswered")
  private Boolean isAnswered = null;

  public Question title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   *
   * @return title
   **/
  @ApiModelProperty(example = "How to run simple java programm?", value = "")

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Question author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   *
   * @return author
   **/
  @ApiModelProperty(example = "newbie", value = "")

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Question postDate(OffsetDateTime postDate) {
    this.postDate = postDate;
    return this;
  }

  /**
   * Get postDate
   *
   * @return postDate
   **/
  @ApiModelProperty(value = "")

  @Valid
  public OffsetDateTime getPostDate() {
    return postDate;
  }

  public void setPostDate(OffsetDateTime postDate) {
    this.postDate = postDate;
  }

  /**
   * Get isAnswered
   *
   * @return isAnswered
   **/
  @ApiModelProperty(value = "")

  @Valid
  public Boolean getIsAnswered() {
    return isAnswered;
  }

  public void setIsAnswered(Boolean isAnswered) {
    this.isAnswered = isAnswered;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question question = (Question) o;
    return Objects.equals(this.title, question.title) &&
           Objects.equals(this.author, question.author) &&
           Objects.equals(this.postDate, question.postDate) &&
           Objects.equals(this.isAnswered, question.isAnswered);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, postDate, isAnswered);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Question {\n");

    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    postDate: ").append(toIndentedString(postDate)).append("\n");
    sb.append("    isAnswered: ").append(toIndentedString(isAnswered)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
