package io.github.ardonplay.tactmuzik.playlistservice.service;

import io.github.ardonplay.tactmuzik.playlistservice.data.NewTrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import java.util.List;
import java.util.UUID;

public interface TrackService {

  void addTrack(NewTrackDto track);

  void removeTrack(UUID trackId);

  void removeTracks(List<UUID> trackIds);

}
