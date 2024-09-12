package io.github.ardonplay.tactmuzik.playlistservice.service.impl;

import io.github.ardonplay.tactmuzik.playlistservice.data.NewTrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import io.github.ardonplay.tactmuzik.playlistservice.repository.TrackRepository;
import io.github.ardonplay.tactmuzik.playlistservice.service.TrackService;
import io.github.ardonplay.tactmuzik.playlistservice.util.mapper.TrackMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

  private final TrackRepository trackRepository;

  private final TrackMapper trackMapper;

  @Override
  public Mono<Void> addTrack(NewTrackDto track) {

    TrackEntity newTrackEntity =
        trackMapper.mapNewTrackDtoToTrackEntity(track);

    return trackRepository.save(newTrackEntity).then();
  }

  @Override
  public Mono<Void> removeTrack(UUID trackId) {
   return trackRepository.deleteById(trackId);
  }

  @Override
  public Mono<Void> removeTracks(Flux<UUID> trackIds) {
    return trackRepository.deleteAllById(trackIds);
  }
}
