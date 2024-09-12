package io.github.ardonplay.tactmuzik.playlistservice.exception;


public class AppException extends BasePlaylistServiceException {
    private static final String ERROR_CODE = "000";
    private static final String ERROR_MESSAGE = "Internal error";

    public AppException() {
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public static String getErrorCode() {
        return ERROR_CODE;
    }

    public static String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
