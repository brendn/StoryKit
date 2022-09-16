package co.grandcircus.adventure.pexels;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PictureResponse {
	
	@JsonProperty("total_results")
	private Integer totalResults;
	
	private List<Photo> photos;

	public List<Photo> getPhotos() {
		return this.photos;
	}
	
	public List<String> getTenPhotos() {
		List<String> tenPhotos = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			tenPhotos.add(getPhotos().get(i).getSrc().getSmall());
		}
		return tenPhotos;
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