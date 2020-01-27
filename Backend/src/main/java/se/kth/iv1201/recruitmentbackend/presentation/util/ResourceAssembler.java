package se.kth.iv1201.recruitmentbackend.presentation.util;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import se.kth.iv1201.recruitmentbackend.domain.Person;

@Component
public class ResourceAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>>{

	@Override
	public EntityModel<Person> toModel(Person entity) {
		
		return null; // lägg till när vi har applications.
	}
	

}
