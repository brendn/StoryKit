package co.grandcircus.adventure.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PictureResponse {
	
	@JsonProperty("total_results")
	private Integer totalResults;
	
	private List<Photo> photos;

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

}

//class Photo{
//	
//	@JsonProperty("avg_color")
//	private Integer avgColor;
//	
//	List<Source> src;
//
//	public Integer getAvgColor() {
//		return avgColor;
//	}
//
//	public void setAvgColor(Integer avgColor) {
//		this.avgColor = avgColor;
//	}
//
//	public List<Source> getSrc() {
//		return src;
//	}
//
//	public void setSrc(List<Source> src) {
//		this.src = src;
//	}
//	
//}
//
//class Source{
//	
//	private String original;
//	private String small;
//	
//	public String getOriginal() {
//		return original;
//	}
//	public void setOriginal(String original) {
//		this.original = original;
//	}
//	public String getSmall() {
//		return small;
//	}
//	public void setSmall(String small) {
//		this.small = small;
//	}
//	
//	
//}