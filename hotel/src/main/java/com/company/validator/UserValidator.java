package com.company.validator;

import com.company.api.service.IUserService;
import com.company.model.User;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
@PropertySource({"classpath:messages.properties"})
public class UserValidator implements Validator {

    private IUserService userService;

    public UserValidator(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if(user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if(userService.loadUserByUsername(user.getUsername())!=null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if(user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if(!user.getPasswordConfirm().equals(user.getPassword())){
            errors.rejectValue("passwordConfirm", "Different.userForm.password");
        }

    }
}
