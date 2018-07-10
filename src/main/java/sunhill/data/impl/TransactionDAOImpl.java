package sunhill.data.impl;

import org.springframework.stereotype.Component;
import sunhill.data.ITransactionDAO;
import sunhill.model.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransactionDAOImpl extends BaseDAOImpl<Transaction> implements ITransactionDAO {
    private Map<Long, List<Transaction>> transactions = new ConcurrentHashMap<>();

    @Override
    void setIdentifier(Long identifier, Transaction item) {
        item.setTransactionId(identifier);
    }

    @Override
    public Transaction add(Transaction item) {

        super.add(item);

        final Long accountId = item.getAccountId();
        if (this.transactions.containsKey(accountId)) {
            this.transactions.get(accountId).add(item);
        } else {
            this.transactions.put(item.getAccountId(), new ArrayList<>(Arrays.asList(item)));
        }

        return item;
    }

    @Override
    public Transaction update(Long identifier, Transaction item) {
        super.update(identifier, item);

        final Long accountId = item.getAccountId();
        this.transactions.get(accountId).remove(item);
        this.transactions.get(accountId).add(item);

        return item;
    }

    public List<Transaction> list(final Long accountId) {
        return this.transactions.get(accountId);
    }
}
