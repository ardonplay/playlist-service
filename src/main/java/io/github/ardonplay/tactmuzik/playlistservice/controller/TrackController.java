package io.github.ardonplay.tactmuzik.playlistservice.controller;

import io.github.ardonplay.tactmuzik.playlistservice.data.NewTrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import io.github.ardonplay.tactmuzik.playlistservice.service.TrackService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/tracks/")
@RequiredArgsConstructor
public class TrackController {

  private final TrackService trackService;

  @PostMapping()
  public ResponseEntity<Void> addTrack(@RequestBody NewTrackDto track) {
    //TODO add logic for adding track
    trackService.addTrack(track);
    return ResponseEntity.created(URI.create("/api/v1/tracks/")).build();
  }

  @DeleteMapping()
  public ResponseEntity<Void> deleteTrack(@RequestBody List<UUID> trackIds) {
    //TODO add logic for deleting tracks

    return ResponseEntity.ok().build();
  }
}
