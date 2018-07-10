package sunhill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sunhill.data.IAccountDAO;
import sunhill.data.ITransactionDAO;
import sunhill.exception.BankingApiException;
import sunhill.model.Account;
import sunhill.model.AccountBalance;
import sunhill.model.Transaction;
import sunhill.model.TransactionType;
import sunhill.service.IAccountService;
import sunhill.service.IClientService;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IClientService clientService;

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private ITransactionDAO transactionDAO;

    @Override
    public Account create(final Long clientId, final Account account) {
        this.createPreconditions(clientId, account);
        account.setClientId(clientId);

        this.accountDAO.add(account);

        return account;
    }

    @Override
    public Account get(final Long clientId, final Long accountId) {
        this.getPreconditions(clientId, accountId);

        final Account account = this.accountDAO.get(accountId);

        this.getPostConditions(clientId, accountId, account);

        return account;
    }

    @Override
    public Account deposit(Long clientId, Long accountId, Transaction transaction) {

        this.transactionPreconditions(clientId, accountId, transaction);
        final Account account = this.accountDAO.get(accountId);
        this.getPostConditions(clientId, accountId, account);

        transaction.setAccountId(accountId);
        transaction.setDate(new Date());
        transaction.setTransactionType(TransactionType.DEPOSIT);
        this.transactionDAO.add(transaction);
        account.setMoney(account.getMoney() + transaction.getMoney());

        return account;
    }

    @Override
    public Account withdraw(Long clientId, Long accountId, Transaction transaction) {
        this.transactionPreconditions(clientId, accountId, transaction);
        final Account account = this.accountDAO.get(accountId);
        this.getPostConditions(clientId, accountId, account);

        transaction.setAccountId(accountId);
        transaction.setDate(new Date());
        transaction.setTransactionType(TransactionType.WITHDRAW);

        if (transaction.getMoney() > account.getMoney()) {
            throw new BankingApiException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }
        this.transactionDAO.add(transaction);

        account.setMoney(account.getMoney() - transaction.getMoney());

        return account;
    }

    @Override
    public AccountBalance balance(Long clientId, Long accountId) {
        this.getPreconditions(clientId, accountId);

        final Account account = this.accountDAO.get(accountId);

        this.getPostConditions(clientId, accountId, account);

        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setAccountBalance(account.getMoney());
        accountBalance.setTransactions(this.transactionDAO.list(accountId));

        return accountBalance;
    }

    private void getPreconditions(final Long clientId, final Long accountId) {
        this.clientService.get(clientId);

        if (accountId == null)
            throw new BankingApiException(HttpStatus.BAD_REQUEST, "Account ID cannot be null");
    }

    private void getPostConditions(Long clientId, Long accountId, Account account) {
        if (account == null)
            throw new BankingApiException(HttpStatus.NOT_FOUND, "Account not found: " + String.valueOf(accountId));

        if (!account.getClientId().equals(clientId))
            throw new BankingApiException(HttpStatus.CONFLICT, "Account has another client ID");
    }

    private void createPreconditions(final Long clientId, final Account account) {
        this.clientService.get(clientId);

        if (account.getMoney() == null)
            throw new BankingApiException(HttpStatus.BAD_REQUEST, "Account money is mandatory.");
    }

    private void transactionPreconditions(Long clientId, Long accountId, Transaction transaction) {
        this.getPreconditions(clientId, accountId);

        if (transaction.getMoney() == null)
            throw new BankingApiException(HttpStatus.BAD_REQUEST, "Money's transaction is mandatory.");

        if (transaction.getMoney() < 0)
            throw new BankingApiException(HttpStatus.BAD_REQUEST, "Money's transaction is not negative.");
    }

}
