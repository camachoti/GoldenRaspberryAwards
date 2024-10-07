package com.example.goldenraspberryawards.dto;

import com.example.goldenraspberryawards.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class IntervalDTO {

    private List<MovieIntervalDTO> min = new ArrayList<MovieIntervalDTO>();
    private List<MovieIntervalDTO> max = new ArrayList<MovieIntervalDTO>();

    public IntervalDTO(List<MovieIntervalDTO> min, List<MovieIntervalDTO> max) {
        this.min = min;
        this.max = max;
    }

    public List<MovieIntervalDTO> getMin() {
        return min;
    }

    public void setMin(List<MovieIntervalDTO> min) {
        this.min = min;
    }

    public List<MovieIntervalDTO> getMax() {
        return max;
    }

    public void setMax(List<MovieIntervalDTO> max) {
        this.max = max;
    }

}
