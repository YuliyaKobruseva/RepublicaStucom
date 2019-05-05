/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dafna
 */
public class ExceptionsDatabase extends exceptions.CodeException {
    // ExceptionsDao codes

    public static final int SPACESHIPS_NOT_FOUND = 0;
    public static final int MULTIPLE_ACCTION_FAILED = 1;

    // ExceptionsDao messages
    private final List<String> messages = Arrays.asList(
            "There is no airport available",
            "An error has occured. Try again later."
    );

    public ExceptionsDatabase(int code) {
        super(code);
    }

    @Override
    public String getMessage() {
        return messages.get(getCode());
    }
}
