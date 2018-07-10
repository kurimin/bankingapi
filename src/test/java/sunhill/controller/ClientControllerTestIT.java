package sunhill.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponentsBuilder;
import sunhill.BankingApi;
import sunhill.constants.RequestConstants;
import sunhill.constants.UriConstants;
import sunhill.data.IClientDAO;
import sunhill.data.test.ClientDataTest;
import sunhill.model.Client;

import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    private IClientDAO clientDAO;

    @Before
    public void initTestData() {
        ClientDataTest.initialize(this.clientDAO);
    }

    @After
    public void cleanTestData() {
        ClientDataTest.clean(this.clientDAO);
    }

    @Test
    public void GivenValidIdentifier_WhenCallGet_ThenMustReturnClient() throws Exception {
        //Given
        final Long clientId = ClientDataTest.ID_1;

        //When
        final ResultActions result = this.mvc.perform(get(createClientUri(clientId))
                .contentType(RequestConstants.CONTENT_TYPE_JSON));
        final String resultString = result.andReturn().getResponse().getContentAsString();

        final Client resultObject = this.parseClient(resultString);

        //Then
        result.andExpect(status().isOk());
        assertNotNull(resultObject);
        assertEquals(resultObject.getClientId(), clientId);
        assertEquals(resultObject.getClientName(), ClientDataTest.NAME_1);

    }

    private Client parseClient(String resultString) throws IOException {
        return new ObjectMapper().readValue(resultString, new TypeReference<Client>() {
        });
    }

    @Test
    public void GivenNonExistingIdentifier_WhenCallGet_ThenMustReturnNotFound() throws Exception {
        //Given
        final Long clientId = ClientDataTest.ID_UNEXISTING;

        //When
        final ResultActions result = this.mvc.perform(get(createClientUri(clientId))
                .contentType(RequestConstants.CONTENT_TYPE_JSON));

        //Then
        result.andExpect(status().isNotFound());

    }

    private URI createClientUri(final Long clientId) {
        return UriComponentsBuilder.newInstance()
                .path(UriConstants.CLIENT_URI)
                .buildAndExpand(clientId)
                .toUri();
    }
}
