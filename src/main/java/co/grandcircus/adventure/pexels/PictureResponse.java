package co.grandcircus.adventure.pexels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PictureResponse {
	
	@JsonProperty("total_results")
	private Integer totalResults;
	
	private List<Photo> photos;

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public String getSmallURL() {
		List<Photo> photoList = getPhotos();
		if (photoList.isEmpty()) {
			return "";
		} else {
			return photoList.get(0).getSrc().getSmall();
		}
	}

}