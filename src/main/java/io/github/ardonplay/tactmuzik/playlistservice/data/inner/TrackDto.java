package io.github.ardonplay.tactmuzik.playlistservice.data.inner;

import java.util.UUID;

public record TrackDto(UUID id, String name, String s3Path, String s3CoverPath ) {

}
