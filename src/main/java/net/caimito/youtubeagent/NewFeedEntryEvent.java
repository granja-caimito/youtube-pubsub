package net.caimito.youtubeagent;

import org.springframework.context.ApplicationEvent;

public class NewFeedEntryEvent extends ApplicationEvent {

  private static final long serialVersionUID = 1L;

  public NewFeedEntryEvent(Object source) {
    super(source);
  }

}
