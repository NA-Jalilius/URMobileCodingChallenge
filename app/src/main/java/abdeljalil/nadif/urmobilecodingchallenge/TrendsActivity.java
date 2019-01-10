package abdeljalil.nadif.urmobilecodingchallenge;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class TrendsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
        getSupportActionBar().setSubtitle("Github Trending Repos");

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
                    Toast.makeText( getBaseContext(), "Hello there 1 :p", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_settings:
                    Toast.makeText( getBaseContext(), "Hello there, Under development :p", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

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
                Toast.makeText(getApplicationContext(), "The repositories are loading ... ", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "It's OK, our app is always here for you when you'll need it\n" +
                        "See you next time !", Toast.LENGTH_SHORT).show();
                finish();
                System.exit(0);
            }
        });
        builder.show();
    }

}
