package br.com.zupacademy.vinicius.mercadolivre.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, METHOD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistsValidator.class})
public @interface Exists {

    String message() default "Não existem recursos para os parâmetros dados.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean optional() default false;

    String fieldName();
    Class<?> domainClass();
}

// b6cf28fd2840645f9c8bf881fdbb1f7636dca0e1
// 55b22b79045fdf2bed5f0fb346baf8f222e5af4c