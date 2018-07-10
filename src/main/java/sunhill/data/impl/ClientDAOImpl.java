package sunhill.data.impl;

import org.springframework.stereotype.Component;
import sunhill.data.IClientDAO;
import sunhill.model.Client;

@Component
public class ClientDAOImpl extends BaseDAOImpl<Client> implements IClientDAO {

    @Override
    void setIdentifier(Long identifier, Client item) {
        item.setClientId(identifier);
    }
}
