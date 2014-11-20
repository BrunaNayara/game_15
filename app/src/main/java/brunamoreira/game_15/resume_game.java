package brunamoreira.game_15;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


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
    Button bt_scramble;



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
        bt_scramble = (Button) findViewById(R.id.button_scramble);

        bt_1.setEnabled(false);
        bt_2.setEnabled(false);
        bt_3.setEnabled(false);
        bt_4.setEnabled(false);
        bt_5.setEnabled(false);
        bt_6.setEnabled(false);
        bt_7.setEnabled(false);
        bt_8.setEnabled(false);
        bt_9.setEnabled(false);
        bt_10.setEnabled(false);
        bt_11.setEnabled(false);
        bt_12.setEnabled(false);
        bt_13.setEnabled(false);
        bt_14.setEnabled(false);
        bt_15.setEnabled(false);
        bt_16.setEnabled(false);


        View.OnClickListener scramble = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scramble_board();
                Toast.makeText(getApplication(), "Finish scramble", Toast.LENGTH_SHORT).show();
            }
        };
        bt_scramble.setOnClickListener(scramble);


        View.OnClickListener movement = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), view.getTag() + " is" + (view.isEnabled() ? " ": " not ") + "enabled", Toast.LENGTH_SHORT).show();
            }
        };
        bt_1.setOnClickListener(movement);
        bt_2.setOnClickListener(movement);
        bt_3.setOnClickListener(movement);
        bt_4.setOnClickListener(movement);
        bt_5.setOnClickListener(movement);
        bt_6.setOnClickListener(movement);
        bt_7.setOnClickListener(movement);
        bt_8.setOnClickListener(movement);
        bt_9.setOnClickListener(movement);
        bt_10.setOnClickListener(movement);
        bt_11.setOnClickListener(movement);
        bt_12.setOnClickListener(movement);
        bt_13.setOnClickListener(movement);
        bt_14.setOnClickListener(movement);
        bt_15.setOnClickListener(movement);
        bt_16.setOnClickListener(movement);





    }

    protected void scramble_board (){

        int x_position = -1;
        int y_position = -1;
        int[]  up_neighbor = new int[] {-1,-1};
        int[]  down_neighbor = new int[] {-1,-1};
        int[]  left_neighbor = new int[] {-1,-1};
        int[]  right_neighbor = new int[] {-1,-1};


        //find blank space
        for(int x=0; x<4; x++){
            for(int y=0; y<4; y++){
                if(board[x][y]== 0){
                    x_position=x;
                    y_position=y;
                }
            }
        }

        //fill neighbors

        up_neighbor = new int[]{(x_position - 1), y_position};
        down_neighbor = new int[] {(x_position + 1), y_position};
        left_neighbor = new int[] {x_position, (y_position - 1)};
        right_neighbor = new int[] {x_position, (y_position + 1)};


        if(x_position ==0){
            up_neighbor = null;
        }else if(x_position == 3){
            down_neighbor = null;
        }

        if (y_position == 0){
            left_neighbor = null;
        }else if (y_position == 3){
            right_neighbor = null;
        }


        //enable neighbor
        if(up_neighbor !=null){
            enable_button(up_neighbor);
        }
        if(down_neighbor !=null){
            enable_button(down_neighbor);
        }
        if(left_neighbor !=null){
            enable_button(left_neighbor);
        }
        if(right_neighbor !=null){
            enable_button(right_neighbor);
        }

    }

    

    protected void enable_button(int[] button_index){

        if(button_index[0]==0){
            switch (button_index[1]){
                case 0:
                    bt_1.setEnabled(true);
                    break;
                case 1:
                    bt_2.setEnabled(true);
                    break;
                case 2:
                    bt_3.setEnabled(true);
                    break;
                case 3:
                    bt_4.setEnabled(true);
                    break;
            }
        }else if(button_index[0]==1) {
            switch (button_index[1]) {
                case 0:
                    bt_5.setEnabled(true);
                    break;
                case 1:
                    bt_6.setEnabled(true);
                    break;
                case 2:
                    bt_7.setEnabled(true);
                    break;
                case 3:
                    bt_8.setEnabled(true);
                    break;
            }
        }else if(button_index[0]==2) {
            switch (button_index[1]) {
                case 0:
                    bt_9.setEnabled(true);
                    break;
                case 1:
                    bt_10.setEnabled(true);
                    break;
                case 2:
                    bt_11.setEnabled(true);
                    break;
                case 3:
                    bt_12.setEnabled(true);
                    break;
            }
        }
        else if(button_index[0]==3) {
            switch (button_index[1]) {
                case 0:
                    bt_13.setEnabled(true);
                    break;
                case 1:
                    bt_14.setEnabled(true);
                    break;
                case 2:
                    bt_15.setEnabled(true);
                    break;
                case 3:
                    bt_16.setEnabled(true);
                    break;
            }
        }

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
