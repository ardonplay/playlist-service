package io.github.ardonplay.tactmuzik.playlistservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.ardonplay.tactmuzik.playlistservice.util.friendlyid.FriendlyIdDeserializer;
import io.github.ardonplay.tactmuzik.playlistservice.util.friendlyid.FriendlyIdSerializer;
import java.text.SimpleDateFormat;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
        SimpleModule module = new SimpleModule();
        SimpleDateFormat df = new SimpleDateFormat();
        module.addSerializer(UUID.class, new FriendlyIdSerializer());
        module.addDeserializer(UUID.class, new FriendlyIdDeserializer());
        objectMapper.registerModule(module);
        objectMapper.setDateFormat(df);
        return objectMapper;
    }
}