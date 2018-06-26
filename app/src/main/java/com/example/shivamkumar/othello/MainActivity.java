package com.example.shivamkumar.othello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout rows[]= new LinearLayout[8];
    LinearLayout rootLayout;
    OthelloButton[][] board;
    int num=8;
    public int move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBoard();
       showMoves();

    }
    public void setBoard(){

        rootLayout = (LinearLayout)findViewById(R.id.boardLayout);
        board= new OthelloButton[rows.length][rows.length];

        for(int i=0;i<8;i++){
            LinearLayout linearLayout= new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(layoutParams);
            rows[i]=linearLayout;
            rootLayout.addView(linearLayout);
        }

        for(int i=0;i<rows.length;i++){
            for(int j=0;j<rows.length;j++){
                OthelloButton othelloButton = new OthelloButton(this);
                LinearLayout linearLayout = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.addView(othelloButton);
                rows[i].addView(linearLayout);
                board[i][j]=othelloButton;
            }
        }

        board[3][3].setPlayer(2);
        board[4][4].setPlayer(2);
        board[3][4].setPlayer(1);
        board[4][3].setPlayer(1);

        move=1;
        showMoves();
    }
    public void showMoves(){
        for(int i=0;i<num;i++){
            for(int j=0;j<num;j++){
                if(board[i][j].getPlayer()==move){
                    int rMove=reverseMove();
                    //check y--
                    if(checkPosition(i+1,j)){
                        if(board[i+1][j].getPlayer()==rMove){
                            int k=i+1;
                            boolean f=false;
                            while (checkPosition(k,j)){
                                if(board[k][j].getPlayer()==move)
                                    break;
                                else if(board[k][j].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[k][j].getPlayer()==-1){
                                    board[k][j].setSilver();
                                    if(f)
                                        break;
                                }
                                k++;
                            }
                        }
                    }
                    //check y++
                    if(checkPosition(i-1,j)){
                        if(board[i-1][j].getPlayer()==rMove){
                            int k=i-1;
                            boolean f=false;
                            while (checkPosition(k,j)){
                                if(board[k][j].getPlayer()==move)
                                    break;
                                else if(board[k][j].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[k][j].getPlayer()==-1){
                                    board[k][j].setSilver();
                                    if(f)
                                        break;
                                }
                                k--;
                            }
                        }
                    }

                    //check x++

                    if(checkPosition(i,j+1)){
                        if(board[i][j+1].getPlayer()==rMove){
                            int k=j+1;
                            boolean f=false;
                            while (checkPosition(i,k)){
                                if(board[i][k].getPlayer()==move)
                                    break;
                                else if(board[i][k].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[i][k].getPlayer()==-1){
                                    board[i][k].setSilver();
                                    if(f)
                                        break;
                                }
                                k++;
                            }
                        }
                    }

                    //check x--

                    if(checkPosition(i,j-1)){
                        if(board[i][j-1].getPlayer()==rMove){
                            int k=j-1;
                            boolean f=false;
                            while (checkPosition(i,k)){
                                if(board[i][k].getPlayer()==move)
                                    break;
                                else if(board[i][k].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[i][k].getPlayer()==-1){
                                    board[i][k].setSilver();
                                    if(f)
                                        break;
                                }
                                k--;
                            }
                        }
                    }

                    //check xy--

                    if(checkPosition(i-1,j-1)){
                        if(board[i-1][j-1].getPlayer()==rMove){
                            int k=j-1;int l=i-1;
                            boolean f=false;
                            while (checkPosition(i,k)){
                                if(board[l][k].getPlayer()==move)
                                    break;
                                else if(board[l][k].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[l][k].getPlayer()==-1){
                                    board[i][k].setSilver();
                                    if(f)
                                        break;
                                }
                                k--;l--;
                            }
                        }
                    }

                    //check xy++

                    if(checkPosition(i+1,j+1)){
                        if(board[i+1][j+1].getPlayer()==rMove){
                            int k=j+1;int l=i+1;
                            boolean f=false;
                            while (checkPosition(i,k)){
                                if(board[l][k].getPlayer()==move)
                                    break;
                                else if(board[l][k].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[l][k].getPlayer()==-1){
                                    board[i][k].setSilver();
                                    if(f)
                                        break;
                                }
                                k++;l++;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkPosition(int i, int j) {
        if(i>=0&&i<=num&&j>=0&&j<=num)
            return true;
        else
            return false;
    }
    private void toggleMove(){
        if(move==1)
            move=2;
        else move=1;
    }
    private int reverseMove(){
        if(move==1)
            return 2;
        else return 1;
    }
}
