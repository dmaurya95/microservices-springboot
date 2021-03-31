package com.microservices.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.moviecatalogservice.model.CatalogItem;
import com.microservices.moviecatalogservice.model.Movie;
import com.microservices.moviecatalogservice.model.Rating;

@RestController
@RequestMapping("/movie-catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){

		// WebClient.Builder webClient = WebClient.builder();
		//RestTemplate restTemplate = new RestTemplate();


		List<Rating> ratings = Arrays.asList(
				new Rating("m1", 4), 
				new Rating("m2",3));

		return ratings.stream().map(rating ->{
			Movie m = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);

			/*
			 * Movie m = webClientBuilder.build() //get an asynchronous object .get()
			 * .uri("http://localhost:5001/movies/"+rating.getMovieId()) .retrieve()
			 * .bodyToMono(Movie.class) .block();
			 */

			return new CatalogItem(m.getName(), "Movie Description", rating.getRating());
		}).collect(Collectors.toList());




		//return Collections.singletonList(new CatalogItem("Transformers", "Robotic life", 4));

	}
}
