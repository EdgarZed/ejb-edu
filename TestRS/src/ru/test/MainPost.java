package ru.test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import javax.json.Json;

import ru.model.db.postgres.Emp;
import ru.utils.MyJsonUtil;

public class MainPost {

	public static void main(String[] args) throws IOException, InterruptedException {
		Emp emp = getEmp("7839");
		updateEmp(emp);
	}
	
	private static Emp getEmp(String id) throws IOException, InterruptedException {
		var request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/RestService/controller/hr/emp"))
				.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(id)).build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode()); // 200
		System.out.println(response.body());

		Emp emp = MyJsonUtil.fromJson(response.body(), Emp.class);
		return emp;
	}
	
	private static void updateEmp(Emp emp) throws IOException, InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'[z]");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String hiredate = dateFormat.format(emp.getHiredate());
		var newEmp = Json.createObjectBuilder()
				.add("empno", emp.getEmpno())
				.add("deptno", emp.getDeptno())
				.add("job", emp.getJob())
				.add("sal", emp.getSal()+1)
				.add("ename", emp.getEname())
				.add("hiredate", hiredate)
				.build();
		System.out.println("-> "+newEmp);
		var request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/RestService/controller/hr/updateemp"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(newEmp.toString()))
				.build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
	}
}
