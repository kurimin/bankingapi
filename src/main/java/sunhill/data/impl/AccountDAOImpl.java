package sunhill.data.impl;

import org.springframework.stereotype.Component;
import sunhill.data.IAccountDAO;
import sunhill.model.Account;

@Component
public class AccountDAOImpl extends BaseDAOImpl<Account> implements IAccountDAO {

    @Override
    void setIdentifier(Long identifier, Account item) {
        item.setAccountId(identifier);
    }
}

