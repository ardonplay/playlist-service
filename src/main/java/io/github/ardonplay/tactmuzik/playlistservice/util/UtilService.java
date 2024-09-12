package io.github.ardonplay.tactmuzik.playlistservice.util;


import io.github.ardonplay.tactmuzik.playlistservice.exception.BasePlaylistServiceException;
import io.github.ardonplay.tactmuzik.playlistservice.exception.ErrorResponse;

public class UtilService {

  public static ErrorResponse createErrorResponse(BasePlaylistServiceException ex) {
    return new ErrorResponse(ex.getCode(), ex.getMessage());
  }

}
