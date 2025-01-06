package net.caimito.youtubeagent;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Component
@Profile("test")
public class FakeFeedWatcher extends AbstractFeedWatcher implements FeedWatcher {
  private static final Logger LOGGER = LoggerFactory.getLogger(FakeFeedWatcher.class);

  @Override
  public void processFeed() {
    try {
      InputStream file = getClass().getResourceAsStream("/videos.xml");

      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feed = input.build(new XmlReader(file));
      doProcessFeed(feed);
    } catch (Exception e) {
      LOGGER.error("Error reading feed", e);
      throw new RuntimeException(e);
    }
  }

}