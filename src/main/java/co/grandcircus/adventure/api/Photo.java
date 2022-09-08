package co.grandcircus.adventure.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo {
	
	@JsonProperty("avg_color")
	private String avgColor;
	
	@JsonProperty("src")
	Source src;

	public String getAvgColor() {
		return avgColor;
	}

	public void setAvgColor(String avgColor) {
		this.avgColor = avgColor;
	}

	public Source getSrc() {
		return src;
	}

	public void setSrc(Source src) {
		this.src = src;
	}

}
