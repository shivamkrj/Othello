package com.example.shivamkumar.othello;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class OthelloButton extends AppCompatButton {
    private int player;
    public boolean isShowingMove=false;
    public int getPlayer() {
        return player;
    }
    public void setPlayer(int player) {
        this.player = player;
        if(player==1)
            setBlack();
        else if(player==2)
            setWhite();
        else if(player==0)
            setSilver();
    }
    public int clickable=0;
    public int x;
    public int y;

    public OthelloButton(Context context) {
        super(context);
       // setBackgroundColor(getResources().getColor(R.color.greenDefault));
        player = -1;
        setBackgroundDrawable(getResources().getDrawable(R.drawable.greenbutton,null));
    }
    public void setBlack(){
        setBackgroundDrawable(getResources().getDrawable(R.drawable.black_button,null));
    }
    public void setWhite(){
        setBackgroundDrawable(getResources().getDrawable(R.drawable.white_button,null));
    }
    public void setSilver(){
        setBackgroundDrawable(getResources().getDrawable(R.drawable.moves_available_button,null));
        isShowingMove=true;
    }
    public void unsetSilver(){
        setBackgroundDrawable(getResources().getDrawable(R.drawable.greenbutton,null));
        isShowingMove=false;
    }
    public void setCoordinat(int i, int j) {
        x=i;
        y=j;
    }
}
