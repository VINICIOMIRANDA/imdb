package imdbAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;


public class GetIMDB {
	
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/dados.properties");
		props.load(file);
		return props;

	}
		
	public static void main(String[] args) throws IOException  {
		String apiKey;
		 String busca = "inception";
		Properties prop = getProp();
		apiKey = prop.getProperty ("prop.apiKey");
		BuscaFilme(apiKey,busca);
		BuscaTop250(apiKey);
		
	}

	private static  void BuscaFilme(String apiKey, String busca) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
		      .uri(URI.create("https://imdb-api.com/en/API/Search/"+apiKey+"/"+busca))
		      .build();
		client.sendAsync(request, BodyHandlers.ofString())
		      .thenApply(HttpResponse::body)
		      .thenAccept(System.out::println)
		      .join();
	}
	
	private static  void BuscaTop250(String apiKey) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
		      .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/"+apiKey))
		      .build();
		client.sendAsync(request, BodyHandlers.ofString())
		      .thenApply(HttpResponse::body)
		      .thenAccept(System.out::println)
		      .join();
	}

	
	
}
