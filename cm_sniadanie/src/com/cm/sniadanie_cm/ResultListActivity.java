package com.cm.sniadanie_cm;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

import com.cm.sniadanie_cm.json.Relic;
import com.cm.sniadanie_cm.json.RelicJsonWrapper;
import com.cm.sniadanie_cm.networking.OtwarteZabytkiApi;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

public class ResultListActivity extends Activity {
	
	ListView listView;
	ProgressBar progressBar;
	String name;
	String place;
	String from;
	String to;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_list);
		
		listView = (ListView) findViewById(R.id.listview);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		
		getData();
		
		OtwarteZabytkiApi api = getApi();
    	
    	Callback<RelicJsonWrapper> cb = new Callback<RelicJsonWrapper>() {

			@Override
			public void failure(RetrofitError rError) {
				progressBar.setVisibility(View.GONE);
				Log.e("network", "failure on network request");		
				Log.e("network", rError.getLocalizedMessage());					

			}

			@Override
			public void success(RelicJsonWrapper relicWrapper, Response response) {
				progressBar.setVisibility(View.GONE);
				Log.d("network", "succes !!!!!!!! ");
				
				ArrayList<String> relicsArr = new ArrayList<String>();
				for (Relic r : relicWrapper.relics){
					Log.d("relic name", r.identification);
					relicsArr.add(r.identification);
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(ResultListActivity.this, 
						android.R.layout.simple_list_item_1, relicsArr);
				
				listView.setAdapter(adapter);
			}
		};
    	
    	
    	api.getRelics(place, name, from, to, cb);
	}
	
	public void getData() {
		Bundle b = getIntent().getExtras();
		name = b.getString("key_name");
		place = b.getString("key_place");
		from = b.getString("key_from");
		to = b.getString("key_to");
	}
	
	public OtwarteZabytkiApi getApi(){
    	RestAdapter restAdapter = new RestAdapter.Builder()
    		.setServer("http://otwartezabytki.pl/api/v1")
    		.setConverter( new GsonConverter( new Gson()))
    		.build();
    	
    	OtwarteZabytkiApi api = restAdapter.create( OtwarteZabytkiApi.class);
    	
    	return api;
    }

}
