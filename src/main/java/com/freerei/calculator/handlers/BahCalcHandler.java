package com.freerei.calculator.handlers;

import com.freerei.calculator.model.bahcalc.*;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

@Component
public class BahCalcHandler {

    public BuyAndHoldDeal init() {
        return new BuyAndHoldDeal();
    }

    public void addGeneralInfo(BuyAndHoldDeal buyAndHoldDeal, GeneralInfo generalInfo) {
        buyAndHoldDeal.setGeneralInfo(generalInfo);
    }

    public void addPurchaseInfo(BuyAndHoldDeal buyAndHoldDeal, PurchaseInfo purchaseInfo) {
        buyAndHoldDeal.setPurchaseInfo(purchaseInfo);
    }

    public void addExpenseInfo(BuyAndHoldDeal buyAndHoldDeal, ExpenseInfo expenseInfo) {
        buyAndHoldDeal.setExpenseInfo(expenseInfo);
    }

    public void addIncomeInfo(BuyAndHoldDeal buyAndHoldDeal, IncomeInfo incomeInfo) {
        buyAndHoldDeal.setIncomeInfo(incomeInfo);
    }

    public String validatePurchaseInfo(PurchaseInfo purchaseInfo, MessageContext error) {

        // Down payment may not be greater than the purchase price
        if (purchaseInfo.isUsingLoan() && purchaseInfo.getDownPayment().compareTo(purchaseInfo.getPurchasePrice()) > 0) {
            error.addMessage(new MessageBuilder().
                    error()
                    .source("downPayment")
                    .defaultText("Down payment may not be greater than the purchase price.")
                    .build());

            return "failure";
        }

        // If using a loan the term must be greater than 0, if not holding length must be greater than 0
        if (purchaseInfo.isUsingLoan() && purchaseInfo.getLoanTerm() < 1) {
            error.addMessage(new MessageBuilder().
                    error()
                    .source("loanTerm")
                    .defaultText("Your loan may not be for 0 years")
                    .build());
            return "failure";
        } else if (!purchaseInfo.isUsingLoan() && purchaseInfo.getYearsHeld() < 1) {
            error.addMessage(new MessageBuilder().
                    error()
                    .source("yearsHeld")
                    .defaultText("Your years held may not be for 0 years")
                    .build());
            return "failure";
        }

        // After repair value may not be less than the purchase price.
        if (purchaseInfo.isNeedsRepairs() && (purchaseInfo.getPurchasePrice().compareTo(purchaseInfo.getAfterRepairValue()) > 0)) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("afterRepairValue")
                    .defaultText("After repair value may not be less than the purchase price.")
                    .build());

            return "failure";
        }
        return "success";
    }
}
