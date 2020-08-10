package com.freerei.calculator.handlers;

import com.freerei.calculator.model.bahcalc.BuyAndHoldDeal;
import com.freerei.calculator.model.bahcalc.PurchaseInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BahCalcHandlerTest {

    BuyAndHoldDeal buyAndHoldDeal = new BuyAndHoldDeal();
    BahCalcHandler bahCalcHandler = new BahCalcHandler();
    PurchaseInfo purchaseInfo = new PurchaseInfo();

    public BahCalcHandlerTest() {
        bahCalcHandler.addPurchaseInfo(buyAndHoldDeal, purchaseInfo);
    }

    @Nested
    @DisplayName("Down Payment Validation")
    class DownPaymentValidation{

    @Test
    @DisplayName("Down payment is above purchase price")
    void DownPaymentAbovePurchase() {

        purchaseInfo.setUsingLoan(true);
        purchaseInfo.setDownPayment(BigDecimal.TEN);
        purchaseInfo.setPurchasePrice(BigDecimal.ONE);

        assertEquals(bahCalcHandler.validateDownPayment(purchaseInfo), false);
    }

    @Test
    @DisplayName("Down payment is below purchase price")
    void DownPaymentBelowPurchase() {

        purchaseInfo.setUsingLoan(true);
        purchaseInfo.setDownPayment(BigDecimal.ONE);
        purchaseInfo.setPurchasePrice(BigDecimal.TEN);

        assertEquals(bahCalcHandler.validateDownPayment(purchaseInfo), true);
    }
}

    @Nested
    @DisplayName("Term Validation")
    class TermValidation {

        @Test
        @DisplayName("Using a loan with a term of 0")
        void ZeroTermWithLoan() {

            purchaseInfo.setUsingLoan(true);
            purchaseInfo.setLoanTerm(0);

            assertEquals(bahCalcHandler.validateTerm(purchaseInfo), false);
        }

        @Test
        @DisplayName("Not using a loan with a term of 0")
        void ZeroTermWithoutLoan() {

            purchaseInfo.setUsingLoan(false);
            purchaseInfo.setYearsHeld(0);

            assertEquals(bahCalcHandler.validateTerm(purchaseInfo), false);
        }

        @Test
        @DisplayName("Using a loan with a term of 1")
        void ValidTermWithLoan() {

            purchaseInfo.setUsingLoan(true);
            purchaseInfo.setYearsHeld(0);
            purchaseInfo.setLoanTerm(1);

            assertEquals(bahCalcHandler.validateTerm(purchaseInfo), true);
        }

        @Test
        @DisplayName("Not using a loan with a term of 1")
        void ValidTermWithoutLoan() {

            purchaseInfo.setUsingLoan(false);
            purchaseInfo.setLoanTerm(0);
            purchaseInfo.setYearsHeld(1);

            assertEquals(bahCalcHandler.validateDownPayment(purchaseInfo), true);
        }
    }

    @Nested
    @DisplayName("Repair Value Validation")
    class RepairValueValidation{

        @Test
        @DisplayName("After repair value is below purchase price, but no repairs are being made")
        void RepairValueBelowPurchaseNoRepairs() {

            purchaseInfo.setNeedsRepairs(false);
            purchaseInfo.setPurchasePrice(BigDecimal.ONE);
            purchaseInfo.setRepairCost(BigDecimal.TEN);
            purchaseInfo.setAfterRepairValue(BigDecimal.ZERO);

            assertEquals(bahCalcHandler.validateRepairValue(purchaseInfo), true);

        }

        @Test
        @DisplayName("After repair value is below purchase price")
        void RepairValueBelowPurchase() {

            purchaseInfo.setNeedsRepairs(true);
            purchaseInfo.setPurchasePrice(BigDecimal.ONE);
            purchaseInfo.setRepairCost(BigDecimal.TEN);
            purchaseInfo.setAfterRepairValue(BigDecimal.ZERO);

            assertEquals(bahCalcHandler.validateRepairValue(purchaseInfo), false);

        }

        @Test
        @DisplayName("After repair value is equal to purchase price")
        void RepairValueEqualsPurchase() {

            purchaseInfo.setNeedsRepairs(true);
            purchaseInfo.setPurchasePrice(BigDecimal.ONE);
            purchaseInfo.setRepairCost(BigDecimal.ONE);
            purchaseInfo.setAfterRepairValue(BigDecimal.valueOf(2));

            assertEquals(bahCalcHandler.validateRepairValue(purchaseInfo), true);
        }

        @Test
        @DisplayName("After repair value is above purchase price")
        void RepairValueAbovePurchase() {

            purchaseInfo.setNeedsRepairs(true);
            purchaseInfo.setPurchasePrice(BigDecimal.ONE);
            purchaseInfo.setRepairCost(BigDecimal.ONE);
            purchaseInfo.setAfterRepairValue(BigDecimal.TEN);

            assertEquals(bahCalcHandler.validateRepairValue(purchaseInfo), true);

        }
    }

}