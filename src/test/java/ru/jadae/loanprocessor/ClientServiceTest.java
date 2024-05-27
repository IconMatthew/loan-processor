package ru.jadae.loanprocessor;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.jadae.loanprocessor.dao.ClientDAO;
import ru.jadae.loanprocessor.entities.Client;
import ru.jadae.loanprocessor.service.ClientService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    private ClientDAO clientDAO;

    @InjectMocks
    private ClientService clientService;

    @Test
    void testApplyLoan() {
        Client client = new Client();
        clientService.applyLoan(client);
        verify(clientDAO, times(1)).save(client);
    }

    @Test
    void testGetClientByIdData() {
        String idData = "1234 567890";
        Client expectedClient = new Client();
        when(clientDAO.getByIdData(idData)).thenReturn(expectedClient);

        Client result = clientService.getClientByIdData(idData);

        assertEquals(expectedClient, result);
        verify(clientDAO, times(1)).getByIdData(idData);
    }

    @Test
    void testGetAllClients() {
        List<Client> expectedClients = Collections.singletonList(new Client());
        when(clientDAO.getAllClients()).thenReturn(expectedClients);

        List<Client> result = clientService.getAllClients();

        assertEquals(expectedClients, result);
        verify(clientDAO, times(1)).getAllClients();
    }

    @Test
    void testClientsByFilters() {
        String idData = "1234 567890";
        String phoneNumber = "+1234567890";
        String fullName = "Евгений Добрынин";
        List<Client> expectedClients = Collections.singletonList(new Client());
        when(clientDAO.findByFilters(idData, phoneNumber, fullName)).thenReturn(expectedClients);

        List<Client> result = clientService.clientsByFilters(idData, phoneNumber, fullName);

        assertEquals(expectedClients, result);
        verify(clientDAO, times(1)).findByFilters(idData, phoneNumber, fullName);
    }
}
