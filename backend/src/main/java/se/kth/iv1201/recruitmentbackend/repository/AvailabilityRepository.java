package se.kth.iv1201.recruitmentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

}
