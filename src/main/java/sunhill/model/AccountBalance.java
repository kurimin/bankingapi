package sunhill.model;

import java.util.List;

public class AccountBalance {
    private Double accountBalance;
    private List<Transaction> transactions;

    public AccountBalance() {
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
