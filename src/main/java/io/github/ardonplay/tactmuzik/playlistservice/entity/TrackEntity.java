package io.github.ardonplay.tactmuzik.playlistservice.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("tracks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackEntity {

  @PrimaryKey
  private UUID id;

  @Column("name")
  private String name;

  @Column("album_id")
  private UUID albumId;

  @Column("album_name")
  private String albumName;

  @Column("artist_name")
  private String artistName;

  @Column("artist_id")
  private UUID artistId;

  @Column("s3_path")
  private String s3Path;

  @Column("s3_cover_path")
  private String S3CoverPath;

}
