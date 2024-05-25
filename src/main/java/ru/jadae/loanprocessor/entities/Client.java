package ru.jadae.loanprocessor.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import ru.jadae.loanprocessor.enums.Gender;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "client")
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="fullName", length = 150, nullable = false)
    private String fullName;

    @Column(name="idData", unique = true, nullable = false)
    private String idData;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "workingExperience")
    private String workingExperience;

    @Column(name = "loanAmount", precision = 19, scale = 2)
    private BigDecimal loanAmount;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<LoanApplication> clientApplications = new ArrayList<>();
}
