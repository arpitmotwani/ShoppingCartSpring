package com.bitwise.onlineshoppingcart.validators;

/**
 * Created by arpitm on 8/22/2016.
 */
import com.bitwise.onlineshoppingcart.beans.LoginBean;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> obj) {
        return LoginBean.class.equals(obj.getClass());
    }
    @Override
    public void validate(Object obj, Errors errors) {
        LoginBean loginBean = (LoginBean)obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "errors.username", "Username  required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.password", "Password  required");

        boolean checkLength = loginBean.getUsername().length() < 5;
        if (checkLength)
            errors.rejectValue("username", "insufficient", "Username must be atleast 5 chars long");
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        boolean isPassValid = loginBean.getPassword().matches(regex);

        String errorMessage = "Invalid Password! \n must be atleast 8 characters long and \ncontain alpha numeric content, special symbols,uppercase \nand lowercase letter ";
        if (! isPassValid)
            errors.rejectValue("password", "invalidPassword", errorMessage);
    }

}