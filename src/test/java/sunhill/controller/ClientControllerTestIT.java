package sunhill.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;
import sunhill.BankingApi;
import sunhill.constants.RequestConstants;
import sunhill.constants.UriConstants;
import sunhill.service.IClientService;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = BankingApi.class)
@AutoConfigureMockMvc
public class ClientControllerTestIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private IClientService clientService;

    @Test
    public void getClientNotFound() throws Exception {
        this.mvc.perform(get(createClientUri(4L))
                .contentType(RequestConstants.CONTENT_TYPE_JSON))
                .andExpect(status().isNotFound());

    }

    private URI createClientUri(final Long clientId) {
        return UriComponentsBuilder.newInstance()
                .path(UriConstants.CLIENT_URI)
                .buildAndExpand(clientId)
                .toUri();
    }
}
