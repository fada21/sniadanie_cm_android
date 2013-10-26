package com.cm.sniadanie_cm.networking;

import com.cm.sniadanie_cm.json.RelicJsonWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface OtwarteZabytkiApi {
	
	@GET("/relics.json")
	public void getRelics(@Query("place") String place, 
			@Query("query") String name, 
			@Query("from") String datingFrom, 
			@Query("to")String datingTo, Callback<RelicJsonWrapper> cb);
	
	
	

}
