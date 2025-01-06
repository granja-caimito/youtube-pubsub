package net.caimito.youtubeagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.rometools.rome.feed.synd.SyndFeed;

public class AbstractFeedWatcher {
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFeedWatcher.class);

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  protected void doProcessFeed(SyndFeed feed) {
    VideoEntry entry = findNewestEntry(feed);
    NewFeedEntryEvent event = new NewFeedEntryEvent(entry);
    applicationEventPublisher.publishEvent(event);
  }

  protected VideoEntry findNewestEntry(SyndFeed feed) {
    try {
      return feed.getEntries().stream()
          .map(entry -> new VideoEntry(entry))
          .max((entry1, entry2) -> entry1.getPublished().compareTo(entry2.getPublished()))
          .map(entry -> {
            return entry;
          })
          .orElseThrow(() -> new RuntimeException("No entries found"));
    } catch (Exception e) {
      LOGGER.error("Error finding newest entry", e);
      throw new RuntimeException(e);
    }
  }
}
