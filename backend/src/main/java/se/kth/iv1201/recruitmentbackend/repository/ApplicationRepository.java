package se.kth.iv1201.recruitmentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.Application;

/**
 * JPA interface used to store and access <code>Application</code> data in the database
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}