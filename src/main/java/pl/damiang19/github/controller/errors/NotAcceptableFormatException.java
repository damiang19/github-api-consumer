package pl.damiang19.github.controller.errors;

public class NotAcceptableFormatException extends RuntimeException{

    public NotAcceptableFormatException(String message) {
        super(message);
    }
}
