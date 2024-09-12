package io.github.ardonplay.tactmuzik.playlistservice.data;

import java.util.UUID;

public record NewTrackDto(UUID id, UUID albumId,
                          String albumName,
                          String artistName,
                          UUID artistId,
                          String name,
                          String s3Path,
                          String S3CoverPath) {

}
