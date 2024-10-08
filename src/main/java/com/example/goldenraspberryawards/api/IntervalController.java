package com.example.goldenraspberryawards.api;

import com.example.goldenraspberryawards.dto.IntervalDTO;
import com.example.goldenraspberryawards.dto.MovieIntervalDTO;
import com.example.goldenraspberryawards.model.Movie;
import com.example.goldenraspberryawards.repository.MovieRepository;
import com.example.goldenraspberryawards.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
public class IntervalController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> interval() {
        List<Movie> movies = movieRepository.findAll();
        AtomicReference<List<MovieIntervalDTO>>  maxIntervals = new AtomicReference<>(new ArrayList<>());
        AtomicReference<Integer> maxInterval = new AtomicReference<>(0);
        AtomicReference<List<MovieIntervalDTO>> minIntervals = new AtomicReference<>(new ArrayList<>());
        AtomicReference<Integer> minInterval = new AtomicReference<>(0);
        movies.forEach(movie1 -> movies.forEach(movie2 -> {
            String producer = StringUtils.splitProducer(movie1.getProducer()).stream().filter(StringUtils.splitProducer(movie2.getProducer())::contains).collect(Collectors.joining());
            if(!producer.isEmpty()
                && !movie1.getId().equals(movie2.getId())
                && movie1.getYear() < movie2.getYear()
                && movie1.isWinner() && movie2.isWinner()
                ) {
                    MovieIntervalDTO movieIntervalDTO = new MovieIntervalDTO(movie1, movie2, producer);
                    if(Objects.equals(maxInterval.get(), movieIntervalDTO.getInterval())) {
                        maxIntervals.get().add(movieIntervalDTO);
                    } else if(maxInterval.get() < movieIntervalDTO.getInterval()) {
                        maxIntervals.set(new ArrayList<>());
                        maxIntervals.get().add(movieIntervalDTO);
                        maxInterval.set(movieIntervalDTO.getInterval());
                    }
                    if(Objects.equals(minInterval.get(), movieIntervalDTO.getInterval()) || minInterval.get().equals(0)) {
                        minIntervals.get().add(movieIntervalDTO);
                        minInterval.set(movieIntervalDTO.getInterval());
                    } else if(minInterval.get() > movieIntervalDTO.getInterval()) {
                        minIntervals.set(new ArrayList<>());
                        minIntervals.get().add(movieIntervalDTO);
                        minInterval.set(movieIntervalDTO.getInterval());
                    }
            }
        }));

        return new ResponseEntity<>(new IntervalDTO(minIntervals.get(), maxIntervals.get()), HttpStatus.OK);
    }

    @PostMapping()
    public void post(@RequestBody Movie movie) {
        if(movie.getId() != null) {
            throw new IllegalArgumentException("Movie id need to be null");
        }
        movieRepository.save(movie);
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        movieRepository.deleteById(id);
    }

    @PutMapping()
    public void put(@RequestBody Movie movie) {
        if(movie.getId() == null) {
            throw new IllegalArgumentException("Movie id cannot be null");
        }
        movieRepository.save(movie);
    }

}
