package io.github.ardonplay.tactmuzik.playlistservice.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePlaylistServiceException extends RuntimeException {

  private final String code;

  public BasePlaylistServiceException(String message, String code) {
    super(message);
    this.code = code;
  }

}
