package io.github.ardonplay.tactmuzik.playlistservice.controller;

import io.github.ardonplay.tactmuzik.playlistservice.data.NewTrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import io.github.ardonplay.tactmuzik.playlistservice.service.TrackService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/tracks/")
@RequiredArgsConstructor
public class TrackController {

  private final TrackService trackService;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> addTrack(@RequestBody NewTrackDto track) {
    return trackService.addTrack(track);
  }

  @DeleteMapping()
  public Mono<Void> deleteTrack(@RequestBody Flux<UUID> trackIds) {

    return trackService.removeTracks(trackIds);
  }
}
