package com.growdev.ecommerce.validation.user;

import com.growdev.ecommerce.dto.user.UserDTO;
import com.growdev.ecommerce.entities.UserEntity;
import com.growdev.ecommerce.exceptions.FieldMessage;
import com.growdev.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

//A CLASSE IMPLEMENTA O CONSTRAINTVALIDATOR POIS HAVERÁ UMA ANNOTATION LIGADA A CLASSE E UMA CLASSE QUE RECEBERÁ A ANNOTATION
public class UserInsertValidationBean implements ConstraintValidator<UserInsertValidation, UserDTO> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void initialize(UserInsertValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        UserEntity usuario = userRepository.findByEmail(value.getEmail());
        if (usuario != null) {
            list.add(new FieldMessage("email", "Esse email já existe"));
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
