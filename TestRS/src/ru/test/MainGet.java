package ru.test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainGet {

	public static void main(String[] args) throws IOException, InterruptedException {
		var request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/RestService/controller/hr/emps/30"))
//                .header("Content-Type", "text/plain")
//                .POST(HttpRequest.BodyPublishers.ofString("Hi there!"))
				.GET().build();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				var client = HttpClient.newHttpClient();
				try {
					var response = client.send(request, HttpResponse.BodyHandlers.ofString());
					System.out.println(response.statusCode()); // 200
					System.out.println(response.body());
				} catch (Exception e) {
				}
			}).start();
		}
	}
}
