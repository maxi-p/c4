package com.zybooks.connectfour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class BoardFragment extends Fragment {
    public static final String GAME_STATE = "gameState";
    public ConnectFourGame mGame;
    public GridLayout mGrid;
    private Thread mBackgroundThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_board, container, false);
        mGrid = parentView.findViewById(R.id.ball_grid);
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            Button gridButton = (Button) mGrid.getChildAt(i);
            gridButton.setOnClickListener(this::onButtonClick);
        }
        mGame = new ConnectFourGame();

        if (savedInstanceState == null) {
            startGame();
        }
        else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setState(gameState);
            setDisc();
        }

        return parentView;
    }

    //stub method.
    private void onButtonClick(View view) {
        int buttonIndex = mGrid.indexOfChild(view);
        int row = buttonIndex / mGame.COL;
        int col = buttonIndex % mGame.COL;
        Log.i("BoardFragment", ""+buttonIndex);
        Log.i("BOARD_STATE: before", ""+mGame.getState());
        mGame.selectDisc(row, col);
        Log.i("BOARD_STATE: after", ""+mGame.getState());
        setDisc();


        if (mGame.isGameOver()) {

            mBackgroundThread = new Thread(() -> {
                try {
                        // Delay for one second
                        Thread.sleep(2000);
                        Message msg = mThreadHandler.obtainMessage();
                        msg.arg1 = 1;
                        mThreadHandler.sendMessage(msg);
                }
                catch (InterruptedException e) {
                    Log.i("THREAD", "Thread was interrupted");
                }
            });

            String message;
            if(mGame.isBlueWin())
                message = new String("Congrats, Blue player!");
            else if(mGame.isRedWin()){
                message = new String("Congrats, Red player!");
            }
            else{
                message = new String("It's a draw! The board is full...");
            }
            Toast.makeText(this.requireActivity(), message, Toast.LENGTH_SHORT).show();
            mBackgroundThread.start();


        }

    }
    // Handler for the main thread
    private Handler mThreadHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                mGame.newGame();
                setDisc();
        }
    };

    private void startGame(){
        mGame.newGame();
        setDisc();
    }

    private void setDisc(){


        for (int buttonIndex = 0; buttonIndex < mGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mGrid.getChildAt(buttonIndex);
            //Log.i("Looping SET COLOR", ""+buttonIndex);
            //Log.i("Looping SET COLOR", ""+buttonIndex);
            int row = buttonIndex / mGame.COL;
            int col = buttonIndex % mGame.COL;
            Drawable empty = ContextCompat.getDrawable(getActivity(), R.drawable.circle_white);
            Drawable red = ContextCompat.getDrawable(getActivity(), R.drawable.circle_red);
            Drawable blue = ContextCompat.getDrawable(getActivity(), R.drawable.circle_blue);
            empty = DrawableCompat.wrap(empty);
            red = DrawableCompat.wrap(red);
            blue = DrawableCompat.wrap(blue);

            int val = mGame.getDisc(row,col);
            Log.i("COLORS", buttonIndex+": "+val+" ("+row+", "+col+")");
            if(val == mGame.BLUE){
                gridButton.setBackground(blue);
                Log.i("SET COLOR", "BLUE");
            }
            else if(val == mGame.RED){
                gridButton.setBackground(red);
                Log.i("SET COLOR", "RED");
            }
            else if(val == mGame.EMPTY){
                gridButton.setBackground(empty);
            }
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }
}