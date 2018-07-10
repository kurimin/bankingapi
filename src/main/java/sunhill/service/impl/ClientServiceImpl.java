package sunhill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sunhill.data.IClientDAO;
import sunhill.exception.BankingApiException;
import sunhill.model.Client;
import sunhill.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientDAO clientDAO;

    @Override
    public Client get(final Long clientId) {
        this.getPreconditions(clientId);

        final Client resultClient = this.clientDAO.get(clientId);

        this.getPostConditions(clientId, resultClient);

        return resultClient;
    }

    @Override
    public Client create(Client client) {
        this.createPreConditions(client);

        this.clientDAO.add(client);

        return client;
    }


    private void getPreconditions(final Long clientId) {
        if (clientId == null)
            throw new BankingApiException(HttpStatus.BAD_REQUEST, "Client ID cannot be null");
    }

    private void getPostConditions(Long clientId, final Client client) {
        if (client == null)
            throw new BankingApiException(HttpStatus.NOT_FOUND, "Client not found: " + String.valueOf(clientId));
    }

    private void createPreConditions(Client client) {
        if (client == null)
            throw new BankingApiException(HttpStatus.BAD_REQUEST, "Client Object cannot be null");

        if (client.getClientName() == null || "".equals(client.getClientName().trim()))
            throw new BankingApiException(HttpStatus.BAD_REQUEST, "Client name is mandatory.");
    }
}
