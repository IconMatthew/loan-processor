package ru.jadae.loanprocessor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.jadae.loanprocessor.controller.ClientController;
import ru.jadae.loanprocessor.entities.Client;
import ru.jadae.loanprocessor.enums.FamilyStatus;
import ru.jadae.loanprocessor.service.ClientService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private final Client client1 = Mockito.mock(Client.class);
    private final Client client2 = Mockito.mock(Client.class);
    @Test
    void testShowAllClients() throws Exception {
        List<Client> clients = new ArrayList<>();

        clients.add(client1);

        when(client1.getFullName()).thenReturn("Name1");
        when(client1.getIdData()).thenReturn("1287 768433");
        when(client1.getFamilyStatus()).thenReturn(FamilyStatus.SINGLE);
        when(client1.getLoanAmount()).thenReturn(BigDecimal.valueOf(100000));
        when(client1.getAddress()).thenReturn("Homeless");
        when(client1.getPhoneNumber()).thenReturn("+79034585531");
        when(client1.getWorkingExperience()).thenReturn("None");
        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(MockMvcRequestBuilders.get("/client/show-all-clients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("clients"))
                .andExpect(MockMvcResultMatchers.view().name("clients-table"));
    }

    @Test
    void testShowAllClientsWithFilters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/client/filterClients")
                        .param("idData", "1234 777777")
                        .param("phoneNumber", "+55512341231")
                        .param("fullName", "Жадаев Матвей Юрьевич"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("clients"))
                .andExpect(MockMvcResultMatchers.model().attribute("idData", "1234 777777"))
                .andExpect(MockMvcResultMatchers.model().attribute("phoneNumber", "+55512341231"))
                .andExpect(MockMvcResultMatchers.model().attribute("fullName", "Жадаев Матвей Юрьевич"))
                .andExpect(MockMvcResultMatchers.view().name("clients-table"));
    }

}
