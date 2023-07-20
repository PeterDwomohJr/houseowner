package com.dwomo.houseowner.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ListValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoPersonallyIdentifiableInformation {

    String message() default "Personally Identifiable Information found within the message.";

    Class<?>[] groups() default {};

    Class<? extends Payload >[] payload() default {};
}
