package com.example.base.jos;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupJob {

//  private final UserService userService;
//
//  @EventListener(ApplicationReadyEvent.class)
//  public void runAtStartup() {
//    userService.cacheFlushAll();
//  }
}
