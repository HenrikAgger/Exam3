/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Henrik
 */
public class MovieInfoDTO {
    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    
    private double imdbRating;
    private double viewerRating;
    private double criticRating;
    private double metaCritic;

    public MovieInfoDTO() {
    }

    public MovieInfoDTO(String title, int year, String plot, String directors, String genres, String cast, String poster) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
    }

    public MovieInfoDTO(String title, int year, String plot, String directors, String genres, String cast, String poster, double imdbRating, double viewerRating, double criticRating, double metaCritic) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
        this.imdbRating = imdbRating;
        this.viewerRating = viewerRating;
        this.criticRating = criticRating;
        this.metaCritic = metaCritic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }   

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public double getViewerRating() {
        return viewerRating;
    }

    public void setViewerRating(double viewerRating) {
        this.viewerRating = viewerRating;
    }

    public double getCriticRating() {
        return criticRating;
    }

    public void setCriticRating(double criticRating) {
        this.criticRating = criticRating;
    }

    public double getMetaCritic() {
        return metaCritic;
    }

    public void setMetaCritic(double metaCritic) {
        this.metaCritic = metaCritic;
    }

    @Override
    public String toString() {
        return "MovieInfoDTO{" + "title=" + title + ", year=" + year + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + ", poster=" + poster + ", imdbRating=" + imdbRating + ", viewerRating=" + viewerRating + ", criticRating=" + criticRating + ", metaCritic=" + metaCritic + '}';
    }
    
    
    
}
