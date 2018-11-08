package com.example.rkjc.news_app_2;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.datafrominternet.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int LOADER_ID = 1;
    private static final String SEARCH_QUERY_RESULTS = "searchResults";
    private String gitHubSearchResults;
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private ArrayList<NewsItem> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mAdapter = new NewsRecyclerViewAdapter(this, news);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(savedInstanceState != null && savedInstanceState.containsKey(SEARCH_QUERY_RESULTS)){
            String searchResults = savedInstanceState.getString(SEARCH_QUERY_RESULTS);
            populateRecyclerView(searchResults);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCH_QUERY_RESULTS, gitHubSearchResults);

    }

    private URL makeSearchUrl() {
        URL newsUrl = NetworkUtils.buildUrl();
        return newsUrl;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            URL url = makeSearchUrl();
            LoaderManager loaderManager = getSupportLoaderManager();
            Loader<String> gitHubSearchLoader = loaderManager.getLoader(LOADER_ID);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void populateRecyclerView(String searchResults){
        Log.d("mycode", searchResults);
        news = JsonUtils.makeList(searchResults);
        mAdapter.mRepos.addAll(news);
        mAdapter.notifyDataSetChanged();
    }



    @Override
    public static android.support.v4.content.Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                Log.d(TAG, "onStartLoading called");
                super.onStartLoading();
                if(args == null) {
                    Log.d(TAG, "bundle null");
                    return;
                }
            }

            @Override
            public String loadInBackground() {
                Log.d(TAG, "loadInBackground called");
                try {
                    Log.d(TAG, "begin network call");
                    gitHubSearchResults = NetworkUtils.getResponseFromHttpUrl(newsURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, gitHubSearchResults);
                return gitHubSearchResults;
            }
        };
    }
    }

}
