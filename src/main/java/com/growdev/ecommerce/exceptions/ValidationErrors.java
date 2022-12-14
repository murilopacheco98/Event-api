package com.growdev.ecommerce.exceptions;

import java.util.ArrayList;
import java.util.List;

//TENHO TUDO QUE TEM NA CLASSE NA PAI
public class ValidationErrors extends StandardError {
  //VOU TER UM ATRIBUTO QUE PODE CONTER MAIS DE UM ERRO DE CAMPO E A MENSAGEM
  private List<FieldMessage> errors = new ArrayList<>();

  //SOMENTE RETORNAR A LISTA DE ERROR (DOS CAMPOS QUE ESTÃO NO DTO)
  public List<FieldMessage> getErrors() {
    return errors;
  }

  //criar um método que adiciona o elemento na lista
  public void addError(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }
}
