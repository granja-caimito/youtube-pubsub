package net.caimito.youtubeagent;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Component
@Profile("!test")
public class FeedWatcherImpl extends AbstractFeedWatcher implements FeedWatcher {
  private static final Logger LOGGER = LoggerFactory.getLogger(FeedWatcherImpl.class);

  @Value("${feed.url}")
  private String feedUrl;

  public InputStream getVideoXmlFromWeb(String url) throws Exception {
    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

    if (response.statusCode() == 200) {
      return response.body();
    } else {
      throw new RuntimeException("Failed to fetch the videos.xml file. HTTP status code: " + response.statusCode());
    }
  }

  @Override
  public void processFeed() {
    try {
      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feed = input.build(new XmlReader(getVideoXmlFromWeb(feedUrl)));
      doProcessFeed(feed);
    } catch (Exception e) {
      LOGGER.error("Error reading feed", e);
      throw new RuntimeException(e);
    }
  }

  @EventListener(ContextRefreshedEvent.class)
  public void onApplicationEvent() {
    processFeed();
  }

  @Scheduled(cron = "${feed.cron}")
  public void scheduledProcessFeed() {
    processFeed();
  }

}
