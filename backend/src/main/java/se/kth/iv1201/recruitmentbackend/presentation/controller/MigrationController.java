package se.kth.iv1201.recruitmentbackend.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentbackend.migration.MigrationService;

@RestController
@CrossOrigin
public class MigrationController {
	@Autowired
	private MigrationService migrationService;
	
	@PostMapping("/migrate")
	@ResponseStatus(HttpStatus.OK)
	public void migrate() {
		this.migrationService.migrate();
	}
}
