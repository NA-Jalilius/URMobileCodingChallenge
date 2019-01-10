package abdeljalil.nadif.urmobilecodingchallenge;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import abdeljalil.nadif.urmobilecodingchallenge.adapter.MyAdapter;
import abdeljalil.nadif.urmobilecodingchallenge.controller.RetrofitController;
import abdeljalil.nadif.urmobilecodingchallenge.model.Repo;
import abdeljalil.nadif.urmobilecodingchallenge.service.ILoadMore;

public class TrendsActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    public ProgressDialog progressDialog;
    public RetrofitController controller;
    public List<Repo> repoList = new ArrayList<>();
    public Activity main;

    public static int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
        getSupportActionBar().setSubtitle("Github Trending Repos");
        main = this;
        progressDialog = new ProgressDialog(main);

        /**
         * Added just so we can make some network calls in the main application thread
         * Yet it's not a good pratice, better make these calls in a separate thread
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        recyclerView = (RecyclerView) findViewById(R.id.listRepos);
        informDialog();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_trending:
                    showProgressDialog();
                    createAPI(String.valueOf(page));

                    repoList = RetrofitController.getAllRepos().getRepos();

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listRepos);
                    recyclerView.setLayoutManager(new LinearLayoutManager(main));
                    hideProgressDialog();

                    myAdapter = new MyAdapter(recyclerView, main, repoList);
                    recyclerView.setAdapter(myAdapter);
                    myAdapter.setLoadMore(new ILoadMore() {
                        @Override
                        public void onLoadMore() {
                            if(repoList.size() <= 100){
                                repoList.add(null);
                                myAdapter.notifyItemInserted(repoList.size()-1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        repoList.remove(repoList.size() - 1);
                                        myAdapter.notifyItemRemoved(repoList.size());

                                        //Load more data
                                        page ++;
                                        createAPI(String.valueOf(page));
                                        repoList.addAll(RetrofitController.getAllRepos().getRepos());
                                        myAdapter.notifyDataSetChanged();
                                        myAdapter.setLoaded();
                                    }
                                },2000);
                            }else{
                                Toast.makeText( main, "Data loading completed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    return true;
                case R.id.navigation_settings:
                    Toast.makeText( getBaseContext(), "Hello there, Under development :p", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    private void createAPI(String page){
        controller = new RetrofitController();
        controller.start(page);
    }

    /* Show progress dialog. */
    public void showProgressDialog()
    {
        // Set progress dialog display message.
        progressDialog.setMessage("Please Wait");
        // The progress dialog can not be cancelled.
        progressDialog.setCancelable(false);
        // Show it.
        progressDialog.show();
    }

    /* Hide progress dialog. */
    public void hideProgressDialog()
    {
        // Close it.
        progressDialog.hide();
    }

    private void informDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Want to load some trending repos ?");
        builder.setMessage("Please click on the trending button at the navigation bar of the application !");
        builder.setCancelable(false);
        builder.setPositiveButton("YES !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                navigationView = (BottomNavigationView) findViewById(R.id.navigation);
                navigationView.setSelectedItemId(R.id.navigation_trending);
                Toast.makeText(getBaseContext(), "The repositories are loading ... ", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "It's OK, our app is always here for you when you'll need it\n" +
                        "See you next time !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.show();
    }

}
