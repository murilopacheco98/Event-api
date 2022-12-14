package com.growdev.ecommerce.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CityValidationBean.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CityValidation {

    //implementação padrão do spring para uso de annotations
    String message() default "Validator error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
