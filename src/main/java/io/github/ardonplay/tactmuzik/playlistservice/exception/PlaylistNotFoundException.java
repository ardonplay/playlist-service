package io.github.ardonplay.tactmuzik.playlistservice.exception;

public class PlaylistNotFoundException extends BasePlaylistServiceException {

  private static final String ERROR_CODE = "404";
  private static final String ERROR_MESSAGE = "Playlist not found";

  public PlaylistNotFoundException() {
    super(ERROR_CODE, ERROR_MESSAGE);
  }
}
