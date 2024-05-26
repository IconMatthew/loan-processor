package ru.jadae.loanprocessor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.jadae.loanprocessor.entities.Client;
import ru.jadae.loanprocessor.service.ClientService;

import java.util.List;

@Controller
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private final ClientService clientService;

    @GetMapping("/main-page")
    public String mainPage() {
        return "main-page";
    }

    @GetMapping("/show-all-clients")
    public String showAllClients(Model model){
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("idData", "");
        model.addAttribute("phoneNumber", "");
        model.addAttribute("fullName", "");
        return "clients-table";
    }

    @GetMapping("/filterClients")
    public String showAllClientsWithFilters(@RequestParam(value = "idData", required = false) String idData,
                                            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                            @RequestParam(value = "fullName", required = false) String fullName,
                                            Model model){

        List<Client> clients = clientService.clientsByFilters(idData, phoneNumber, fullName);
        model.addAttribute("clients", clients);
        model.addAttribute("idData", idData);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("fullName", fullName);
        return "clients-table";
    }

    @GetMapping("/reset-filters")
    public String resetFilters(@RequestParam(value = "idData", required = false) String idData,
                                            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                            @RequestParam(value = "fullName", required = false) String fullName,
                                            Model model){

        model.addAttribute("idData", "");
        model.addAttribute("phoneNumber", "");
        model.addAttribute("fullName", "");
        return "redirect:/client/show-all-clients";
    }
}
