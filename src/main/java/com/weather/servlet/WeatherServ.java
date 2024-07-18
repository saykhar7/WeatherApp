package com.weather.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/WeatherServ")
public class WeatherServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public WeatherServ() {
        super();
       
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String WEATHER_API_KEY = "";
		
		String cityName = request.getParameter("city");
		
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?zip="+cityName+",US&appid="+WEATHER_API_KEY;
		
		URL url = new URL(apiUrl);
		HttpURLConnection connection =(HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		
		InputStream inputStream = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream);
		
		StringBuilder responseContent = new StringBuilder();
		
		Scanner scanner = new Scanner(reader);
		
		while(scanner.hasNext()) {
			responseContent.append(scanner.nextLine());
		}
		scanner.close();
		
		System.out.println(responseContent);
		
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
		
		
		
		
		
	}

}
