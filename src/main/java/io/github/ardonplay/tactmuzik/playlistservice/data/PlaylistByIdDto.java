package io.github.ardonplay.tactmuzik.playlistservice.data;

import io.github.ardonplay.tactmuzik.playlistservice.data.inner.TrackDto;
import java.util.List;
import java.util.UUID;

public record PlaylistByIdDto(UUID id, String name, String description, List<TrackDto> tracks) {

}
