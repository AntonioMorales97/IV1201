package se.kth.iv1201.recruitmentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.Competence;

/**
 * JPA interface used to store and access <code>Competence</code> data in the database
 */
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
	public Competence findByName(String name);
}
