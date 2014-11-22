package brunamoreira.game_15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class winner extends Activity {

    Button bt_home;
    Intent intent_from;
    TextView time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        time = (TextView) findViewById(R.id.chrono_time);
        bt_home = (Button) findViewById(R.id.winner_home_button);
        intent_from = getIntent();



        time.setText(intent_from.getStringExtra("Time"));


        View.OnClickListener go_home_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_home_intent = new Intent(winner.this, menu.class);
                startActivity(go_home_intent);
            }
        };
        bt_home.setOnClickListener(go_home_listener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.winner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
