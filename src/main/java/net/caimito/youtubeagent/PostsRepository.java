package net.caimito.youtubeagent;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<PostEntity, Long> {

  Optional<PostEntity> findByLink(String link);

}
