package io.github.ardonplay.tactmuzik.playlistservice.exception;

public class TrackNotFoundException extends BasePlaylistServiceException {
  private static final String ERROR_CODE = "404";
  private static final String ERROR_MESSAGE = "Track not found";

  public TrackNotFoundException() {
    super(ERROR_MESSAGE, ERROR_MESSAGE);
  }

}
