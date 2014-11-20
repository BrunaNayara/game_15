package brunamoreira.game_15;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;


public class resume_game extends Activity {

    private final static String TAG = "resume_game_activity";
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


        Random rand = new Random();
        int total_movements = rand.nextInt(500) + 200;

        int[] empty_index = new int[] {-1,-1};
        int[][] neighbors = new int[][]{{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}};
        int[] invalid_index = {-1,-1};



        //find blank space
        empty_index = find_empty();


        //fill neighbors
        neighbors = fill_neighbors_indexes(empty_index);

        //enable neighbor
        if(neighbors[0] !=invalid_index){
            enable_button(neighbors[0]);
        }
        if(neighbors[1] !=invalid_index){
            enable_button(neighbors[1]);
        }
        if(neighbors[2] !=invalid_index){
            enable_button(neighbors[2]);
        }
        if(neighbors[3] !=invalid_index){
            enable_button(neighbors[3]);
        }

        Log.d(TAG, "Index of  all neighbors 0 scramble " + neighbors[0][0] + " " + neighbors[0][1]);
        Log.d(TAG, "Index of  all neighbors 1 scramble " + neighbors[1][0] + " " + neighbors[1][1]);
        Log.d(TAG, "Index of  all neighbors 2 scramble " + neighbors[2][0] + " " + neighbors[2][1]);
        Log.d(TAG, "Index of  all neighbors 3 scramble " + neighbors[3][0] + " " + neighbors[3][1]);


