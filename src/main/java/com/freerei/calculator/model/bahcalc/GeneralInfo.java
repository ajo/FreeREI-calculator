package com.freerei.calculator.model.bahcalc;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class GeneralInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String title;

    @NotBlank
    private String address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
