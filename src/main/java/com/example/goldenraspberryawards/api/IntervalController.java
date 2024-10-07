package com.example.goldenraspberryawards.api;

import com.example.goldenraspberryawards.dto.IntervalDTO;
import com.example.goldenraspberryawards.dto.MovieIntervalDTO;
import com.example.goldenraspberryawards.model.Movie;
import com.example.goldenraspberryawards.repository.MovieRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
            if(movie1.getProducer().equals(movie2.getProducer())
                && !movie1.getId().equals(movie2.getId())
                && movie1.getYear() < movie2.getYear()) {
                    MovieIntervalDTO movieIntervalDTO = new MovieIntervalDTO(movie1, movie2);
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

        return new ResponseEntity<Object>(new IntervalDTO(minIntervals.get(), maxIntervals.get()), HttpStatus.OK);
    }

}
