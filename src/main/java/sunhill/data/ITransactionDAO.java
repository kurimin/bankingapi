package sunhill.data;

import sunhill.model.Transaction;

import java.util.List;

public interface ITransactionDAO extends IBaseDAO<Transaction> {

    /**
     * Get all transactions of an account.
     *
     * @param accountId
     * @return
     */
    List<Transaction> list(final Long accountId);
}
