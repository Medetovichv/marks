//package com.example.marks.validator;
//
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.*;
//
//@Target({ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = BetweenValidator.class)
//@Documented
//public @interface Between{
//    String message() default "{Between.invalid}";
//    Class<?> [] groups() default{};
//
//    Class<? extends Payload> [] payload() default {};
//
//    int from();
//    int to();
//}