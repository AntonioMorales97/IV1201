package se.kth.iv1201.recruitmentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.CompetenceProfile;
/**
 * JPA interface used to store and access competence profile data in the database
 */
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Long>{

}
