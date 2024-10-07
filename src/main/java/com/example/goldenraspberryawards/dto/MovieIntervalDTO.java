package com.example.goldenraspberryawards.dto;

import com.example.goldenraspberryawards.model.Movie;

public class MovieIntervalDTO {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    public MovieIntervalDTO(Movie movie1, Movie movie2) {
        this.producer = movie1.getProducer();
        this.interval = movie2.getYear() - movie1.getYear();
        this.previousWin = movie1.getYear();
        this.followingWin = movie2.getYear();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }

}
