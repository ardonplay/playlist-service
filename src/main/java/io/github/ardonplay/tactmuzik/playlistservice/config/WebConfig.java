package io.github.ardonplay.tactmuzik.playlistservice.config;

import io.github.ardonplay.tactmuzik.playlistservice.util.friendlyid.FriendlyIdStringToUUIDConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new FriendlyIdStringToUUIDConverter());
    }
}