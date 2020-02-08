package se.kth.iv1201.recruitmentbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{
	
	public Optional<Status> findByName(String name);

}