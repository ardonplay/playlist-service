package io.github.ardonplay.tactmuzik.playlistservice.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlaylistDetailsDto(String name, String description, @JsonProperty("public") Boolean isPublic) {

}
