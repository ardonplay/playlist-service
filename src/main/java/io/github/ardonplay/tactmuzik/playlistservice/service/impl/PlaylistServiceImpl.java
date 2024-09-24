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
import io.github.ardonplay.tactmuzik.playlistservice.repository.PlaylistRepository;
import io.github.ardonplay.tactmuzik.playlistservice.repository.PlaylistTrackRepository;
import io.github.ardonplay.tactmuzik.playlistservice.repository.TrackRepository;
import io.github.ardonplay.tactmuzik.playlistservice.service.PlaylistService;
import io.github.ardonplay.tactmuzik.playlistservice.util.mapper.PlaylistMapper;
import io.github.ardonplay.tactmuzik.playlistservice.util.mapper.TrackMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

  private final PlaylistRepository playlistRepository;

  private final PlaylistTrackRepository playlistTrackRepository;

  private final TrackRepository trackRepository;

  private final PlaylistMapper playlistMapper;

  private final TrackMapper trackMapper;

  public Mono<PlaylistByIdDto> getPlaylist(UUID playlistId) {

    Flux<TrackDto> playlistTrackEntities = playlistTrackRepository.findAllByPlaylistId(playlistId)
        .map(trackMapper::mapTrackEntityToTrackDto);

    return playlistRepository.findByPlaylistId(playlistId)
        .switchIfEmpty(Mono.error(new PlaylistNotFoundException()))
        .flatMap(playlist -> playlistTrackEntities.collectList()
            .map(tracks -> new PlaylistByIdDto(
                playlistId,
                playlist.getName(),
                playlist.getDescription(),
                tracks
            ))
        );
  }


  @Override
  public Flux<PlaylistBaseInfoDto> getUserPlaylists(UUID userId) {
    return playlistRepository.findAllByUserId(userId)
        .map(playlistMapper::mapPlaylistEntityToPlaylistBaseInfoDto);
  }

  @Override
  public Mono<PlaylistBaseInfoDto> createPlaylist(PlaylistDetailsDto createPlaylistDto,
      UUID userId) {
    return playlistRepository.save(
        PlaylistEntity.builder()
            .id(UUID.randomUUID())
            .name(createPlaylistDto.name())
            .description(createPlaylistDto.description())
            .userId(userId).build()).map(playlistMapper::mapPlaylistEntityToPlaylistBaseInfoDto);


  }

  @Override
  public Mono<Void> changePlaylistDetails(PlaylistDetailsDto playlistDetailsDto, UUID playlistId) {
    return playlistRepository.findByPlaylistId(playlistId)
        .switchIfEmpty(Mono.error(new PlaylistNotFoundException())).map(
            playlistEntity -> {
              playlistEntity.setName(playlistDetailsDto.name());
              return playlistRepository.save(playlistEntity);
            }
        ).then();

  }

  @Override
  public Flux<TrackDto> getPlaylistTracks(UUID playlistId) {

    Flux<PlaylistTrackEntity> playlistTracks = playlistTrackRepository.findAllByPlaylistId(
        playlistId);

    return playlistTracks
        .map(track -> new TrackDto(track.getTrackId(), track.getName(), track.getS3Path(),
            track.getS3CoverPath())
        );
  }

  @Override
  public Mono<Void> updatePlaylistTracks(UUID playlistId, List<UUID> trackIds)
      throws TrackNotFoundException, PlaylistNotFoundException {

    Flux<TrackEntity> trackEntityFlux = trackRepository.findAllById(trackIds);

    return trackEntityFlux.count()
        .flatMap(count -> {
          if (count != trackIds.size()) {
            return Mono.error(new TrackNotFoundException());
          }

          return playlistRepository.findByPlaylistId(playlistId)
              .switchIfEmpty(Mono.error(new PlaylistNotFoundException()))
              .flatMap(playlistEntity -> {
                Flux<PlaylistTrackEntity> tracks = trackEntityFlux.map(
                    track -> new PlaylistTrackEntity(
                        playlistId,
                        playlistEntity.getName(),
                        playlistEntity.getUserId(),
                        track
                    )
                );
                return playlistTrackRepository.saveAll(tracks).then();
              });
        });
  }


  @Override
  public Mono<Void> deletePlaylistTracks(UUID playlistId, List<UUID> trackIds) {
    Flux<PlaylistTrackEntity> tracks = playlistTrackRepository.findAllByIdAndTrackIdIn(playlistId,
        trackIds);

    return tracks.count()
        .flatMap(count -> {
          if (count != trackIds.size()) {
            return Mono.error(new TrackNotFoundException());
          }
          return playlistTrackRepository.deleteAll(tracks);
        });
  }


}
