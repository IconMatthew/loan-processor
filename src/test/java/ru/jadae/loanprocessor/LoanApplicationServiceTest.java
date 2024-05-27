package ru.jadae.loanprocessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.jadae.loanprocessor.dao.LoanApplicationDAO;
import ru.jadae.loanprocessor.entities.Client;
import ru.jadae.loanprocessor.entities.LoanApplication;
import ru.jadae.loanprocessor.service.LoanApplicationService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanApplicationServiceTest {

    @Mock
    private LoanApplicationDAO loanApplicationDAO;

    @InjectMocks
    private LoanApplicationService loanApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNewLoanApplication() {
        Client client = new Client();
        client.setLoanAmount(new BigDecimal("10000"));

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setClient(client);
        loanApplication.setStatus(true);
        loanApplication.setTerm(12);
        loanApplication.setApprovedAmount(client.getLoanAmount());

        when(loanApplicationDAO.saveApplication(any(LoanApplication.class))).thenReturn(loanApplication);

        LoanApplication result = loanApplicationService.createNewLoanApplication(client);

        assertNotNull(result);
        assertEquals(client, result.getClient());
        assertTrue(result.getStatus());

        verify(loanApplicationDAO, times(1)).saveApplication(any(LoanApplication.class));
    }

    @Test
    void testGetAllApplications() {
        LoanApplication loanApplication1 = new LoanApplication();
        LoanApplication loanApplication2 = new LoanApplication();
        List<LoanApplication> loanApplications = List.of(loanApplication1, loanApplication2);

        when(loanApplicationDAO.getAllApplications()).thenReturn(loanApplications);

        List<LoanApplication> result = loanApplicationService.getAllApplications();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(loanApplicationDAO, times(1)).getAllApplications();
    }
}
