package ru.jadae.loanprocessor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.jadae.loanprocessor.entities.LoanContract;
import ru.jadae.loanprocessor.service.LoanContractService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("contract")
public class LoanContractController {

    private final LoanContractService loanContractService;
    @PostMapping("/signed")
    public String signContract(@RequestParam("loanContractId") String loanContractId) {
        LoanContract loanContract = loanContractService.getLoanContractById(Long.parseLong(loanContractId));
        if (loanContract.getLoanApplication().getStatus()) {
            loanContractService.setContractSigned(loanContract);
            return "loan-contract-signed";
        } else {
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/show-all-signed-contracts")
    public String showAllContracts(Model model){
        List<LoanContract> contracts = loanContractService.getAllSignedContracts();
        model.addAttribute("contracts", contracts);
        return "contracts-table";
    }
}
