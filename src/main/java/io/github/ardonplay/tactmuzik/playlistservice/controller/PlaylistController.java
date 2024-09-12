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

@Slf4j
@RestController
@RequestMapping("/api/v1/playlists")
@RequiredArgsConstructor
public class PlaylistController {

  private final PlaylistService playlistService;

  @GetMapping("/{id}")
  public ResponseEntity<PlaylistByIdDto> getPlaylistInfoById(@PathVariable("id") UUID id) {
    return new ResponseEntity<>(playlistService.getPlaylist(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> changePlaylistDetails(@PathVariable("id") UUID playlistId,
      @RequestBody PlaylistDetailsDto playlistDetailsDto) {
    playlistService.changePlaylistDetails(playlistDetailsDto, playlistId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{id}/tracks")
  public ResponseEntity<List<TrackDto>> getPlaylistTracks(@PathVariable("id") UUID playlistId) {
    return new ResponseEntity<>(playlistService.getPlaylistTracks(playlistId), HttpStatus.OK);
  }

  @PutMapping("/{id}/tracks")
  public ResponseEntity<Void> updatePlaylistTracks(@PathVariable("id") UUID playlistId, @RequestBody List<UUID> trackIds) {
    playlistService.updatePlaylistTracks(playlistId, trackIds);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/{id}/tracks")
  public ResponseEntity<Void> addTracksToPlaylist(@PathVariable("id") UUID playlistId, @RequestBody List<UUID> trackIds) {
    playlistService.updatePlaylistTracks(playlistId, trackIds);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}/tracks")
  public ResponseEntity<Void> deleteTracks(@PathVariable("id") UUID playlistId, @RequestBody List<UUID> trackIds) {
    playlistService.deletePlaylistTracks(playlistId, trackIds);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
