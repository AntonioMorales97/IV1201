package se.kth.iv1201.recruitmentbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.Status;
/**
 * JPA interface used to store and access application status data in the database
 */
public interface StatusRepository extends JpaRepository<Status, Long>{
	
	public Optional<Status> findByName(String name);

}