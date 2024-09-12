package io.github.ardonplay.tactmuzik.playlistservice.data;

import java.util.UUID;

public record PlaylistBaseInfoDto(UUID id, String name, String description, int trackCount) {

}
