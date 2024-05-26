package ru.jadae.loanprocessor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jadae.loanprocessor.dao.LoanApplicationDAO;
import ru.jadae.loanprocessor.entities.Client;
import ru.jadae.loanprocessor.entities.LoanApplication;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanApplicationService {
    @Autowired
    private final LoanApplicationDAO loanApplicationDAO;

    public LoanApplication createNewLoanApplication(Client client){
        Random random = new Random();

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setClient(client);
        loanApplication.setStatus(random.nextBoolean());

        if (loanApplication.getStatus()){
            loanApplication.setTerm(random.nextInt(1, 360));
            loanApplication.setApprovedAmount(client.getLoanAmount());

            loanApplicationDAO.saveApplication(loanApplication);
        }

        return loanApplicationDAO.saveApplication(loanApplication);
    }

    public List<LoanApplication> getAllApplications() {
        return loanApplicationDAO.getAllApplications();
    }
}
