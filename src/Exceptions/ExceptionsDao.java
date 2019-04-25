/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author yuli
 */
public class ExceptionsDao extends CodeException{
    
    // ExceptionsDao codes
    public static final int SPACEPORT_EXIST = 0;
    
  
    // ExceptionsDao messages
    private final List<String> messages = Arrays.asList(
            "Spaceport already exist"
         
           );

    public ExceptionsDao(int code) {
        super(code);
    }

    @Override
    public String getMessage() {
        return messages.get(getCode());
    }
}