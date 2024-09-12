package io.github.ardonplay.tactmuzik.playlistservice.controller;

import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistByIdDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistDetailsDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.inner.TrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.service.PlaylistService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/playlists")
@RequiredArgsConstructor
public class PlaylistController {

  private final PlaylistService playlistService;

  @GetMapping("/{id}")
  public Mono<PlaylistByIdDto> getPlaylistInfoById(@PathVariable("id") UUID id) {
    return playlistService.getPlaylist(id);
  }

  @PutMapping("/{id}")
  public Mono<Void> changePlaylistDetails(@PathVariable("id") UUID playlistId,
      @RequestBody PlaylistDetailsDto playlistDetailsDto) {
    return playlistService.changePlaylistDetails(playlistDetailsDto, playlistId);
  }

  @GetMapping("/{id}/tracks")
  public Flux<TrackDto> getPlaylistTracks(@PathVariable("id") UUID playlistId) {
    return playlistService.getPlaylistTracks(playlistId);
  }

  @PutMapping("/{id}/tracks")
  public Mono<Void> updatePlaylistTracks(@PathVariable("id") UUID playlistId,
      @RequestBody List<UUID> trackIds) {
    return playlistService.updatePlaylistTracks(playlistId, trackIds);
  }

  @PostMapping("/{id}/tracks")
  public Mono<Void> addTracksToPlaylist(@PathVariable("id") UUID playlistId,
      @RequestBody List<UUID> trackIds) {
    return playlistService.updatePlaylistTracks(playlistId, trackIds);
  }

  @DeleteMapping("/{id}/tracks")
  public Mono<Void> deleteTracks(@PathVariable("id") UUID playlistId,
      @RequestBody List<UUID> trackIds) {
    return playlistService.deletePlaylistTracks(playlistId, trackIds);
  }

}
