package kiec.ireneusz.germanelearningplatformserver.exception;

public class AppException extends Exception {

    private String value;

    AppException(String message, String value) {
        super(message);
        this.value = value;
    }

    AppException(String message) {
        super(message);
    }

    public String getValue() {
        return value;
    }

}

