package io.github.ardonplay.tactmuzik.playlistservice.service;

import io.github.ardonplay.tactmuzik.playlistservice.data.NewTrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.UpdateTrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.inner.TrackDto;
import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrackService {

  Mono<Void> addTrack(NewTrackDto track);

  Mono<Void> removeTrack(UUID trackId);

  Mono<Void> removeTracks(List<UUID> trackIds);

  Mono<TrackDto> updateTrack(UpdateTrackDto track);
}
