package se.kth.iv1201.recruitmentbackend.presentation.util;

import se.kth.iv1201.recruitmentbackend.presentation.controller.ApplicationController;
import se.kth.iv1201.recruitmentbackend.presentation.models.ApplicationResponse;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;
/**
 * Uses Hateos to add hypermedia links to different response entities of the rest api.
 *
 */
@Component
public class ResourceAssembler {

	public void addLinksToApplicationResponse(ApplicationResponse applicationResponse){
		applicationResponse.add(linkTo(methodOn(ApplicationController.class).getApplication(applicationResponse.getId())).withSelfRel());
	}
}
