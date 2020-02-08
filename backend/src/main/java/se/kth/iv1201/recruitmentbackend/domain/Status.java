package se.kth.iv1201.recruitmentbackend.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Status {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private long id;
	
	@NotNull(message = "{Status.name.missing}")
    @NotBlank(message = "{Status.name.blank}")
    @Column(unique=true)
    private String name;
	
	public Status() {
    	
    }
    public Status(String name) {
		this.name= name;
	}
}
