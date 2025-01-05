package net.caimito.youtubeagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/youtube", "/youtube/" })
public class YouTubeEndpoint {
  private static final Logger LOGGER = LoggerFactory.getLogger(YouTubeEndpoint.class);

  @GetMapping
  public String handleYouTubeRequest(
      @RequestParam("hub.topic") String topic,
      @RequestParam("hub.challenge") String challenge,
      @RequestParam("hub.mode") String mode,
      @RequestParam("hub.lease_seconds") int leaseSeconds) {

    LOGGER.info("Received YouTube Webhook Request:");
    LOGGER.info("Topic: {}", topic);
    LOGGER.info("Mode: {}", mode);
    LOGGER.info("Lease Seconds: {}", leaseSeconds);

    // Respond with the hub.challenge as required by YouTube
    return challenge;
  }
}