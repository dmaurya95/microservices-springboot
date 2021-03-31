package com.microservices.movieinfoservice.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.movieinfoservice.model.Movie;


@RestController
@RequestMapping("/movies")
public class MovieInfoResource {
	
	@RequestMapping("/{id}")
	public Movie getMovie(@PathVariable String id) {
		return new Movie(id, "Transformers");
	}

}
