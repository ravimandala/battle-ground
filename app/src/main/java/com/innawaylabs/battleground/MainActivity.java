package com.innawaylabs.battleground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final int POSITION = 101;
    private static int board[] = {
            1, 1, 2, 2, 2, 0, 0, 0,
            1, 1, 0, 2, 2, 0, 0, 1,
            1, 1, 2, 2, 2, 2, 0, 1};

    // TODO: Optimize the way the seekbars are seeked
    private static SeekBar positions[] = new SeekBar[24];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        positions[0] = (SeekBar) findViewById(R.id.seekBar0);
        positions[1] = (SeekBar) findViewById(R.id.seekBar1);
        positions[2] = (SeekBar) findViewById(R.id.seekBar2);
        positions[3] = (SeekBar) findViewById(R.id.seekBar3);
        positions[4] = (SeekBar) findViewById(R.id.seekBar4);
        positions[5] = (SeekBar) findViewById(R.id.seekBar5);
        positions[6] = (SeekBar) findViewById(R.id.seekBar6);
        positions[7] = (SeekBar) findViewById(R.id.seekBar7);
        positions[8] = (SeekBar) findViewById(R.id.seekBar8);
        positions[9] = (SeekBar) findViewById(R.id.seekBar9);
        positions[10] = (SeekBar) findViewById(R.id.seekBar10);
        positions[11] = (SeekBar) findViewById(R.id.seekBar11);
        positions[12] = (SeekBar) findViewById(R.id.seekBar12);
        positions[13] = (SeekBar) findViewById(R.id.seekBar13);
        positions[14] = (SeekBar) findViewById(R.id.seekBar14);
        positions[15] = (SeekBar) findViewById(R.id.seekBar15);
        positions[16] = (SeekBar) findViewById(R.id.seekBar16);
        positions[17] = (SeekBar) findViewById(R.id.seekBar17);
        positions[18] = (SeekBar) findViewById(R.id.seekBar18);
        positions[19] = (SeekBar) findViewById(R.id.seekBar19);
        positions[20] = (SeekBar) findViewById(R.id.seekBar20);
        positions[21] = (SeekBar) findViewById(R.id.seekBar21);
        positions[22] = (SeekBar) findViewById(R.id.seekBar22);
        positions[23] = (SeekBar) findViewById(R.id.seekBar23);

        for (int i=0; i<positions.length; i++) {
            positions[i].setTag(new Integer(i));
            positions[i].setOnSeekBarChangeListener(this);
        }
        setBoard();

        Toast.makeText(this, "Attack status on position #9 is " + isInAttack(9), Toast.LENGTH_SHORT).show();
    }

    private void setBoard() {
        for (int i = 0; i < 24; i++) {
            positions[i].setProgress(board[i]);
        }
    }


    public static boolean isInAttack(int i) {
        if (i < 0 || i > 23) return false;

        int grid = i / 8;
        if (i % 2 == 0) {
            return isTripletInAttack(i, (grid * 8) + (i + 1) % 8, (grid * 8) + (i + 2) % 8) ||
                    isTripletInAttack(i, (grid * 8) + (i + 8 - 1) % 8, (grid * 8) + (i + 8 - 2) % 8);
        } else {
            return isTripletInAttack(i, (grid * 8) + (i + 1) % 8, (grid * 8) + (i + 8 - 1) % 8) ||
                    isTripletInAttack(i % 8, 8 + i % 8, 16 + i % 8);
        }
    }

    public static boolean isTripletInAttack(int t1, int t2, int t3) {
        //  System.out.println("Checking triplet: " + t1 + ", " + t2 + ", " + t3);
        if (t1 < 0 || t1 > 23 || t2 < 0 || t2 > 23 || t3 < 0 || t3 > 23) {
            System.err.println("Invalid inputs");
            return false;
        }
        return (board[t1] != 0 && board[t1] == board[t2] && board[t2] == board[t3]);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int position = ((Integer)seekBar.getTag()).intValue();
        board[position] = seekBar.getProgress();

        Toast.makeText(this,
                "Is position#" + position + " is in attack = " + isInAttack(position),
                Toast.LENGTH_SHORT).show();
    }

//    public static void assertEquals(int position, boolean actual, boolean expected) {
//        System.out.println("Test " + (expected != actual ? "failed" : "passed") + " for " + position);
//    }
}
