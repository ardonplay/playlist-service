package io.github.ardonplay.tactmuzik.playlistservice.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("playlists_tracks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistTrackEntity {

  @PrimaryKeyColumn(name = "playlist_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
  private UUID id;

  @PrimaryKeyColumn(name = "track_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
  private UUID trackId;

  @Column("name")
  private String name;

  @Column("author_id")
  private UUID userId;

  @Column("album_id")
  private UUID albumId;

  @Column("album_name")
  private String albumName;

  @Column("artist_name")
  private String artistName;

  @Column("artist_id")
  private UUID artistId;

  @Column("track_title")
  private String trackTitle;

  @Column("s3_path")
  private String s3Path;

  @Column("s3_cover_path")
  private String s3CoverPath;

  @Column("position")
  private Integer position;

  public PlaylistTrackEntity(UUID id, String name, UUID userId, TrackEntity trackEntity) {

    this.trackId = trackEntity.getId();
    this.s3CoverPath = trackEntity.getS3CoverPath();
    this.s3Path = trackEntity.getS3Path();
    this.trackTitle = trackEntity.getName();
    this.albumName = trackEntity.getAlbumName();
    this.artistName = trackEntity.getArtistName();
    this.artistId = trackEntity.getArtistId();
    this.albumId = trackEntity.getAlbumId();

    this.id = id;
    this.name = name;
    this.userId = userId;

  }
}
