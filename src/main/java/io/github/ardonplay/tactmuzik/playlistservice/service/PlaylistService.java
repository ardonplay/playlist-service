package io.github.ardonplay.tactmuzik.playlistservice.service;

import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistDetailsDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistBaseInfoDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistByIdDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.inner.TrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.exception.PlaylistNotFoundException;
import io.github.ardonplay.tactmuzik.playlistservice.exception.TrackNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistService {
  Mono<PlaylistByIdDto> getPlaylist(UUID playlistId);

  Flux<PlaylistBaseInfoDto> getUserPlaylists(UUID userId);

  Mono<PlaylistBaseInfoDto> createPlaylist(PlaylistDetailsDto createPlaylistDto, UUID userId);

  Mono<Void> changePlaylistDetails(PlaylistDetailsDto playlistDetailsDto, UUID playlistId);

  Flux<TrackDto> getPlaylistTracks(UUID playlistId);

  Mono<Void> updatePlaylistTracks(UUID playlistId, List<UUID> trackIds) throws PlaylistNotFoundException, TrackNotFoundException;

  Mono<Void> deletePlaylistTracks(UUID playlistId, List<UUID> trackIds);
}
