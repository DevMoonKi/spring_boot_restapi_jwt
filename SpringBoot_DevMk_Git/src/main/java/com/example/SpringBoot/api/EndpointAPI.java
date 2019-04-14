package com.example.SpringBoot.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class EndpointAPI<T> {
	
	protected static String API_URL = "http://localhost:8080";
	protected Retrofit retrofit;
	protected String apiKey;
	
	{
		OkHttpClient client = new OkHttpClient.Builder()
												.readTimeout(5, TimeUnit.SECONDS)
												.connectTimeout(5, TimeUnit.SECONDS)
												.build();
		retrofit = new Retrofit.Builder()
								.baseUrl(API_URL)
								.addConverterFactory(GsonConverterFactory.create())
								.client(client)
								.build();
	}
	
	public EndpointAPI(String apiKey) {
		this.apiKey = apiKey;
	}
	
	protected abstract T create();
	
}
