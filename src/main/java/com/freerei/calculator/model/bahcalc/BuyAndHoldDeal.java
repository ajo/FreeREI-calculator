package com.freerei.calculator.model.bahcalc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BuyAndHoldDeal implements Serializable {

    private static final long serialVersionUID = 1L;

    private GeneralInfo generalInfo;

    private PurchaseInfo purchaseInfo;

    private ExpenseInfo expenseInfo;

    private IncomeInfo incomeInfo;

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public PurchaseInfo getPurchaseInfo() {
        return purchaseInfo;
    }

    public void setPurchaseInfo(PurchaseInfo purchaseInfo) {
        this.purchaseInfo = purchaseInfo;
    }

    public ExpenseInfo getExpenseInfo() {
        return expenseInfo;
    }

    public void setExpenseInfo(ExpenseInfo expenseInfo) {
        this.expenseInfo = expenseInfo;
    }

    public IncomeInfo getIncomeInfo() {
        return incomeInfo;
    }

    public void setIncomeInfo(IncomeInfo incomeInfo) {
        this.incomeInfo = incomeInfo;
    }

    public BigDecimal getIncomeAtMonth(int atMonth, boolean cumulative) {

        // For every month, add that months rent to the total
        BigDecimal rentTotal = getCompoundGrowth(atMonth, incomeInfo.getMonthlyRentIncome(), incomeInfo.getMonthlyRentIncomeIncrease(), cumulative);

        // Repeat the same calculation process as rent, but with the other income figures.
        BigDecimal otherTotal = getCompoundGrowth(atMonth, incomeInfo.getMonthlyOtherIncome(), incomeInfo.getMonthlyOtherIncomeIncrease(), cumulative);

        return rentTotal.add(otherTotal).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getFlatRateGrowth(int atMonth, BigDecimal rate, boolean cumulative) {

        BigDecimal total = BigDecimal.ZERO;

        for (int i = 1; i <= atMonth; i++) {
            if (cumulative) {
                total = total.add(this.getIncomeAtMonth(i, false).multiply(rate.divide(new BigDecimal("100"))));
            } else {
                total = this.getIncomeAtMonth(i, false).multiply(rate.divide(new BigDecimal("100")));
            }
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }

    // Compounding is assumed to be yearly.
    public BigDecimal getCompoundGrowth(int atMonth, BigDecimal principal, BigDecimal rate, boolean cumulative) {

        BigDecimal total = BigDecimal.ZERO;

        for (int i = 1; i <= atMonth; i++) {
            if (cumulative) {
                total = total.add(principal);
            } else {
                total = principal;
            }

            // Increase the principal every 12 months.
            if (i % 12 == 0) {
                principal = principal.multiply(BigDecimal.ONE.add(rate.divide(new BigDecimal("100"))));
            }
        }

        return total;
    }

    public BigDecimal getMortgagePayment() {
        if (purchaseInfo.isUsingLoan()) {

            BigDecimal interestRate = purchaseInfo.getInterestRate().divide(BigDecimal.valueOf(100.0));
            BigDecimal monthlyRate = interestRate.setScale(5).divide(BigDecimal.valueOf(12), RoundingMode.DOWN);

            /*
             * Loan amount (L)
             * Monthly interest rate (R)
             * Total payments made (n)
             * Formula: L[P(1 + R)^n]/[(1 + R)^n - 1]
             */
            return (purchaseInfo.getPurchasePrice().subtract(purchaseInfo.getDownPayment())).multiply((monthlyRate.multiply(((BigDecimal.ONE.add(monthlyRate)).pow(purchaseInfo.getLoanTerm() * 12)))))
                    .divide(((BigDecimal.ONE.add(monthlyRate)).pow(purchaseInfo.getLoanTerm() * 12).subtract(BigDecimal.ONE)), RoundingMode.DOWN)
                    .setScale(2, RoundingMode.DOWN);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getExpensesAtMonth(int atMonth, boolean cumulative) {

        BigDecimal total = BigDecimal.ZERO;

        // Subtract all expense sources
        total = total.subtract(getMortgagePayment().multiply(BigDecimal.valueOf(atMonth)));
        total = total.subtract(getFlatRateGrowth(atMonth, expenseInfo.getVacancyRateExpensePercentage(), cumulative));
        total = total.subtract(getFlatRateGrowth(atMonth, expenseInfo.getManagementExpensePercentage(), cumulative));
        total = total.subtract(getCompoundGrowth(atMonth, expenseInfo.getTaxExpense(), expenseInfo.getTaxExpenseIncrease(), cumulative));
        total = total.subtract(getCompoundGrowth(atMonth, expenseInfo.getInsuranceExpense(), expenseInfo.getInsuranceExpenseIncrease(), cumulative));
        total = total.subtract(getCompoundGrowth(atMonth, expenseInfo.getMaintenanceExpense(), expenseInfo.getMaintenanceExpenseIncrease(), cumulative));
        total = total.subtract(getCompoundGrowth(atMonth, expenseInfo.getUtilityExpense(), expenseInfo.getUtilityExpenseIncrease(), cumulative));
        total = total.subtract(getCompoundGrowth(atMonth, expenseInfo.getOtherExpense(), expenseInfo.getOtherExpenseIncrease(), cumulative));

        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public int getDealLength() {
        if (purchaseInfo.isUsingLoan()) {
            return purchaseInfo.getLoanTerm();
        } else {
            return purchaseInfo.getYearsHeld();
        }
    }
}