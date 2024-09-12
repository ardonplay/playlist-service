package io.github.ardonplay.tactmuzik.playlistservice.service.impl;

import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistBaseInfoDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistByIdDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistDetailsDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.inner.TrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistEntity;
import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistTrackEntity;
import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import io.github.ardonplay.tactmuzik.playlistservice.exception.PlaylistNotFoundException;
import io.github.ardonplay.tactmuzik.playlistservice.exception.TrackNotFoundException;
import io.github.ardonplay.tactmuzik.playlistservice.repository.PlaylistByUserIdRepository;
import io.github.ardonplay.tactmuzik.playlistservice.repository.PlaylistRepository;
import io.github.ardonplay.tactmuzik.playlistservice.repository.PlaylistTrackRepository;
import io.github.ardonplay.tactmuzik.playlistservice.repository.TrackRepository;
import io.github.ardonplay.tactmuzik.playlistservice.service.PlaylistService;
import io.github.ardonplay.tactmuzik.playlistservice.util.mapper.PlaylistMapper;
import io.github.ardonplay.tactmuzik.playlistservice.util.mapper.TrackMapper;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

  private final PlaylistRepository playlistRepository;

  private final PlaylistTrackRepository playlistTrackRepository;

  private final PlaylistByUserIdRepository playlistByUserIdRepository;

  private final TrackRepository trackRepository;

  private final PlaylistMapper playlistMapper;

  private final TrackMapper trackMapper;

  @Override
  public PlaylistByIdDto getPlaylist(UUID playlistId) {
    PlaylistEntity playlistEntity = playlistRepository.findById(playlistId).orElseThrow(
        PlaylistNotFoundException::new);

    List<PlaylistTrackEntity> playlistTrackEntities = playlistTrackRepository.findAllByPlaylistId(
        playlistId);

    var tracks = playlistTrackEntities.stream().map(trackMapper::mapTrackEntityToTrackDto).toList();

    return new PlaylistByIdDto(playlistId, playlistEntity.getName(), tracks);
  }

  @Override
  public List<PlaylistBaseInfoDto> getUserPlaylists(UUID userId) {
    return playlistByUserIdRepository.findAllByUserId(userId).stream()
        .map(playlistMapper::mapPlaylistByUserIdEntityToPlaylistByIdDto).toList();
  }

  @Override
  public PlaylistBaseInfoDto createPlaylist(PlaylistDetailsDto createPlaylistDto, UUID userId) {
    return (playlistMapper.mapPlaylistEntityToPlaylistBaseInfoDto(playlistRepository.save(
        PlaylistEntity.builder()
            .id(UUID.randomUUID())
            .name(createPlaylistDto.name())
            .description(createPlaylistDto.description())
            .userId(userId).build())));


  }

  @Override
  public void changePlaylistDetails(PlaylistDetailsDto playlistDetailsDto, UUID playlistId) {
    PlaylistEntity playlist = playlistRepository.findById(playlistId)
        .orElseThrow(PlaylistNotFoundException::new);
    playlist.setName(playlistDetailsDto.name());
    playlistRepository.save(playlist);
  }

  @Override
  public List<TrackDto> getPlaylistTracks(UUID playlistId) {

    List<PlaylistTrackEntity> playlistTracks = playlistTrackRepository.findAllById(
        Collections.singleton(playlistId));

    return playlistTracks
        .stream().map(track -> new TrackDto(track.getTrackId(), track.getName(), track.getS3Path(),
            track.getS3CoverPath())
        )
        .toList();
  }

  @Override
  public void updatePlaylistTracks(UUID playlistId, List<UUID> trackIds)
      throws TrackNotFoundException, PlaylistNotFoundException {

    PlaylistEntity playlistEntity = playlistRepository.findById(playlistId)
        .orElseThrow(PlaylistNotFoundException::new);
    List<TrackEntity> tracks = trackRepository.findAllById(trackIds);

    if (tracks.size() != trackIds.size()) {
      throw new TrackNotFoundException();
    }

    List<PlaylistTrackEntity> playlistEntities = tracks.stream().map(
        track -> new PlaylistTrackEntity(playlistId, playlistEntity.getName(),
            playlistEntity.getUserId(), track)).toList();

    playlistTrackRepository.saveAll(playlistEntities);

  }

  @Override
  public void deletePlaylistTracks(UUID playlistId, List<UUID> trackIds) {
    List<PlaylistTrackEntity> tracks = playlistTrackRepository.findAllByIdAndTrackIdIn(playlistId,
        trackIds);

    if (tracks.size() != trackIds.size()) {
      throw new TrackNotFoundException();
    }

    playlistTrackRepository.deleteAll(tracks);

  }


}
