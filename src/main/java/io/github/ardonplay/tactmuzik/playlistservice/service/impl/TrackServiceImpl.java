package io.github.ardonplay.tactmuzik.playlistservice.service.impl;

import io.github.ardonplay.tactmuzik.playlistservice.data.NewTrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import io.github.ardonplay.tactmuzik.playlistservice.repository.TrackRepository;
import io.github.ardonplay.tactmuzik.playlistservice.service.TrackService;
import io.github.ardonplay.tactmuzik.playlistservice.util.mapper.TrackMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

  private final TrackRepository trackRepository;

  private final TrackMapper trackMapper;

  @Override
  public void addTrack(NewTrackDto track) {

    TrackEntity newTrackEntity =
        trackMapper.mapNewTrackDtoToTrackEntity(track);

    trackRepository.save(newTrackEntity);
  }

  @Override
  public void removeTrack(UUID trackId) {
    trackRepository.deleteById(trackId);
  }

  @Override
  public void removeTracks(List<UUID> trackIds) {
    trackRepository.deleteAllById(trackIds);
  }
}
