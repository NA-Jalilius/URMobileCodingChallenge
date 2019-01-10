package abdeljalil.nadif.urmobilecodingchallenge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class TrendsActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
        getSupportActionBar().setSubtitle("Github Trending Repos");

        mTextMessage = (TextView) findViewById(R.id.message);
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

}
