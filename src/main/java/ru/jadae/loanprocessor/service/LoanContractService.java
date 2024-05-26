package ru.jadae.loanprocessor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jadae.loanprocessor.dao.LoanContractDAO;
import ru.jadae.loanprocessor.entities.LoanApplication;
import ru.jadae.loanprocessor.entities.LoanContract;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanContractService {
    @Autowired
    private final LoanContractDAO loanContractDAO;

    public LoanContract createNewLoanContract(LoanApplication loanApplication) {

        LoanContract loanContract = new LoanContract();
        loanContract.setLoanApplication(loanApplication);
        return loanContractDAO.saveContract(loanContract);
    }

    public LoanContract setContractSigned(LoanContract loanContract){

        loanContract.setSignatureStatus(true);
        loanContract.setSignatureDate(new Date());
        return loanContractDAO.saveContract(loanContract);
    }

    public LoanContract getLoanContractById(Long id){

        return loanContractDAO.getById(id);
    }

    public List<LoanContract> getAllSignedContracts() {
        return loanContractDAO.getAllSignedContracts();
    }
}
