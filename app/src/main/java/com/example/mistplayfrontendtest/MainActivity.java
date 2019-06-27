package com.example.mistplayfrontendtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // linking variables
    TextView seacrhText;
    SearchView seacrhBar;
    ListView listView;
    String[] games = {"Underwater Survival Sim – 2 ", "Close Up Character - Pic Quiz!",
            "Go Kart Go! Ultra!", "Sudoku 10'000 Free"};

    ArrayList<String> list;

    String[] stockArr = new String[list.size()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seacrhText=findViewById(R.id.textSearch);
        seacrhBar=findViewById(R.id.searchMe);


//populates search
        //String[] stockArr = getJSonData();

        final ListAdapter listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,games);


        listView=(ListView)findViewById(R.id.listofItems);

        listView.setAdapter(listAdapter);


        seacrhBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((ArrayAdapter) listAdapter).getFilter().filter(newText);
                return false;
            }
        });

    }

//populates array
    public String[] updateList()
    {
        String [] returnArr=new String[games.length];
        String searchstring=seacrhBar.toString();
        String text = searchstring.toLowerCase(Locale.getDefault());
            for (int i=0;i<games.length;i++)
            {
                int j=0;
                if(games[i].toLowerCase().startsWith(searchstring)) {
                    returnArr[j] =games[i];
                    j++;
                }
            }
            return  returnArr;
    }

//opens json file

    private String[] getJSonData(){

        JSONArray jsonArray=null;

        try {

            InputStream is = getResources().getAssets().open("games.json");

            int size = is.available();

            byte[] data = new byte[size];

            is.read(data);

            is.close();

            String json = new String(data, "UTF-8");

            jsonArray=new JSONArray(json);


            list = new ArrayList<String>();
            for(int i = 0; i < jsonArray.length(); i++){
                list.add(jsonArray.getJSONObject(i).getString("title"));
            }




        }catch (IOException e){

            e.printStackTrace();

        }catch (JSONException je){

            je.printStackTrace();

        }

        stockArr = list.toArray(stockArr);
        //converted from json to arraylist to stringarr

        return stockArr;


    }

}
