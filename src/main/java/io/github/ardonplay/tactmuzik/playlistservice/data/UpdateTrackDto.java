package io.github.ardonplay.tactmuzik.playlistservice.data;

import java.util.UUID;


public record UpdateTrackDto(UUID id, String name, UUID albumId, String albumName,
                             String artistName, UUID artistId,
                             String s3Path, String S3CoverPath) {

}
