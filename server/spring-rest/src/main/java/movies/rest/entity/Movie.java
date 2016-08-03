package movies.rest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table
@NamedQueries({ 
	@NamedQuery(name = "Movie.findAll", query = "SELECT e FROM Movie e ORDER BY e.title ASC"),
	@NamedQuery(name = "Movie.findByYear", query = "SELECT e FROM Movie e ORDER BY e.year ASC"),
	@NamedQuery(name = "Movie.findByGenre", query = "SELECT e FROM Movie e ORDER BY e.genre ASC"),
	@NamedQuery(name = "Movie.findByIMDBRatings", query = "SELECT e FROM Movie e ORDER BY e.imdbRating ASC"),
	@NamedQuery(name = "Movie.findByIMDBVotes", query = "SELECT e FROM Movie e ORDER BY e.imdbVotes ASC"),
	@NamedQuery(name = "Movie.findByTitle", query = "SELECT e FROM Movie e WHERE e.title=:pTitle") 
})
public class Movie {
	
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String movie_id;
	
	@Column(unique = true)
	private String title;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Review> reviews;
	
	private int year;
	private String rated;
	private String releaseDate;
	private String runTime;
	private String genre;
	private String director;
	private String writer;
	private String actors;
	private String plot;
	private String language;
	private String country;
	private String awards;
	private String poster;
	private int metaScore;
	private float imdbRating;
	private long imdbVotes;
	private String imdbId;
	private String type;
	
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public int getMetaScore() {
		return metaScore;
	}
	public void setMetaScore(int metaScore) {
		this.metaScore = metaScore;
	}
	public float getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(float imdbRating) {
		this.imdbRating = imdbRating;
	}
	public long getImdbVotes() {
		return imdbVotes;
	}
	public void setImdbVotes(long imdbVotes) {
		this.imdbVotes = imdbVotes;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
