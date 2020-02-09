package se.kth.iv1201.recruitmentbackend.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Application extends RepresentationModel<Application> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private long id;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@Version
	private long version;
	
	@ManyToOne
	@JoinColumn(name = "status_id", referencedColumnName = "status_id")
	private Status status;

	@OneToOne
	@JoinColumn(name = "person_id")
	@JsonIgnoreProperties(value = { "id", "password" }, allowSetters = true)
	private Person person;

	@JsonManagedReference
	@JsonIgnoreProperties(value = { "id" }, allowSetters = true)
	@EqualsAndHashCode.Exclude
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "application", orphanRemoval = true)
	private Set<Availability> availability = new HashSet<>();
	
	@JsonManagedReference
	@JsonIgnoreProperties(value = { "id" }, allowSetters = true)
	@EqualsAndHashCode.Exclude
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "application", orphanRemoval = true)
	private Set<CompetenceProfile> competenceProfile = new HashSet<>();

	public Application() {

	}

	public Application(Status status, Person person) {
		this.status = status;
		this.person = person;

	}

	public Application(Status status) {
		this.status = status;

	}
}
