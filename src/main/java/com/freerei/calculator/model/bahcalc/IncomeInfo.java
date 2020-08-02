package com.freerei.calculator.model.bahcalc;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class IncomeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private BigDecimal monthlyRentIncome = BigDecimal.ZERO;

    @NotNull
    private BigDecimal monthlyRentIncomeIncrease = BigDecimal.ZERO;

    @NotNull
    private BigDecimal monthlyOtherIncome = BigDecimal.ZERO;

    @NotNull
    private BigDecimal monthlyOtherIncomeIncrease = BigDecimal.ZERO;

    public BigDecimal getMonthlyRentIncome() {
        return monthlyRentIncome;
    }

    public void setMonthlyRentIncome(BigDecimal monthlyRentIncome) {
        this.monthlyRentIncome = monthlyRentIncome;
    }

    public BigDecimal getMonthlyRentIncomeIncrease() {
        return monthlyRentIncomeIncrease;
    }

    public void setMonthlyRentIncomeIncrease(BigDecimal monthlyRentIncomeIncrease) {
        this.monthlyRentIncomeIncrease = monthlyRentIncomeIncrease;
    }

    public BigDecimal getMonthlyOtherIncome() {
        return monthlyOtherIncome;
    }

    public void setMonthlyOtherIncome(BigDecimal monthlyOtherIncome) {
        this.monthlyOtherIncome = monthlyOtherIncome;
    }

    public BigDecimal getMonthlyOtherIncomeIncrease() {
        return monthlyOtherIncomeIncrease;
    }

    public void setMonthlyOtherIncomeIncrease(BigDecimal monthlyOtherIncomeIncrease) {
        this.monthlyOtherIncomeIncrease = monthlyOtherIncomeIncrease;
    }
}
