package io.github.ardonplay.tactmuzik.playlistservice.util.friendlyid;

import com.devskiller.friendly_id.FriendlyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.UUID;

public class FriendlyIdDeserializer extends JsonDeserializer<UUID> {

  @Override
  public UUID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String value = p.getText();
    try {
      return FriendlyId.toUuid(value);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Invalid UUID format: " + value);
    }
  }
}
