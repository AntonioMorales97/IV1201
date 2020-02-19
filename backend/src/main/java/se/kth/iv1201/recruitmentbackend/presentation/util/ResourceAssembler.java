package se.kth.iv1201.recruitmentbackend.presentation.util;

import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.presentation.controller.ApplicationController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

/**
 * Uses HateOAS to add hypermedia links to different response entities of the
 * rest api.
 *
 */
@Component
public class ResourceAssembler {
	public void addLinksToApplication(Application application) {
		application
				.add(linkTo(methodOn(ApplicationController.class).getApplication(application.getId())).withSelfRel());
	}
}
