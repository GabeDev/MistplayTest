package com.example.mistplayfrontendtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // linking variables
    TextView seacrhText;
    SearchView seacrhBar;
    ListView listView;
    String[] favoriteTVShows = {"Pushing Daisies", "Better Off Ted",
            "Twin Peaks", "Freaks and Geeks", "Orphan Black", "Walking Dead",
            "Breaking Bad", "The 400", "Alphas", "Life on Mars"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seacrhText=findViewById(R.id.textSearch);
        seacrhBar=findViewById(R.id.searchMe);


//populates search
        final ListAdapter listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,favoriteTVShows);

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
        String [] returnArr=new String[favoriteTVShows.length];
        String searchstring=seacrhBar.toString();
        String text = searchstring.toLowerCase(Locale.getDefault());
            for (int i=0;i<favoriteTVShows.length;i++)
            {
                int j=0;
                if(favoriteTVShows[i].toLowerCase().startsWith(searchstring)) {
                    returnArr[j] =favoriteTVShows[i];
                    j++;
                }
            }
            return  returnArr;
    }

//opens json file
    public void getJSON()
    {
        try {
            InputStream is = getAssets().open("games.json");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
