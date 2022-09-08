package co.grandcircus.adventure.api;

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
	
	/**String plainCreds = "willie:p@ssword";
byte[] plainCredsBytes = plainCreds.getBytes();
byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
String base64Creds = new String(base64CredsBytes);

HttpHeaders headers = new HttpHeaders();
headers.add("Authorization", "Basic " + base64Creds);

And this is to pass the header to the REST template:

HttpEntity<String> request = new HttpEntity<String>(headers);
ResponseEntity<Account> response = restTemplate.exchange(url, HttpMethod.GET, request, Account.class);
Account account = response.getBody();
**/
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	//incl a param to get multiple random jokes
	public PictureResponse getPicture(String name) {
		String url = "https://api.pexels.com/v1/search?query=".concat(name);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", apiKey);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<PictureResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, PictureResponse.class);

		//		PictureResponse account = response.getBody();
//		PictureResponse response = restTemplate.getForObject(url, PictureResponse.class, name);
		return response.getBody();
		
	}

}


