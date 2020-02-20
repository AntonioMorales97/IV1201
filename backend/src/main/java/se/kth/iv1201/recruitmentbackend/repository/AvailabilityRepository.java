package se.kth.iv1201.recruitmentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.Availability;

/**
 * JPA interface used to store and access <code>Availability</code> data in the database
 */
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}
