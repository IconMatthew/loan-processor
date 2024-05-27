package ru.jadae.loanprocessor;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.jadae.loanprocessor.dao.LoanContractDAO;
import ru.jadae.loanprocessor.entities.LoanApplication;
import ru.jadae.loanprocessor.entities.LoanContract;
import ru.jadae.loanprocessor.service.LoanContractService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoanContractServiceTest {

    @Mock
    private LoanContractDAO loanContractDAO;

    @InjectMocks
    private LoanContractService loanContractService;

    @Test
    void testCreateNewLoanContract() {
        LoanApplication loanApplication = new LoanApplication();
        LoanContract loanContract = new LoanContract();
        when(loanContractDAO.saveContract(any(LoanContract.class))).thenReturn(loanContract);

        LoanContract result = loanContractService.createNewLoanContract(loanApplication);

        assertEquals(loanContract, result);
        verify(loanContractDAO, times(1)).saveContract(any(LoanContract.class));
    }

    @Test
    void testSetContractSigned() {
        LoanContract loanContract = new LoanContract();
        loanContract.setSignatureStatus(false);
        loanContract.setSignatureDate(null);

        loanContractService.setContractSigned(loanContract);

        assertEquals(true, loanContract.getSignatureStatus());
        assertEquals(new Date().getDate(), loanContract.getSignatureDate().getDate());
        verify(loanContractDAO, times(1)).saveContract(loanContract);
    }

    @Test
    void testGetLoanContractById() {
        long id = 1L;
        LoanContract loanContract = new LoanContract();
        when(loanContractDAO.getById(id)).thenReturn(loanContract);

        LoanContract result = loanContractService.getLoanContractById(id);

        assertEquals(loanContract, result);
        verify(loanContractDAO, times(1)).getById(id);
    }

    @Test
    void testGetAllSignedContracts() {
        List<LoanContract> expectedContracts = Collections.singletonList(new LoanContract());
        when(loanContractDAO.getAllSignedContracts()).thenReturn(expectedContracts);

        List<LoanContract> result = loanContractService.getAllSignedContracts();

        assertEquals(expectedContracts, result);
        verify(loanContractDAO, times(1)).getAllSignedContracts();
    }
}
