package ru.jadae.loanprocessor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.jadae.loanprocessor.entities.Client;

import java.util.List;

@Repository
public class ClientDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Client save(Client client){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(client);
        session.getTransaction().commit();
        return client;
    }

    public List<Client> getAllClients() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select c from Client c", Client.class).getResultList();
    }

    public Client getByIdData(String idData) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select c from Client c where idData like :idData", Client.class)
                .setParameter("idData", idData).getSingleResult();
    }
}
