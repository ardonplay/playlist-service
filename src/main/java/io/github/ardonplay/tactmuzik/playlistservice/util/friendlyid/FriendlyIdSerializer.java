package io.github.ardonplay.tactmuzik.playlistservice.util.friendlyid;

import com.devskiller.friendly_id.FriendlyId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.UUID;

public class FriendlyIdSerializer extends JsonSerializer<UUID> {

    @Override
    public void serialize(UUID value, JsonGenerator gen, SerializerProvider serializers) 
            throws IOException {
        gen.writeString(FriendlyId.toFriendlyId(value));
    }
}
