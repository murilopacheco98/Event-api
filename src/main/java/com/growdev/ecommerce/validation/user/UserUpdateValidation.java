package com.growdev.ecommerce.validation.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//ESTAMOS CRIANDO UMA VALIDAÇÃO (annotations
//@Constraint(qual a classe que implementa a validação)
//@Target - @Reten
@Constraint(validatedBy = UserUpdateValidationBean.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserUpdateValidation {

    //implementação padrão do spring para uso de annotations
    String message() default "Validator error";
    Class<?>[] groups() default{};
    Class <? extends Payload>[] payload() default{};

}


