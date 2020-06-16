package kiec.ireneusz.germanelearningplatformserver.exception;

public class NotFoundException extends AppException {

    private static String NOT_FOUND = "VALUE_NOT_FOUND";

    public NotFoundException() {
        super(NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class clazz) {
        super(NOT_FOUND, clazz.getSimpleName().toUpperCase());
    }

}

