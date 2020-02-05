package se.kth.iv1201.recruitmentbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Role findByName(String name);

}


