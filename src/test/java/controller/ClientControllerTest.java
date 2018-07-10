package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sunhill.BankingApi;
import sunhill.constants.RequestConstants;
import sunhill.constants.UriConstants;
import sunhill.controller.ClientController;
import sunhill.service.IClientService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BankingApi.class})
@WebMvcTest(value = ClientController.class, secure = false)
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IClientService clientService;

    @Test
    public void getClientNotFound() throws Exception {

        String url = String.format(UriConstants.CLIENT_URI, 6);
        this.mvc.perform(get(url)
                .contentType(RequestConstants.CONTENT_TYPE_JSON))
                .andExpect(status().isNotFound());

    }
}
