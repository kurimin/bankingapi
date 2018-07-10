package sunhill.service;

import java.io.Serializable;

public interface IBaseAccountService<T extends Serializable> {
    T create(final Long clientId, final T account);

    T get(final Long clientId, final T account);
}
