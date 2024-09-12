package io.github.ardonplay.tactmuzik.playlistservice.controller;

import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistDetailsDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistBaseInfoDto;
import io.github.ardonplay.tactmuzik.playlistservice.service.PlaylistService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{user_id}/playlists")
public class UserPlaylistController {

  private final PlaylistService playlistService;

  @GetMapping()
  public Flux<PlaylistBaseInfoDto> getUserPlaylists(
      @PathVariable("user_id") UUID userId) {
    return playlistService.getUserPlaylists(userId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<PlaylistBaseInfoDto> createPlaylist(@PathVariable("user_id") UUID userId,
      @RequestBody PlaylistDetailsDto createPlaylistDto) {
    return playlistService.createPlaylist(createPlaylistDto, userId);
  }


}
