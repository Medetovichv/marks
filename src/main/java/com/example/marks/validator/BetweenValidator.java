//package com.example.marks.validator;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class BetweenValidator implements ConstraintValidator<Between, Integer> {
//    private int from = 0;
//    @Override
//    public void initialize(Between c) {
//        from = c.from();
//    }
//
//    @Override
//    public boolean isValid(Integer i, ConstraintValidatorContext c) {
//        return i != null && i > 8;
//    }
//}
