package com.example.moviepractice;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository repository;
    private final RunTheGems runTheGems;

    public MovieController(MovieRepository repository, RunTheGems runTheGems) {
        this.repository = repository;
        this.runTheGems = runTheGems;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        String geminiDescription = runTheGems.generateDescription(movie.getTitle());
        movie.setDescription(geminiDescription);
        return repository.save(movie);
    }
}
