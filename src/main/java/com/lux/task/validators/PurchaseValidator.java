package com.lux.task.validators;

import com.lux.task.dao.models.Purchase;
import org.springframework.validation.Errors;

public class PurchaseValidator {
    private Purchase purchase;
    private Errors res;

    public PurchaseValidator(Purchase purchase, Errors res) {
        this.purchase = purchase;
        this.res = res;

    }

    public void validate(){
        if (purchase.getQuantity()<=0){
            res.rejectValue("quantity", "negative or zero", "quantity should be integer positive");
        }
    }
}