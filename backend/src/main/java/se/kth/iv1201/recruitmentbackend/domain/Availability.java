package se.kth.iv1201.recruitmentbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Data

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Temporal(TemporalType.DATE)
    @Column(name = "from_date")
    private Calendar fromDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "to_date")
    private Calendar toDate;

    public Availability(){

    };
}
