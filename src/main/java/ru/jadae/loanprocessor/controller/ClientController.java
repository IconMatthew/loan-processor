package ru.jadae.loanprocessor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jadae.loanprocessor.entities.Client;
import ru.jadae.loanprocessor.entities.LoanApplication;
import ru.jadae.loanprocessor.entities.LoanContract;
import ru.jadae.loanprocessor.service.ClientService;
import ru.jadae.loanprocessor.service.LoanApplicationService;
import ru.jadae.loanprocessor.service.LoanContractService;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private final ClientService clientService;
    @Autowired
    private final LoanApplicationService loanApplicationService;
    @Autowired
    private final LoanContractService loanContractService;

    @GetMapping("/main-page")
    public String mainPage() {
        return "main-page";
    }

    @GetMapping("/loan-application")
    public String loanApplication() {
        return "loan-application";
    }

    @PostMapping("/loan-application")
    public String makeApplication(@ModelAttribute("client") Client client, Model model) {

        clientService.applyLoan(client);

        LoanApplication loanApplication = loanApplicationService.createNewLoanApplication(client);

        if (loanApplication.getStatus()){
            LoanContract loanContract = loanContractService.createNewLoanContract(loanApplication);
            model.addAttribute("loanContract", loanContract);
            model.addAttribute("loanApplication", loanApplication);
            model.addAttribute("client", client);
            return "loan-application-applied";
        }
        else {
            return "loan-application-rejected";
        }
    }

    @PostMapping("/loan-contract-signed")
    public String signContract(@RequestParam("loanContractId") String loanContractId) {
        LoanContract loanContract = loanContractService.getLoanContractById(Long.parseLong(loanContractId));
        if (loanContract.getLoanApplication().getStatus()) {
            loanContractService.setContractSigned(loanContract);
            return "loan-contract-signed";
        } else {
            throw new IllegalArgumentException();
        }
    }
}
