package sunhill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sunhill.constants.RequestConstants;
import sunhill.constants.UriConstants;
import sunhill.model.Client;
import sunhill.service.IClientService;

@RestController
@RequestMapping(produces = RequestConstants.CONTENT_TYPE_JSON)
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private IClientService clientService;


    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = UriConstants.CLIENT_URI_ROOT, method = RequestMethod.POST)
    public Client createClient(
            @RequestBody final Client client) {
        final Client result = this.clientService.create(client);
        return result;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = UriConstants.CLIENT_URI, method = RequestMethod.GET)
    public Client getClient(
            @PathVariable(value = UriConstants.CLIENT_PATHVAR) final Long clientId) {
        final Client result = this.clientService.get(clientId);
        return result;
    }

}
