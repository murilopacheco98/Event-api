package com.growdev.ecommerce.validation;

import com.growdev.ecommerce.dto.CityDTO;
import com.growdev.ecommerce.entities.City;
import com.growdev.ecommerce.exceptions.FieldMessage;
import com.growdev.ecommerce.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CityValidationBean implements ConstraintValidator<CityValidation, CityDTO> {

    @Autowired
    CityRepository cityRepository;

    @Override
    public void initialize(CityValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CityDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        City city = cityRepository.findByName(value.getName());
        if (city != null) {
            //uma excecao padrao
            list.add(new FieldMessage("name", "This city already exist."));
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
