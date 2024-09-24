package io.github.ardonplay.tactmuzik.playlistservice.service;

import io.github.ardonplay.tactmuzik.playlistservice.data.NewTrackDto;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrackService {

  Mono<Void> addTrack(NewTrackDto track);

  Mono<Void> removeTrack(UUID trackId);

  Mono<Void> removeTracks(Flux<UUID> trackIds);

}
