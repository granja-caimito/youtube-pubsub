package net.caimito.youtubeagent;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "posts")
public class PostEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String link;
  private MediaTarget mediaTarget;
  private LocalDateTime publishedDate;

  @SuppressWarnings("unused")
  private PostEntity() {
  }

  public PostEntity(String link, MediaTarget mediaTarget, LocalDateTime publishedDate) {
    this.link = link;
    this.mediaTarget = mediaTarget;
    this.publishedDate = publishedDate;
  }

  public Long getId() {
    return id;
  }

  public String getLink() {
    return link;
  }

  public MediaTarget getMediaTarget() {
    return mediaTarget;
  }

  public LocalDateTime getPublishedDate() {
    return publishedDate;
  }

}
