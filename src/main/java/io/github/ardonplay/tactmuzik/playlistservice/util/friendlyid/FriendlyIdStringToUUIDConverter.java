package io.github.ardonplay.tactmuzik.playlistservice.util.friendlyid;

import com.devskiller.friendly_id.FriendlyId;
import org.springframework.core.convert.converter.Converter;
import java.util.UUID;

public class FriendlyIdStringToUUIDConverter implements Converter<String, UUID> {

    @Override
    public UUID convert(String source) {
        try {
            return FriendlyId.toUuid(source);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + source, e);
        }
    }
}
