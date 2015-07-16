package com.mall.biz.demo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mall.biz.demo.model.Demo;

public class DemoValidator implements Validator {

    @Override
    public boolean supports(Class<?> c) {
        return Demo.class.isAssignableFrom(c);
    }

    @Override
    public void validate(Object command, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "field.age.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDay", "field.birthDay.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "score", "field.score.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "score", "field.isGraduated.empty");
        Demo domeModel = (Demo) command;
        if (domeModel.getAge() < 0)
            errors.rejectValue("age", "field.age.negative");
    }

}
