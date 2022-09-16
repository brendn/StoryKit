package co.grandcircus.adventure.pexels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo {
	
	@JsonProperty("avg_color")
	private String avgColor;
	
	@JsonProperty("src")
	Source src;

	public String getAvgColor() {
		return avgColor;
	}

	public Source getSrc() {
		return src;
	}

}
