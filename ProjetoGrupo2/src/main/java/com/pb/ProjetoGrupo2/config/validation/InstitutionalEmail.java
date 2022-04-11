package com.pb.ProjetoGrupo2.config.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InstitutionalEmailValidation.class)
public @interface InstitutionalEmail {

    String message() default "invalid e-mail";
    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
