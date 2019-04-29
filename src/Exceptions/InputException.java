package exceptions;

import java.util.Arrays;
import java.util.List;

/**
 * Exception of problems with commands
 *
 * @author Yuli
 */
public class InputException extends CodeException {

    // Exception codes    
    public static final int WRONG_DATA = 0;

    // Exception messages
    private final List<String> messages = Arrays.asList(            
            "Incorrect data");

    public InputException(int code) {
        super(code);
    }

    @Override
    public String getMessage() {
        return messages.get(getCode());
    }

}
