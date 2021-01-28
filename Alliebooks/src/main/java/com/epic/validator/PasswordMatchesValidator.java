package com.epic.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.alliebooks.dto.UserRegistrationDto;
import com.epic.annotations.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> { 
  
  @Override
  public void initialize(PasswordMatches constraintAnnotation) {       
  }
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){   
      UserRegistrationDto user = (UserRegistrationDto) obj;
      return user.getPassword().equals(user.getMatchingPassword());    
  }     
}