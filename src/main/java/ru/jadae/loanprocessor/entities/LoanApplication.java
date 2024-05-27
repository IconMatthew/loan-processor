package ru.jadae.loanprocessor.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Table(name = "loanApplication")
@Entity
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name="status")
    private Boolean status;

    @Column(name="term")
    @Min(value = 1)
    @Max(value = 360)
    private Integer term;

    @Column(name = "approvedAmount", precision = 19, scale = 2)
    private BigDecimal approvedAmount;

    @OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    private LoanContract loanContract;
}
