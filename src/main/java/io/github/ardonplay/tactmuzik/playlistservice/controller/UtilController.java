package io.github.ardonplay.tactmuzik.playlistservice.controller;

import com.devskiller.friendly_id.FriendlyId;
import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/utils/")
public class UtilController {

  @PostMapping("/generate_friendly_id/{uuid}")
  public String generateFriendlyId(@PathVariable UUID uuid) {
    return FriendlyId.toFriendlyId(uuid);
  }
}
