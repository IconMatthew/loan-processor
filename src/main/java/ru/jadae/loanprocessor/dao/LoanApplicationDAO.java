package ru.jadae.loanprocessor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.jadae.loanprocessor.entities.LoanApplication;

import java.util.List;

@Repository
public class LoanApplicationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public LoanApplication saveApplication(LoanApplication loanApplication){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(loanApplication);
        session.getTransaction().commit();
        return loanApplication;
    }

    public List<LoanApplication> getAllApplications() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select l from LoanApplication l", LoanApplication.class).list();
    }
}
