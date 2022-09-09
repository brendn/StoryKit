package co.grandcircus.adventure.pexels;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PictureService {
	
	@Value("${api.key}")
	private String apiKey;
	
	private final RestTemplate restTemplate = new RestTemplate();

	public PictureResponse getPicture(String name) {
		String url = "https://api.pexels.com/v1/search?query=".concat(name);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", apiKey);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<PictureResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, PictureResponse.class);
		return response.getBody();
	}
}