        //make a move
        move_random(neighbors, empty_index);



    }

    protected int[] find_empty (){
        int[] empty_index = new int[] {-1,-1};

        for(int x=0; x<4; x++){
            for(int y=0; y<4; y++){
                if(board[x][y]== 0){
                    empty_index[0]=x;
                    empty_index[1]=y;
                }
            }
        }

        Log.d(TAG, "Index of blank in find_empty" + empty_index[0] + " " + empty_index[1]);
        return empty_index;
    }

    protected int[][] fill_neighbors_indexes (int[] empty_index){

        int[][] neighbors = new int[][]{{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}};


        if(empty_index[0] !=0){
            neighbors[0] = new int[]  {(empty_index[0] - 1), empty_index[1]};
            //neighbors[0] = null;
        }
        if(empty_index[0] != 3){
            neighbors[1] = new int[] {(empty_index[0] + 1), empty_index[1]};
            //neighbors[1] = null;
        }

        if (empty_index[1]!= 0){
            neighbors[2] = new int[] {empty_index[0], (empty_index[1] - 1)};
            //neighbors[2] = null;
        }
        if (empty_index[1] != 3){
            neighbors[3] = new int[] {empty_index[0], (empty_index[1]+ 1)};
            //neighbors[3] = null;
        }

        Log.d(TAG, "Index of  all neighbors 0 move " + neighbors[0][0] + " " + neighbors[0][1]);
        Log.d(TAG, "Index of  all neighbors 1 move " + neighbors[1][0] + " " + neighbors[1][1]);
        Log.d(TAG, "Index of  all neighbors 2 move " + neighbors[2][0] + " " + neighbors[2][1]);
        Log.d(TAG, "Index of  all neighbors 3 move " + neighbors[3][0] + " " + neighbors[3][1]);

        return neighbors;
    }

    protected Button button_from_index(int[] button_index){

        if(button_index[0]==0){
            switch (button_index[1]){
                case 0:
                    return bt_1;
                case 1:
                    return bt_2;
                case 2:
                    return bt_3;
                case 3:
                    return bt_4;
            }
        }else if(button_index[0]==1) {
            switch (button_index[1]) {
                case 0:
                    return bt_5;
                case 1:
                    return bt_6;
                case 2:
                    return bt_7;
                case 3:
                    return bt_8;
            }
        }else if(button_index[0]==2) {
            switch (button_index[1]) {
                case 0:
                    return bt_9;
                case 1:
                    return bt_10;
                case 2:
                    return bt_11;
                case 3:
                    return bt_12;
            }
        }
        else if(button_index[0]==3) {
            switch (button_index[1]) {
                case 0:
                    return bt_13;
                case 1:
                    return bt_14;
                case 2:
                    return bt_15;
                case 3:
                    return bt_16;
            }
        }

        return null;
    }

    protected void enable_button(int[] button_index){
        /*
        Button button_to_enable = button_from_index(button_index);
        button_to_enable.setEnabled(true);
        */

        if(button_index[0]==0){
            switch (button_index[1]){
                case 0:
                    bt_1.setEnabled(true);
                case 1:
                    bt_2.setEnabled(true);
                case 2:
                    bt_3.setEnabled(true);
                case 3:
                    bt_4.setEnabled(true);
            }
        }else if(button_index[0]==1) {
            switch (button_index[1]) {
                case 0:
                    bt_5.setEnabled(true);
                case 1:
                    bt_6.setEnabled(true);
                case 2:
                    bt_7.setEnabled(true);
                case 3:
                    bt_8.setEnabled(true);
            }
        }else if(button_index[0]==2) {
            switch (button_index[1]) {
                case 0:
                    bt_9.setEnabled(true);
                case 1:
                    bt_10.setEnabled(true);
                case 2:
                    bt_11.setEnabled(true);
                case 3:
                    bt_12.setEnabled(true);
            }
        }
        else if(button_index[0]==3) {
            switch (button_index[1]) {
                case 0:
                    bt_13.setEnabled(true);
                case 1:
                    bt_14.setEnabled(true);
                case 2:
                    bt_15.setEnabled(true);
                case 3:
                    bt_16.setEnabled(true);
            }
        }

    }

    protected void move_random(int[][] neighbors, int[] empty_index){

        Random rand = new Random();
        int possible_moves=0;
        int neighbor_to_move=0;
        int[] invalid_index = {-1,-1};
        int invalid = -1;

        for(int a=0; a<4;a++){
            if (neighbors[a][0] != invalid) {
                possible_moves++;
                Log.d(TAG, "Neighbors " + "[" + a + "] = "+ neighbors[a][0] + invalid);
                Log.d(TAG, "Possible moves" + possible_moves);
            }

        }


        // Find out why it does not rand other positions
        Log.d(TAG, "Neighbors while " + "[" + neighbor_to_move + "] = "+ neighbors[neighbor_to_move][0] + invalid);
         do{
            neighbor_to_move = rand.nextInt(4);
            Log.d(TAG, "Random number" + neighbor_to_move);
        }while(neighbors[neighbor_to_move][0] == invalid);

        Log.d(TAG, "Index of blank in move_random" + empty_index[0]+" "+empty_index[1]);
        //Log.d(TAG, "Index of  all neighbors 0 move " + neighbors[0][0] + " " + neighbors[0][1]);
        //Log.d(TAG, "Index of  all neighbors 1 move " + neighbors[1][0] + " " + neighbors[1][1]);
        //Log.d(TAG, "Index of  all neighbors 2 move " + neighbors[2][0] + " " + neighbors[2][1]);
        //Log.d(TAG, "Index of  all neighbors 3 move " + neighbors[3][0] + " " + neighbors[3][1]);

        //swap 0(empty) and neighbor  === array
        int temp = board[empty_index[0]][empty_index[1]];
        //Find out why it comes here even though it is an invalid index (while)
        board[empty_index[0]] [empty_index[1]] = board[neighbors[neighbor_to_move][0]][neighbors[neighbor_to_move][1]];
        board[neighbors[neighbor_to_move][0]][neighbors[neighbor_to_move][1]] = temp;


        //swap button Text   === screen
        Button empty = button_from_index(empty_index);
        Button bt_movement = button_from_index(neighbors[neighbor_to_move]);

        move_button_text(empty, bt_movement);



    }

    protected void move_button_text(Button empty, Button move){

        String temp = (String) empty.getText();
        empty.setText(move.getText());
        move.setText(temp);

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


}
