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

public interface PlaylistService {
  PlaylistByIdDto getPlaylist(UUID playlistId);

  List<PlaylistBaseInfoDto> getUserPlaylists(UUID userId);

  PlaylistBaseInfoDto createPlaylist(PlaylistDetailsDto createPlaylistDto, UUID userId);

  void changePlaylistDetails(PlaylistDetailsDto playlistDetailsDto, UUID playlistId);

  List<TrackDto> getPlaylistTracks(UUID playlistId);

  void updatePlaylistTracks(UUID playlistId, List<UUID> trackIds) throws PlaylistNotFoundException, TrackNotFoundException;

  void deletePlaylistTracks(UUID playlistId, List<UUID> trackIds);
}
