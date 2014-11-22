package brunamoreira.game_15;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;


public class resume_game extends Activity {

    private final static String TAG = "resume_game_activity";
    private int[][] board = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,0}};

    private SensorManager sensor;
    private float accel;
    private float current_accel;
    private float last_accel;

    Vibrator vibrate;

    Chronometer chrono;

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
    Button bt_home;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_game);

        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        sensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor.registerListener(sensor_listener, sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        accel = 0.00f;
        current_accel = SensorManager.GRAVITY_EARTH;
        last_accel = SensorManager.GRAVITY_EARTH;

        chrono = (Chronometer) findViewById(R.id.chronometer);

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
        bt_home = (Button) findViewById(R.id.bt_home);

        disable_all_buttons();


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
                //Toast.makeText(getApplication(), view.getTag() + " is" + (view.isEnabled() ? " ": " not ") + "enabled", Toast.LENGTH_SHORT).show();
                make_a_move(view);

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


        View.OnClickListener go_home_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_home_intent = new Intent(resume_game.this, menu.class);
                startActivity(go_home_intent);
            }
        };
        bt_home.setOnClickListener(go_home_listener);





    }

    private void make_a_move(View view) {

        Button empty_button;
        int[] button_index;
        int[] empty;
        int[] invalid_index = {-1,-1};
        int[][] neighbors;

        //fill values
        empty = find_empty();
        empty_button = button_from_index(empty);
        button_index = index_from_button((Button)view);

        //make a move
        move_in_array(empty, button_index);
        move_button_text((Button) empty_button, (Button) view );

        //disable all buttons
        disable_all_buttons();

        //find and enable neighbors
        empty = find_empty();
        neighbors = fill_neighbors_indexes(empty);
        if (neighbors[0] != invalid_index) {
            enable_button(neighbors[0]);
        }
        if (neighbors[1] != invalid_index) {
            enable_button(neighbors[1]);
        }
        if (neighbors[2] != invalid_index) {
            enable_button(neighbors[2]);
        }
        if (neighbors[3] != invalid_index) {
            enable_button(neighbors[3]);
        }

        //try winner?
        win();

    }

    protected void scramble_board (){


        Random rand = new Random();
        int total_movements = rand.nextInt(500) + 200;
        int[] invalid_index = {-1,-1};
        int[] empty_index = new int[]{-1, -1};
        int[][] neighbors = new int[][]{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}};

        for(int movements=0; movements<total_movements;movements++) {
            empty_index = new int[]{-1, -1};
            neighbors = new int[][]{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}};


            //disable all buttons
            //disable_all_buttons();

            //find blank space
            empty_index = find_empty();


            //fill neighbors
            neighbors = fill_neighbors_indexes(empty_index);

            /*
            //enable neighbor
            if (neighbors[0] != invalid_index) {
                enable_button(neighbors[0]);
            }
            if (neighbors[1] != invalid_index) {
                enable_button(neighbors[1]);
            }
            if (neighbors[2] != invalid_index) {
                enable_button(neighbors[2]);
            }
            if (neighbors[3] != invalid_index) {
                enable_button(neighbors[3]);
            }
            */

            // Log.d(TAG, "Index of  all neighbors 0 scramble " + neighbors[0][0] + " " + neighbors[0][1]);
            //Log.d(TAG, "Index of  all neighbors 1 scramble " + neighbors[1][0] + " " + neighbors[1][1]);
            //Log.d(TAG, "Index of  all neighbors 2 scramble " + neighbors[2][0] + " " + neighbors[2][1]);
            //Log.d(TAG, "Index of  all neighbors 3 scramble " + neighbors[3][0] + " " + neighbors[3][1]);


            //make a move
            move_random(neighbors, empty_index);
        }

        //disable all buttons
        disable_all_buttons();

        //find blank space
        empty_index = find_empty();


        //fill neighbors
        neighbors = fill_neighbors_indexes(empty_index);

        //enable neighbor
        if (neighbors[0] != invalid_index) {
            enable_button(neighbors[0]);
        }
        if (neighbors[1] != invalid_index) {
            enable_button(neighbors[1]);
        }
        if (neighbors[2] != invalid_index) {
            enable_button(neighbors[2]);
        }
        if (neighbors[3] != invalid_index) {
            enable_button(neighbors[3]);
        }


        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();

    }

    protected void disable_all_buttons(){
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

    protected  int[] index_from_button(Button button){
        int[] index = {-1,-1};
        //final CharSequence tag_1 = (CharSequence) bt_1.getTag();

        if(button.getTag() == bt_1.getTag()){
          return new int[] {0,0};
        }
        if(button.getTag() == bt_2.getTag()){
            return new int[] {0,1};
        }
        if(button.getTag() == bt_3.getTag()){
            return new int[] {0,2};
        }
        if(button.getTag() == bt_4.getTag()){
            return new int[] {0,3};
        }
        if(button.getTag() == bt_5.getTag()){
            return new int[] {1,0};
        }
        if(button.getTag() == bt_6.getTag()){
            return new int[] {1,1};
        }
        if(button.getTag() == bt_7.getTag()){
            return new int[] {1,2};
        }
        if(button.getTag() == bt_8.getTag()){
            return new int[] {1,3};
        }
        if(button.getTag() == bt_9.getTag()){
            return new int[] {2,0};
        }
        if(button.getTag() == bt_10.getTag()){
            return new int[] {2,1};
        }
        if(button.getTag() == bt_11.getTag()){
            return new int[] {2,2};
        }
        if(button.getTag() == bt_12.getTag()){
            return new int[] {2,3};
        }
        if(button.getTag() == bt_13.getTag()){
            return new int[] {3,0};
        }
        if(button.getTag() == bt_14.getTag()){
            return new int[] {3,1};
        }
        if(button.getTag() == bt_15.getTag()){
            return new int[] {3,2};
        }
        if(button.getTag() == bt_16.getTag()){
            return new int[] {3,3};
        }

        return index;
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
       /*
        int temp = board[empty_index[0]][empty_index[1]];
        board[empty_index[0]] [empty_index[1]] = board[neighbors[neighbor_to_move][0]][neighbors[neighbor_to_move][1]];
        board[neighbors[neighbor_to_move][0]][neighbors[neighbor_to_move][1]] = temp;
        */

        move_in_array(empty_index, neighbors[neighbor_to_move]);


        //swap button Text   === screen
        Button empty = button_from_index(empty_index);
        Button bt_movement = button_from_index(neighbors[neighbor_to_move]);
        move_button_text(empty, bt_movement);



    }

    protected void move_in_array(int[] empty_index, int[]neighbor){
        int temp = board[empty_index[0]][empty_index[1]];
        board[empty_index[0]] [empty_index[1]] = board[neighbor[0]][neighbor[1]];
        board[neighbor[0]][neighbor[1]] = temp;
    }

    protected void move_button_text(Button empty, Button move){

        String temp = (String) empty.getText();
        empty.setText(move.getText());
        move.setText(temp);
        move_button_image(empty, move);

    }

    protected void move_button_image(Button empty, Button move){
        //int image_move_button = move.getBackground();
        Drawable image_empty = empty.getBackground();
        empty.setBackground(move.getBackground());
        move.setBackground(image_empty);

    }

    protected void win (){
        final int[][] winner_board = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,0}};
        long time_elapsed;

        if (Arrays.deepEquals(winner_board, board)){
            //Toast.makeText(getApplication(), " You are a winner!", Toast.LENGTH_SHORT ).show();
            Intent winner_page = new Intent(resume_game.this, winner.class);
            chrono.stop();
            time_elapsed = SystemClock.elapsedRealtime() - chrono.getBase();
            winner_page.putExtra("Chronometer", time_elapsed);
            startActivity(winner_page);
        }


    }

    //sensor

    private final SensorEventListener sensor_listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            last_accel = current_accel;
            current_accel = (float) Math.sqrt((double) x*x + y*y + z*z);
            float delta = current_accel - last_accel;
            accel = accel * 0.9f + delta;

            if(accel>2){
                //Toast.makeText(getApplication(), "Shake!", Toast.LENGTH_SHORT).show();
                vibrate.vibrate(500);
                scramble_board();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onResume(){
        super.onResume();
        sensor.registerListener(sensor_listener, sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        sensor.unregisterListener(sensor_listener);
        super.onPause();
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
