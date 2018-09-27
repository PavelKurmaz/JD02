package com.gmail.kurmazpavel.util;

import com.gmail.kurmazpavel.dto.AdminDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.regex.Pattern;

@Component
public class AdminValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return AdminDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "login", "Wrong login");
        ValidationUtils.rejectIfEmpty(errors, "password", "Wrong password");
        AdminDTO admin = (AdminDTO) o;
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(admin.getEmail()).matches())){
            errors.rejectValue("email", "invalid email");
        }
    }
}
