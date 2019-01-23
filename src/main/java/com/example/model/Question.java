package com.example.model;

import java.sql.Timestamp;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Question
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-01-22T13:22:50.616Z[GMT]")
public class Question   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("postDate")
  private Timestamp postDate = null;

  @JsonProperty("isAnswered")
  private Boolean isAnswered = null;

  @JsonProperty("originalLink")
  private String originalLink = null;

  public Question title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/

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
   * @return author
   **/

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Question postDate(Timestamp postDate) {
    this.postDate = postDate;
    return this;
  }

  /**
   * Get postDate
   * @return postDate
   **/

  @Valid
  public Timestamp getPostDate() {
    return postDate;
  }

  public void setPostDate(Timestamp postDate) {
    this.postDate = postDate;
  }

  public Question isAnswered(Boolean isAnswered) {
    this.isAnswered = isAnswered;
    return this;
  }

  /**
   * Get isAnswered
   * @return isAnswered
   **/

  public Boolean isIsAnswered() {
    return isAnswered;
  }

  public void setIsAnswered(Boolean isAnswered) {
    this.isAnswered = isAnswered;
  }

  public Question originalLink(String originalLink) {
    this.originalLink = originalLink;
    return this;
  }

  /**
   * Get originalLink
   * @return originalLink
   **/

  public String getOriginalLink() {
    return originalLink;
  }

  public void setOriginalLink(String originalLink) {
    this.originalLink = originalLink;
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
           Objects.equals(this.isAnswered, question.isAnswered) &&
           Objects.equals(this.originalLink, question.originalLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, postDate, isAnswered, originalLink);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Question {\n");

    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    postDate: ").append(toIndentedString(postDate)).append("\n");
    sb.append("    isAnswered: ").append(toIndentedString(isAnswered)).append("\n");
    sb.append("    originalLink: ").append(toIndentedString(originalLink)).append("\n");
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
