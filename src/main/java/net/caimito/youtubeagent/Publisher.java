package net.caimito.youtubeagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
  private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

  @Autowired
  private PostsRepository postsRepository;

  @EventListener
  public void handleNewFeedEntry(NewFeedEntryEvent event) {
    VideoEntry entry = (VideoEntry) event.getSource();
    LOGGER.debug("New feed entry: {}", entry.getLink());

    postsRepository.findByLink(((VideoEntry) event.getSource()).getLink())
        .ifPresentOrElse(
            post -> LOGGER.info("Post for {} already exists: ", post.getLink()),
            () -> savePost(entry));
  }

  private void savePost(VideoEntry entry) {
    LOGGER.info("Saving post: " + entry.getLink());
    PostEntity post = new PostEntity(entry.getLink(), MediaTarget.TWITTER, entry.getPublished());
    postsRepository.save(post);
  }

}
