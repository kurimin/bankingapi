package sunhill.model;

import java.io.Serializable;

public class Account implements Serializable {
    private Long accountId;
    private Long clientId;
    private Double money;

    public Account() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

}
