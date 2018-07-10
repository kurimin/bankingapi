package sunhill.service;

import sunhill.model.Account;
import sunhill.model.AccountBalance;
import sunhill.model.Transaction;

public interface IAccountService {

    /**
     * Create an account for a certain client
     *
     * @param clientId
     * @param account
     * @return account
     */
    Account create(
            final Long clientId,
            final Account account);

    /**
     * Get the account by a given accountId of a client
     *
     * @param clientId
     * @param accountId
     * @return account
     */
    Account get(
            final Long clientId,
            final Long accountId);

    /**
     * Deposit money in the account
     *
     * @param clientId
     * @param accountId
     * @param transaction
     * @return account
     */
    Account deposit(
            final Long clientId,
            final Long accountId,
            final Transaction transaction);

    /**
     * Withdraw money of the account
     *
     * @param clientId
     * @param accountId
     * @param transaction
     * @return account
     */
    Account withdraw(
            final Long clientId,
            final Long accountId,
            final Transaction transaction);


    /**
     * Get the account balance with its statements
     *
     * @param clientId
     * @param accountId
     * @return accountBalance
     */
    AccountBalance balance(
            final Long clientId,
            final Long accountId);

}

