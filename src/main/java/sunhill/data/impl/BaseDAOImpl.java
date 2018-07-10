package sunhill.data.impl;

import sunhill.data.IBaseDAO;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseDAOImpl<T extends Serializable> implements IBaseDAO<T> {

    private Map<Long, T> data = new ConcurrentHashMap<>();
    private Long currentIdentifier = 0L;

    public T get(final Long identifier) {
        return this.data.get(identifier);
    }

    public T add(final T item) {
        final Long identifier = this.getNextIdentifier();
        this.setIdentifier(identifier, item);
        return this.data.put(identifier, item);
    }

    public T update(final Long identifier, final T item) {
        this.data.put(identifier, item);
        return item;
    }

    public void delete(final Long identifier) {
        this.data.remove(identifier);
    }

    abstract void setIdentifier(final Long identifier, final T item);

    private Long getNextIdentifier() {
        this.currentIdentifier++;
        return this.currentIdentifier;
    }

}
