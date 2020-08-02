package com.freerei.calculator.model.bahcalc;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

public class PurchaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @DecimalMin("0")
    @NotNull
    private BigDecimal purchasePrice = BigDecimal.ZERO;

    private boolean usingLoan;

    @DecimalMin("0")
    @NotNull
    private BigDecimal downPayment = BigDecimal.ZERO;

    @DecimalMin("0")
    @NotNull
    private int yearsHeld = 0;

    @DecimalMax("25.00")
    @DecimalMin("0.00")
    @NotNull
    private BigDecimal interestRate = BigDecimal.ZERO;

    @Max(100)
    @Min(0)
    @NotNull
    private int loanTerm = 0;

    private boolean needsRepairs;

    @DecimalMin("0")
    @NotNull
    private BigDecimal repairCost = BigDecimal.ZERO;

    @DecimalMin("0")
    @NotNull
    private BigDecimal afterRepairValue = BigDecimal.ZERO;

    public int getYearsHeld() {
        return yearsHeld;
    }

    public void setYearsHeld(int yearsHeld) {
        this.yearsHeld = yearsHeld;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public boolean isUsingLoan() {
        return usingLoan;
    }

    public void setUsingLoan(boolean usingLoan) {
        this.usingLoan = usingLoan;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public boolean isNeedsRepairs() {
        return needsRepairs;
    }

    public void setNeedsRepairs(boolean needsRepairs) {
        this.needsRepairs = needsRepairs;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(BigDecimal repairCost) {
        this.repairCost = repairCost;
    }

    public BigDecimal getAfterRepairValue() {
        return afterRepairValue;
    }

    public void setAfterRepairValue(BigDecimal afterRepairValue) {
        this.afterRepairValue = afterRepairValue;
    }
}