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

    public List<Client> findByFilters(String idData, String phoneNumber, String fullName) {
        StringBuilder hql = new StringBuilder("FROM Client WHERE 1=1");
        if (idData != null && !idData.isEmpty()) {
            hql.append(" AND idData = :idData");
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            hql.append(" AND phoneNumber = :phoneNumber");
        }
        if (fullName != null && !fullName.isEmpty()) {
            hql.append(" AND fullName = :fullName");
        }

        Session session = sessionFactory.getCurrentSession();

        var query = session.createQuery(hql.toString(), Client.class);

        if (idData != null && !idData.isEmpty()) {
            query.setParameter("idData", idData);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            query.setParameter("phoneNumber", phoneNumber);
        }
        if (fullName != null && !fullName.isEmpty()) {
            query.setParameter("fullName", fullName);
        }

        return query.list();
    }
}
