package ru.jadae.loanprocessor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.jadae.loanprocessor.entities.Client;
import ru.jadae.loanprocessor.entities.LoanApplication;

import java.math.BigDecimal;

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
}
