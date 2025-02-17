package com.theGameOfLife.shared.domain.utils;

public class ValidateUtils {

    public validateIsNotNull(Object object, String message){
        if(object == null){
            throw new IllegalArgumentException(message);
        }
        return object;
    }

    public validateIsNotEmpty(String string, String message){
        if(string == null || string.isEmpty()){
            throw new IllegalArgumentException(message);
        }
        return string;
    }

    public validateIsNotBlank(atributo string, String message){
        if(atributo.isBlank()){
            throw new IllegalArgumentException(message);
        }
        return atributo;
    }

    public validateIsPositive(int number, String message){
        if(number <= 0){
            throw new IllegalArgumentException(message);
        }
        return number;
    }

    public isValidEmail(String email) {
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")){
            return throw new IllegalArgumentException("El correo no es valido");
        }
        return email;
    }

    public validateMaxLength(String string, int maxLength, String message){
        if(string.length() > maxLength){
            throw new IllegalArgumentException(message);
        }
        return string;
    }

    public validateIsString(String string, String message){
        if(string.matches("[0-9]")){
            throw new IllegalArgumentException(message);
        }
        return string;
    }

}