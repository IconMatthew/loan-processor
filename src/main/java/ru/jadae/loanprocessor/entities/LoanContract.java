package ru.jadae.loanprocessor.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Table(name = "loanContract")
@Entity
public class LoanContract {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_application_id", nullable = false)
    @ToString.Exclude
    private LoanApplication loanApplication;

    @Column(name = "signatureDate")
    private Date signatureDate;

    @Column(name = "signature_status")
    private Boolean signatureStatus;
}
