package WEBTIMVIEC.example.DoAnLapTrinhJava.validators.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = com.example.nguyenhoangdangkhoa.validators.ValidChuyenNganhIdValidator.class)
@Documented
public @interface ValidChuyenNganhId {
    String message() default "Invalid Category Id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
