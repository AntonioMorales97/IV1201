package se.kth.iv1201.recruitmentbackend.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Data
@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    @ToString.Exclude
    @JsonIgnore
    private Application application;

    @Temporal(TemporalType.DATE)
    @Column(name = "from_date")
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "to_date")
    private Date toDate;
    
    
    public Availability(Application application, Date from, Date to) {
    	this.application= application;
    	this.fromDate= from;
    	this.toDate=to;
    	
    }
    public Availability() {}
    
}
