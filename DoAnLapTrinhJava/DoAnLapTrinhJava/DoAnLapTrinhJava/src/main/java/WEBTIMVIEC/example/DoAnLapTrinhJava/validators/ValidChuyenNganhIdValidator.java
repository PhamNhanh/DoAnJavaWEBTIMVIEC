package com.example.nguyenhoangdangkhoa.validators;





import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.ChuyenNganh;
import WEBTIMVIEC.example.DoAnLapTrinhJava.validators.annotations.ValidChuyenNganhId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidChuyenNganhIdValidator implements
        ConstraintValidator<ValidChuyenNganhId, ChuyenNganh> {
    @Override
    public boolean isValid(ChuyenNganh chuyenNganh,
                           ConstraintValidatorContext context) {
        return chuyenNganh != null && chuyenNganh.getChuyenNganhId() != null;
    }
}