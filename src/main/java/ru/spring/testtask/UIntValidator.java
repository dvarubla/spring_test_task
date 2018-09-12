package ru.spring.testtask;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*Валидация номера заказа*/
@Component
class UIntValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(String.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String str = (String) target;
        if(str.length() == 0){
            return;
        }
        try{
            int num = Integer.valueOf(str);
            if(num < 0){
                errors.rejectValue(null, "negativeNumber", "Вы ввели отрицательное число");
            }
        } catch (Exception e){
            errors.rejectValue(null,"notANumber", "Вы ввели не число");
        }
    }
}