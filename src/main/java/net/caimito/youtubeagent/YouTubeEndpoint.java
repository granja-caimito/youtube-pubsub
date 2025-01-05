package net.caimito.youtubeagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
public class YouTubeEndpoint {
  private static final Logger LOGGER = LoggerFactory.getLogger(YouTubeEndpoint.class);

  @GetMapping("/youtube")
  public String handleSubscriptionVerification(
      @RequestParam("hub.challenge") String hubChallenge,
      @RequestParam("hub.mode") String hubMode,
      @RequestParam(value = "hub.lease_seconds", required = false) String hubLeaseSeconds) {

    // Log the incoming parameters for debugging (optional)
    LOGGER
        .info("hub.mode: " + hubMode + ", hub.challenge: " + hubChallenge + ", hub.lease_seconds: " + hubLeaseSeconds);

    // Return the hub.challenge value as plain text in the response
    return hubChallenge;
  }

}
