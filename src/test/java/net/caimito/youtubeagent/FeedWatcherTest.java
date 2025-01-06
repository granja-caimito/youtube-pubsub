package net.caimito.youtubeagent;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class FeedWatcherTest {

  @Autowired
  private FeedWatcher feedWatcher;

  @Autowired
  private PostsRepository postsRepository;

  @Test
  void processFeed() {
    feedWatcher.processFeed();

    assertThat(postsRepository.count()).isEqualTo(1);
  }

}
