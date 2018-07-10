package sunhill.data;

import java.io.Serializable;

public interface IBaseDAO<T extends Serializable> {

    T get(final Long identifier);

    T add(final T item);

    T update(final Long identifier, final T item);

    void delete(final Long identifier);
}
