package ru.jadae.loanprocessor.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.jadae.loanprocessor.entities.Client;
import ru.jadae.loanprocessor.entities.LoanApplication;
import ru.jadae.loanprocessor.entities.LoanContract;
import ru.jadae.loanprocessor.enums.FamilyStatus;
import ru.jadae.loanprocessor.service.ClientService;
import ru.jadae.loanprocessor.service.LoanApplicationService;
import ru.jadae.loanprocessor.service.LoanContractService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/loan-application")
@RequiredArgsConstructor
public class LoanApplicationController {

    @Autowired
    private final ClientService clientService;
    @Autowired
    private final LoanApplicationService loanApplicationService;
    @Autowired
    private final LoanContractService loanContractService;

    @GetMapping()
    public String loanApplication() {
        return "loan-application";
    }

    @PostMapping()
    public String makeApplication(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {
                if (error instanceof FieldError fieldError) {

                    if (fieldError.getField().equals("phoneNumber")) {
                        client.setPhoneNumber("");
                    } else if (fieldError.getField().equals("idData")) {
                        client.setIdData("");
                    }
                }
            }

            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());

            model.addAttribute("isSingle", client.getFamilyStatus() == FamilyStatus.SINGLE);
            model.addAttribute("isMarried", client.getFamilyStatus() == FamilyStatus.MARRIED);
            model.addAttribute("isDivorced", client.getFamilyStatus() == FamilyStatus.DIVORCED);
            model.addAttribute("errors", errors);
            model.addAttribute("client", client);
            return "loan-application";
        }

        clientService.applyLoan(client);
        LoanApplication loanApplication = loanApplicationService.createNewLoanApplication(client);

        if (loanApplication.getStatus()) {
            LoanContract loanContract = loanContractService.createNewLoanContract(loanApplication);
            model.addAttribute("loanContract", loanContract);
            model.addAttribute("loanApplication", loanApplication);
            model.addAttribute("client", client);
            return "loan-application-applied";
        } else {
            return "loan-application-rejected";
        }
    }


    @GetMapping("/show-all-applications")
    public String showAllApplications(Model model){
        List<LoanApplication> applications = loanApplicationService.getAllApplications();
        model.addAttribute("applications", applications);
        return "loan-applications-table";
    }
}
