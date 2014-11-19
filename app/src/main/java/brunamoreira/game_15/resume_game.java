package brunamoreira.game_15;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class resume_game extends Activity {

    private int[][] board = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,0}};

    //  buttons

    Button bt_1;
    Button bt_2;
    Button bt_3;
    Button bt_4;
    Button bt_5;
    Button bt_6;
    Button bt_7;
    Button bt_8;
    Button bt_9;
    Button bt_10;
    Button bt_11;
    Button bt_12;
    Button bt_13;
    Button bt_14;
    Button bt_15;
    Button bt_16;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_game);
        bt_1 = (Button) findViewById(R.id.button1);
        bt_2 = (Button) findViewById(R.id.button2);
        bt_3 = (Button) findViewById(R.id.button3);
        bt_4 = (Button) findViewById(R.id.button4);
        bt_5 = (Button) findViewById(R.id.button5);
        bt_6 = (Button) findViewById(R.id.button6);
        bt_7 = (Button) findViewById(R.id.button7);
        bt_8 = (Button) findViewById(R.id.button8);
        bt_9 = (Button) findViewById(R.id.button9);
        bt_10 = (Button) findViewById(R.id.button10);
        bt_11 = (Button) findViewById(R.id.button11);
        bt_12 = (Button) findViewById(R.id.button12);
        bt_13 = (Button) findViewById(R.id.button13);
        bt_14 = (Button) findViewById(R.id.button14);
        bt_15 = (Button) findViewById(R.id.button15);
        bt_16 = (Button) findViewById(R.id.button16);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.resume_game, menu);
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

    public void fill_matrix(){
        int i, j;
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                //board [i][j] = ;
            }
        }
    }

    public void scramble(){
        int i, j;
        for(i=0; i<4; i++){
            for(j=0; j<4;j++){
                //if(){}
            }

        }
    }
}
