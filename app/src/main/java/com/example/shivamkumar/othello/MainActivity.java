package com.example.shivamkumar.othello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout rows[]= new LinearLayout[8];
    LinearLayout rootLayout;
    OthelloButton[][] board;
    TextView textViewMove;
    TextView blackCount;
    TextView whiteCount;
    int num=8;
    public int move;
    int countClickable=1;
    int movableCount;
    boolean flagWin=false;
    int countWhite;
    int countBlack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMove=findViewById(R.id.currentMove);
        blackCount=findViewById(R.id.blackCount);
        whiteCount=findViewById(R.id.whiteCount);
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
                othelloButton.setOnClickListener(this);
                othelloButton.setCoordinat(i,j);
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
        movableCount=0;
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
                                    board[k][j].clickable=countClickable;
                                    movableCount++;
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
                                    board[k][j].clickable=countClickable;
                                    movableCount++;
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
                                    board[i][k].clickable=countClickable;
                                    movableCount++;
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
                                    board[i][k].clickable=countClickable;
                                    movableCount++;
                                    if(f)
                                        break;
                                }
                                k--;
                            }
                        }
                    }

                    //check xy--2

                    if(checkPosition(i-1,j-1)){
                        if(board[i-1][j-1].getPlayer()==rMove){
                            int k=j-1;int l=i-1;
                            boolean f=false;
                            while (checkPosition(l,k)){
                                if(board[l][k].getPlayer()==move)
                                    break;
                                else if(board[l][k].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[l][k].getPlayer()==-1){
                                    board[l][k].setSilver();
                                    board[l][k].clickable=countClickable;
                                    movableCount++;
                                    if(f)
                                        break;
                                }
                                k--;l--;
                            }
                        }
                    }

                    //check xy++4

                    if(checkPosition(i+1,j+1)){
                        if(board[i+1][j+1].getPlayer()==rMove){
                            int k=j+1;int l=i+1;
                            boolean f=false;
                            while (checkPosition(l,k)){
                                if(board[l][k].getPlayer()==move)
                                    break;
                                else if(board[l][k].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[l][k].getPlayer()==-1){
                                    board[l][k].setSilver();
                                    board[l][k].clickable=countClickable;
                                    movableCount++;
                                    if(f)
                                        break;
                                }
                                k++;l++;
                            }
                        }
                    }



                    //xy++1

                    if(checkPosition(i-1,j+1)){
                        if(board[i-1][j+1].getPlayer()==rMove){
                            int k=j+1;int l=i-1;
                            boolean f=false;
                            while (checkPosition(l,k)){
                                if(board[l][k].getPlayer()==move)
                                    break;
                                else if(board[l][k].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[l][k].getPlayer()==-1){
                                    board[l][k].setSilver();
                                    board[l][k].clickable=countClickable;
                                    movableCount++;
                                    if(f)
                                        break;
                                }
                                k++;l--;
                            }
                        }
                    }


                    //xy--3

                    if(checkPosition(i+1,j-1)){
                        if(board[i+1][j-1].getPlayer()==rMove){
                            int k=j-1;int l=i+1;
                            boolean f=false;
                            while (checkPosition(l,k)){
                                if(board[l][k].getPlayer()==move)
                                    break;
                                else if(board[l][k].getPlayer()==rMove)
                                {
                                    f=true;
                                }
                                else if(board[l][k].getPlayer()==-1){
                                    board[l][k].setSilver();
                                    board[l][k].clickable=countClickable;
                                    movableCount++;
                                    if(f)
                                        break;
                                }
                                k--;l++;
                            }
                        }
                    }
                }
            }
        }
    }
    public void unShowMoves(){
        for(int i=0;i<num;i++){
            for(int j=0;j<num;j++){
                if(board[i][j].isShowingMove)
                    board[i][j].unsetSilver();
            }
        }
    }
    public boolean checkPosition(int i, int j) {
        if(i>=0&&i<num&&j>=0&&j<num)
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

    @Override
    public void onClick(View view) {
        if(flagWin){
            gameOver();
            return;
        }
        OthelloButton button=(OthelloButton)view;
        if(button.clickable!=countClickable)
            return;
        unShowMoves();
        int x=button.x;
        int y= button.y;
        gamePlay(x,y);
        toggleMove();
        countClickable++;
        showMoves();
        if(movableCount==0){
            toggleMove();
            showMoves();
            if(movableCount==0)
                gameOver();
        }
        if(move==1)
            textViewMove.setText("Player 1 (Black)\n Move");
        else
            textViewMove.setText("Player 2 (White)\n Move");
        countPieces();
        blackCount.setText(countBlack+"\nBlack");
        whiteCount.setText(countWhite+"\nWhite");
        if(countBlack>countWhite){
            blackCount.setBackgroundColor(getResources().getColor(R.color.yellow,null));
            whiteCount.setBackgroundColor(getResources().getColor(R.color.red,null));
        }else if(countWhite>countBlack){
            whiteCount.setBackgroundColor(getResources().getColor(R.color.yellow,null));
            blackCount.setBackgroundColor(getResources().getColor(R.color.red,null));
        }
        else{
            blackCount.setBackgroundColor(getResources().getColor(R.color.greenDefault,null));
            whiteCount.setBackgroundColor(getResources().getColor(R.color.greenDefault,null));
        }
    }
    public void countPieces(){
        countWhite=0;
        countBlack=0;
        for(int i=0;i<num;i++){
            for(int j=0;j<num;j++){
                if(board[i][j].getPlayer()==1)
                    countBlack++;
                else if(board[i][j].getPlayer()==2)
                    countWhite++;
            }
        }
    }
    public void gameOver(){
        countPieces();
        if(countWhite<countBlack){
            Toast.makeText(this,"Player 1 (Black) Wins",Toast.LENGTH_LONG).show();
        }else if(countBlack<countWhite)
            Toast.makeText(this,"Player 2 (White) Wins",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Draw",Toast.LENGTH_LONG).show();
        flagWin=true;
    }
    private void gamePlay(int i, int j) {
        int rMove=reverseMove();

        //check x++
        if(checkPosition(i,j+1)){
            if(board[i][j+1].getPlayer()==rMove){
                ArrayList<OthelloButton> arrayList= new ArrayList<>(8);
                arrayList.add(board[i][j]);
                int k=j+1;
                boolean f=false;
                while (checkPosition(i,k)){
                    int x=board[i][k].getPlayer();
                    if(x==move){
                        if(!f)
                            break;
                        for(int z=0;z<arrayList.size();z++){
                            arrayList.get(z).setPlayer(move);
                        }
                        break;
                    }
                    if(x==rMove){
                        arrayList.add(board[i][k]);
                        k++;
                        f=true;
                        continue;
                    }else if(x==-1){
                        break;
                    }
                }
            }
        }

        //check x--

        if(checkPosition(i,j-1)){
            if(board[i][j-1].getPlayer()==rMove){
                ArrayList<OthelloButton> arrayList= new ArrayList<>(8);
                arrayList.add(board[i][j]);
                int k=j-1;
                boolean f=false;
                while (checkPosition(i,k)){
                    int x=board[i][k].getPlayer();
                    if(x==move){
                        if(!f)
                            break;
                        for(int z=0;z<arrayList.size();z++){
                            arrayList.get(z).setPlayer(move);
                        }
                        break;
                    }
                    if(x==rMove){
                        arrayList.add(board[i][k]);
                        k--;
                        f=true;
                        continue;
                    }else if(x==-1){
                        break;
                    }
                }
            }
        }
        //check y++
        if(checkPosition(i+1,j)){
            if(board[i+1][j].getPlayer()==rMove){
                ArrayList<OthelloButton> arrayList= new ArrayList<>(8);
                arrayList.add(board[i][j]);
                int k=i+1;
                boolean f=false;
                while (checkPosition(k,j)){
                    int x=board[k][j].getPlayer();
                    if(x==move){
                        if(!f)
                            break;
                        for(int z=0;z<arrayList.size();z++){
                            arrayList.get(z).setPlayer(move);
                        }
                        break;
                    }
                    if(x==rMove){
                        arrayList.add(board[k][j]);
                        k++;
                        f=true;
                        continue;
                    }else if(x==-1){
                        break;
                    }
                }
            }
        }





        //check y--
        if(checkPosition(i-1,j)){
            if(board[i-1][j].getPlayer()==rMove){
                ArrayList<OthelloButton> arrayList= new ArrayList<>(8);
                arrayList.add(board[i][j]);
                int k=i-1;
                boolean f=false;
                while (checkPosition(k,j)){
                    int x=board[k][j].getPlayer();
                    if(x==move){
                        if(!f)
                            break;
                        for(int z=0;z<arrayList.size();z++){
                            arrayList.get(z).setPlayer(move);
                        }
                        break;
                    }
                    if(x==rMove){
                        arrayList.add(board[k][j]);
                        k--;
                        f=true;
                        continue;
                    }else if(x==-1){
                        break;
                    }
                }
            }
        }

        //check xy++4

        if(checkPosition(i+1,j+1)){
            if(board[i+1][j+1].getPlayer()==rMove){
                ArrayList<OthelloButton> arrayList= new ArrayList<>(8);
                arrayList.add(board[i][j]);
                int k=j+1; int l=i+1;
                boolean f=false;
                while (checkPosition(l,k)){
                    int x=board[l][k].getPlayer();
                    if(x==move){
                        if(!f)
                            break;
                        for(int z=0;z<arrayList.size();z++){
                            arrayList.get(z).setPlayer(move);
                        }
                        break;
                    }
                    if(x==rMove){
                        arrayList.add(board[l][k]);
                        k++;l++;
                        f=true;
                        continue;
                    }else if(x==-1){
                        break;
                    }
                }
            }
        }

        //check xy--2

        if(checkPosition(i-1,j-1)){
            if(board[i-1][j-1].getPlayer()==rMove){
                ArrayList<OthelloButton> arrayList= new ArrayList<>(8);
                arrayList.add(board[i][j]);
                int k=j-1; int l=i-1;
                boolean f=false;
                while (checkPosition(l,k)){
                    int x=board[l][k].getPlayer();
                    if(x==move){
                        if(!f)
                            break;
                        for(int z=0;z<arrayList.size();z++){
                            arrayList.get(z).setPlayer(move);
                        }
                        break;
                    }
                    if(x==rMove){
                        arrayList.add(board[l][k]);
                        k--;l--;
                        f=true;
                        continue;
                    }else if(x==-1){
                        break;
                    }
                }
            }
        }

        //xy++1

        if(checkPosition(i-1,j+1)){
            if(board[i-1][j+1].getPlayer()==rMove){
                ArrayList<OthelloButton> arrayList= new ArrayList<>(8);
                arrayList.add(board[i][j]);
                int k=j+1; int l=i-1;
                boolean f=false;
                while (checkPosition(l,k)){
                    int x=board[l][k].getPlayer();
                    if(x==move){
                        if(!f)
                            break;
                        for(int z=0;z<arrayList.size();z++){
                            arrayList.get(z).setPlayer(move);
                        }
                        break;
                    }
                    if(x==rMove){
                        arrayList.add(board[l][k]);
                        k++;l--;
                        f=true;
                        continue;
                    }else if(x==-1){
                        break;
                    }
                }
            }
        }

        //xy--3

        if(checkPosition(i+1,j-1)){
            if(board[i+1][j-1].getPlayer()==rMove){
                ArrayList<OthelloButton> arrayList= new ArrayList<>(8);
                arrayList.add(board[i][j]);
                int k=j-1; int l=i+1;
                boolean f=false;
                while (checkPosition(l,k)){
                    int x=board[l][k].getPlayer();
                    if(x==move){
                        if(!f)
                            break;
                        for(int z=0;z<arrayList.size();z++){
                            arrayList.get(z).setPlayer(move);
                        }
                        break;
                    }
                    if(x==rMove){
                        arrayList.add(board[l][k]);
                        k--;l++;
                        f=true;
                        continue;
                    }else if(x==-1){
                        break;
                    }
                }
            }
        }

    }
}
