package ru.jadae.loanprocessor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jadae.loanprocessor.dao.ClientDAO;
import ru.jadae.loanprocessor.entities.Client;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private final ClientDAO clientDAO;

    public void applyLoan(Client client){
        clientDAO.save(client);
    }

    public Client getClientByIdData(String idData){
        return clientDAO.getByIdData(idData);
    }

    public List<Client> getAllClients(){
        return clientDAO.getAllClients();
    }

    public List<Client> clientsByFilters(String idData, String phoneNumber, String fullName) {
        return clientDAO.findByFilters(idData, phoneNumber, fullName);
    }
}
