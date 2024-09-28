package io.github.ardonplay.tactmuzik.playlistservice.repository;

import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.ReactiveCassandraBatchOperations;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleReactiveCassandraRepository;
import reactor.core.publisher.Mono;

@Slf4j
public class PlaylistTrackBatchRepositoryImpl extends
    SimpleReactiveCassandraRepository<TrackEntity, UUID> {

  private final PlaylistTrackRepository playlistTrackRepository;
  private final ReactiveCassandraOperations reactiveOperations;


  public PlaylistTrackBatchRepositoryImpl(CassandraEntityInformation<TrackEntity, UUID> metadata,
      ReactiveCassandraOperations operations, PlaylistTrackRepository playlistTrackRepository) {
    super(metadata, operations);
    this.playlistTrackRepository = playlistTrackRepository;
    this.reactiveOperations = operations;
  }

  public <S extends TrackEntity> Mono<S> updateTrack(final S track) {
    final ReactiveCassandraBatchOperations batchOperations = reactiveOperations.batchOps();

    log.info("Starting update for track: {}", track.getId());

    return updatePlaylistTracks(track, batchOperations)
        .doOnNext(batchOps -> batchOps.update(track))
        .flatMap(ReactiveCassandraBatchOperations::execute)
        .doOnSuccess(unused -> log.info("Successfully updated track: {}", track.getId()))
        .doOnError(error -> log.error("Failed to update track: {}", track.getId(), error))
        .then(Mono.just(track));
  }

  private Mono<ReactiveCassandraBatchOperations> updatePlaylistTracks(TrackEntity track,
      ReactiveCassandraBatchOperations batchOperations) {
    return playlistTrackRepository.findAllByTrackId(track.getId())
        .map(playlistTrackEntity -> {
          playlistTrackEntity.setTrackId(track.getId());
          playlistTrackEntity.setTrackTitle(track.getName());
          playlistTrackEntity.setAlbumName(track.getAlbumName());
          playlistTrackEntity.setAlbumId(track.getAlbumId());
          playlistTrackEntity.setArtistName(track.getArtistName());
          playlistTrackEntity.setArtistId(track.getArtistId());
          playlistTrackEntity.setS3Path(track.getS3Path());
          playlistTrackEntity.setS3CoverPath(track.getS3CoverPath());
          return playlistTrackEntity;
        })
        .collectList()
        .map(batchOperations::update);
  }
}
