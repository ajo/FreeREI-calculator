package com.freerei.calculator.model.bahcalc;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ExpenseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @DecimalMin("0")
    private BigDecimal taxExpense = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal taxExpenseIncrease = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal insuranceExpense = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal insuranceExpenseIncrease = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal maintenanceExpense = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal maintenanceExpenseIncrease = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal utilityExpense = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal utilityExpenseIncrease = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal otherExpense = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal otherExpenseIncrease = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal managementExpensePercentage = BigDecimal.ZERO;

    @NotNull
    @DecimalMin("0")
    private BigDecimal vacancyRateExpensePercentage = BigDecimal.ZERO;

    public BigDecimal getTaxExpense() {
        return taxExpense;
    }

    public void setTaxExpense(BigDecimal taxExpense) {
        this.taxExpense = taxExpense;
    }

    public BigDecimal getTaxExpenseIncrease() {
        return taxExpenseIncrease;
    }

    public void setTaxExpenseIncrease(BigDecimal taxExpenseIncrease) {
        this.taxExpenseIncrease = taxExpenseIncrease;
    }

    public BigDecimal getInsuranceExpense() {
        return insuranceExpense;
    }

    public void setInsuranceExpense(BigDecimal insuranceExpense) {
        this.insuranceExpense = insuranceExpense;
    }

    public BigDecimal getInsuranceExpenseIncrease() {
        return insuranceExpenseIncrease;
    }

    public void setInsuranceExpenseIncrease(BigDecimal insuranceExpenseIncrease) {
        this.insuranceExpenseIncrease = insuranceExpenseIncrease;
    }

    public BigDecimal getMaintenanceExpense() {
        return maintenanceExpense;
    }

    public void setMaintenanceExpense(BigDecimal maintenanceExpense) {
        this.maintenanceExpense = maintenanceExpense;
    }

    public BigDecimal getMaintenanceExpenseIncrease() {
        return maintenanceExpenseIncrease;
    }

    public void setMaintenanceExpenseIncrease(BigDecimal maintenanceExpenseIncrease) {
        this.maintenanceExpenseIncrease = maintenanceExpenseIncrease;
    }

    public BigDecimal getUtilityExpense() {
        return utilityExpense;
    }

    public void setUtilityExpense(BigDecimal utilityExpense) {
        this.utilityExpense = utilityExpense;
    }

    public BigDecimal getUtilityExpenseIncrease() {
        return utilityExpenseIncrease;
    }

    public void setUtilityExpenseIncrease(BigDecimal utilityExpenseIncrease) {
        this.utilityExpenseIncrease = utilityExpenseIncrease;
    }

    public BigDecimal getOtherExpense() {
        return otherExpense;
    }

    public void setOtherExpense(BigDecimal otherExpense) {
        this.otherExpense = otherExpense;
    }

    public BigDecimal getOtherExpenseIncrease() {
        return otherExpenseIncrease;
    }

    public void setOtherExpenseIncrease(BigDecimal otherExpenseIncrease) {
        this.otherExpenseIncrease = otherExpenseIncrease;
    }

    public BigDecimal getManagementExpensePercentage() {
        return managementExpensePercentage;
    }

    public void setManagementExpensePercentage(BigDecimal managementExpensePercentage) {
        this.managementExpensePercentage = managementExpensePercentage;
    }

    public BigDecimal getVacancyRateExpensePercentage() {
        return vacancyRateExpensePercentage;
    }

    public void setVacancyRateExpensePercentage(BigDecimal vacancyRateExpensePercentage) {
        this.vacancyRateExpensePercentage = vacancyRateExpensePercentage;
    }
}