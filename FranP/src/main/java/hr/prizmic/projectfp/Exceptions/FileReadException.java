package hr.prizmic.projectfp.Exceptions;

import java.io.FileNotFoundException;

public class FileReadException extends Exception {
    public FileReadException() {
    }

    public FileReadException(String message) {
        super(message);
    }

    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReadException(Throwable cause) {
        super(cause);
    }

    public FileReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
