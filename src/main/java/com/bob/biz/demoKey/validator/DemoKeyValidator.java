package com.bob.biz.demoKey.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bob.biz.demoKey.model.DemoKey;

public class DemoKeyValidator implements Validator {

    @Override
    public boolean supports(Class<?> c) {
        return DemoKey.class.isAssignableFrom(c);
    }

    @Override
    public void validate(Object command, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.name.empty");
        // DemoKey domeModel = (DemoKey) command;
        // if (domeModel.getAge() < 0)
        // errors.rejectValue("age", "field.age.negative");
    }

}
