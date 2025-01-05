package net.caimito.youtubeagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping(consumes = "application/atom+xml")
  public ResponseEntity<Void> handleNotification(@RequestBody String notificationPayload) {

    LOGGER.info("Push notification received:");
    LOGGER.info(notificationPayload);

    // Process the notification payload (e.g., parse the XML, store it, etc.)
    // For now, we're just logging it.

    // Respond with 204 No Content to acknowledge receipt
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}