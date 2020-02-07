package se.kth.iv1201.recruitmentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.CompetenceProfile;

public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Long>{

}
