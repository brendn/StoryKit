package co.grandcircus.adventure.pexels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PictureResponse {
	
	@JsonProperty("total_results")
	private Integer totalResults;
	
	private List<Photo> photos;

	public Integer getTotalResults() {
		return totalResults;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public String getSmallURL() {
		return getPhotos().get(0).getSrc().getSmall();
	}

}