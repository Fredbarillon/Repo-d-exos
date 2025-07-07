package exceptions;

import java.util.InputMismatchException;

public class StringIsNotAnIntException extends InputMismatchException {
    public StringIsNotAnIntException(String message) {
        super(message);
    }
}
