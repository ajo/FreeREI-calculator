package com.freerei.calculator.model.bahcalc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyAndHoldDealTest {

    private static final BuyAndHoldDeal buyAndHoldDeal = new BuyAndHoldDeal();

    @BeforeAll
    static void setup() {

        GeneralInfo generalInfo = new GeneralInfo();
        generalInfo.setTitle("Test Report");
        generalInfo.setAddress("1234 Test Street Roanoke VA, 24014");

        // "Default" assumes a loan, tests will need to setup a cash-only, or repairs included scenario.
        PurchaseInfo purchaseInfo = new PurchaseInfo();

        // 120,000 @ 4.6% for 30 years, 20,000 down, 3000 in repairs. After repair value increases by 5000.
        purchaseInfo.setPurchasePrice(BigDecimal.valueOf(120000));
        purchaseInfo.setUsingLoan(true);
        purchaseInfo.setDownPayment(BigDecimal.valueOf(20000));
        purchaseInfo.setInterestRate(BigDecimal.valueOf(4.6));
        purchaseInfo.setLoanTerm(30);
        purchaseInfo.setNeedsRepairs(true);
        purchaseInfo.setRepairCost(BigDecimal.valueOf(3000));
        purchaseInfo.setAfterRepairValue(BigDecimal.valueOf(125000));


        ExpenseInfo expenseInfo = new ExpenseInfo();

        // Tax
        expenseInfo.setTaxExpense(BigDecimal.valueOf(102));
        expenseInfo.setTaxExpenseIncrease(BigDecimal.valueOf(2));

        // Insurance
        expenseInfo.setInsuranceExpense(BigDecimal.valueOf(30));
        expenseInfo.setInsuranceExpenseIncrease(BigDecimal.ONE);

        // Maintenance
        expenseInfo.setMaintenanceExpense(BigDecimal.valueOf(50));
        expenseInfo.setMaintenanceExpenseIncrease(BigDecimal.valueOf(3));

        // Utilities
        expenseInfo.setUtilityExpense(BigDecimal.valueOf(70));
        expenseInfo.setUtilityExpenseIncrease(BigDecimal.ONE);

        // Other
        expenseInfo.setOtherExpense(BigDecimal.valueOf(10));
        expenseInfo.setOtherExpenseIncrease(BigDecimal.valueOf(5));

        // Property Management
        expenseInfo.setManagementExpensePercentage(BigDecimal.valueOf(8));

        // Vacancy
        expenseInfo.setVacancyRateExpensePercentage(BigDecimal.valueOf(5));


        IncomeInfo incomeInfo = new IncomeInfo();

        // Rent
        incomeInfo.setMonthlyRentIncome(BigDecimal.valueOf(1200));
        incomeInfo.setMonthlyRentIncomeIncrease(BigDecimal.valueOf(2));

        // Other
        incomeInfo.setMonthlyOtherIncome(BigDecimal.valueOf(20));
        incomeInfo.setMonthlyOtherIncomeIncrease(BigDecimal.ONE);

        buyAndHoldDeal.setGeneralInfo(generalInfo);
        buyAndHoldDeal.setPurchaseInfo(purchaseInfo);
        buyAndHoldDeal.setExpenseInfo(expenseInfo);
        buyAndHoldDeal.setIncomeInfo(incomeInfo);
    }

    @Test
    @DisplayName("First months income")
    void getIncomeAtFirstMonth() {
        assertEquals(buyAndHoldDeal.getIncomeAtMonth(1, false), BigDecimal.valueOf(1220.00).setScale(2));
    }

    @Test
    @DisplayName("Last months income")
    void getIncomeAtLastMonth() {
        assertEquals(buyAndHoldDeal.getIncomeAtMonth(buyAndHoldDeal.getDealLength() * 12, true), BigDecimal.valueOf(592528.71).setScale(2));
    }

    @Test
    @DisplayName("First months flat rate expense")
    void getFlatRateGrowthFirstMonth() {

        /*
         *   Should return the specified percentage of total income, as flat rate expenses are based off of income in the calculator.
         *   Ex - property management fee or vacancy expense
         */
        assertEquals(buyAndHoldDeal.getFlatRateGrowth(1, BigDecimal.ONE, false), BigDecimal.valueOf(12.20).setScale(2));
    }

    @Test
    @DisplayName("Last months flat rate expense")
    void getFlatRateGrowthLastMonth() {

        /*
         *   Should return the sum of the specified percentage of total income for the specified years, compounding yearly
         *   as flat rate expenses are based off of income in the calculator. Ex - property management fee or vacancy expense
         */
        assertEquals(buyAndHoldDeal.getFlatRateGrowth(buyAndHoldDeal.getDealLength() * 12, BigDecimal.ONE, true), BigDecimal.valueOf(5925.29));
    }

    @Test
    @DisplayName("Compound growth formula")
    void getCompoundGrowth() {
        assertEquals(buyAndHoldDeal.getCompoundGrowth(13, BigDecimal.valueOf(1000), BigDecimal.valueOf(10), false), BigDecimal.valueOf(1100.0) );
    }

    @Test
    @DisplayName("Mortgage calculation")
    void getMortgagePaymentWithLoan() {
        assertEquals(buyAndHoldDeal.getMortgagePayment(), BigDecimal.valueOf(512.40).setScale(2));
    }

    @Test
    @DisplayName("Mortgage is 0 if no mortgage is taken")
    void getMortgagePaymentNoLoan() {

        // Set to no loan
        buyAndHoldDeal.getPurchaseInfo().setUsingLoan(false);

        assertEquals(buyAndHoldDeal.getMortgagePayment(), BigDecimal.ZERO);

        // Switch back to using a loan
        buyAndHoldDeal.getPurchaseInfo().setUsingLoan(true);
    }

    @Test
    @DisplayName("First months total expenses")
    void getExpensesAtFirstMonth() {
        assertEquals(buyAndHoldDeal.getExpensesAtMonth(1, false), BigDecimal.valueOf(-933.00).setScale(2));
    }

    @Test
    @DisplayName("Last months total expenses")
    void getExpensesAtLastMonth() {
        assertEquals(buyAndHoldDeal.getExpensesAtMonth(buyAndHoldDeal.getDealLength() * 12, true), BigDecimal.valueOf(-389407.87).setScale(2));
    }

    @Test
    @DisplayName("Deal length without a loan (holding length)")
    void getDealLengthNoLoan() {

        // Set to no loan
        buyAndHoldDeal.getPurchaseInfo().setUsingLoan(false);

        assertEquals(buyAndHoldDeal.getDealLength(), buyAndHoldDeal.getPurchaseInfo().getYearsHeld());

        // Switch back to using a loan
        buyAndHoldDeal.getPurchaseInfo().setUsingLoan(true);
    }

    @Test
    @DisplayName("Deal length with a loan (mortgage term)")
    void getDealLengthWithLoan() {

        assertEquals(buyAndHoldDeal.getDealLength(), buyAndHoldDeal.getPurchaseInfo().getLoanTerm());

    }
}