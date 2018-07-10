package sunhill.service;

import sunhill.model.Client;

public interface IClientService {

    /**
     * Get client
     *
     * @param clientId
     * @return client OR null if client is not found
     */
    Client get(final Long clientId);


    /**
     * Create client
     *
     * @param client
     * @return client
     */
    Client create(Client client);
}
