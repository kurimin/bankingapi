package sunhill.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import sunhill.data.IClientDAO;
import sunhill.data.test.ClientDataTest;
import sunhill.exception.BankingApiException;
import sunhill.model.Client;
import sunhill.service.IClientService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplTestIT {

    @Autowired
    private IClientDAO clientDAO;

    @Autowired
    private IClientService clientService;

    @Before
    public void initTestData() {
        ClientDataTest.initialize(this.clientDAO);
    }

    @After
    public void cleanTestData() {
        ClientDataTest.clean(this.clientDAO);
    }

    @Test
    public void GivenValidIdentifier_WhenCallGet_ThenMustReturnClient() {
        // Given
        final Long clientId = ClientDataTest.ID_1;

        // When
        final Client result = this.clientService.get(clientId);

        // Then
        assertNotNull(result);
        assertEquals(result.getClientId(), clientId);
        assertEquals(result.getClientName(), ClientDataTest.NAME_1);
    }

    @Test
    public void GivenNonExistingIdentifier_WhenCallGet_ThenMustThrowNotFoundException() {
        // Given
        final Long clientId = ClientDataTest.ID_UNEXISTING;

        // When
        try {
            this.clientService.get(clientId);
            fail();
        } catch (BankingApiException ex) {
            // Then
            assertEquals(ex.getErrorStatus(), HttpStatus.NOT_FOUND);
        }
    }

}