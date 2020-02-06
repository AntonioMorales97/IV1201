package se.kth.iv1201.recruitmentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.Competence;


public interface CompetenceRepository extends JpaRepository<Competence, Long> {
	public Competence findByName(String name);
}
