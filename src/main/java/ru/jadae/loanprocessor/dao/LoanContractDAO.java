package ru.jadae.loanprocessor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.jadae.loanprocessor.entities.LoanContract;

import java.util.List;

@Repository
public class LoanContractDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public LoanContract saveContract(LoanContract loanContract) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(loanContract);
        session.getTransaction().commit();
        return loanContract;
    }

    public LoanContract getByApplicationId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select l from LoanContract l where l.loanApplication.id = :id", LoanContract.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    public LoanContract getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select l from LoanContract l where l.id = :id", LoanContract.class)
                .setParameter("id", id).getSingleResult();
    }

    public List<LoanContract> getAllSignedContracts() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select l from LoanContract l where l.signatureStatus = true", LoanContract.class)
                .list();
    }
}
