package net.caimito.youtubeagent;

import java.time.LocalDateTime;

import com.rometools.rome.feed.synd.SyndEntry;

public class VideoEntry {

  private String videoId;
  private String channelId;
  private String title;
  private String link;
  private String authorName;
  private String authorUri;
  private LocalDateTime published;
  private LocalDateTime updated;
  private String mediaTitle;
  private String mediaContentUrl;
  private String mediaThumbnailUrl;
  private String mediaDescription;

  public VideoEntry(SyndEntry entry) {
    this.videoId = entry.getUri(); // Assuming videoId is the same as entry URI
    this.channelId = entry.getAuthor(); // Assuming channelId is the same as entry author
    this.title = entry.getTitle();
    this.link = entry.getLink();
    this.authorName = entry.getAuthor();
    this.authorUri = entry.getUri(); // Assuming authorUri is the same as entry URI
    this.published = entry.getPublishedDate() != null
        ? entry.getPublishedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime()
        : null;
    this.updated = entry.getUpdatedDate() != null
        ? entry.getUpdatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime()
        : null;
    this.mediaTitle = entry.getTitle(); // Assuming mediaTitle is the same as entry title
    this.mediaContentUrl = entry.getLink(); // Assuming mediaContentUrl is the same as entry link
    this.mediaThumbnailUrl = ""; // Placeholder, as SyndEntry does not have thumbnail URL
    this.mediaDescription = entry.getDescription() != null ? entry.getDescription().getValue() : null;
  }

  public String getVideoId() {
    return videoId;
  }

  public void setVideoId(String videoId) {
    this.videoId = videoId;
  }

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getAuthorUri() {
    return authorUri;
  }

  public void setAuthorUri(String authorUri) {
    this.authorUri = authorUri;
  }

  public LocalDateTime getPublished() {
    return published;
  }

  public void setPublished(LocalDateTime published) {
    this.published = published;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public String getMediaTitle() {
    return mediaTitle;
  }

  public void setMediaTitle(String mediaTitle) {
    this.mediaTitle = mediaTitle;
  }

  public String getMediaContentUrl() {
    return mediaContentUrl;
  }

  public void setMediaContentUrl(String mediaContentUrl) {
    this.mediaContentUrl = mediaContentUrl;
  }

  public String getMediaThumbnailUrl() {
    return mediaThumbnailUrl;
  }

  public void setMediaThumbnailUrl(String mediaThumbnailUrl) {
    this.mediaThumbnailUrl = mediaThumbnailUrl;
  }

  public String getMediaDescription() {
    return mediaDescription;
  }

  public void setMediaDescription(String mediaDescription) {
    this.mediaDescription = mediaDescription;
  }

}
