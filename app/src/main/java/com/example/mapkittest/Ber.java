package com.example.mapkittest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Ber extends AppCompatActivity {

    private final static int[] blocks = {
            R.id.blockone,
            R.id.blocktwo,
            R.id.blockthree,
            R.id.blockfour,
            R.id.blockone2,
            R.id.blocktwo2,
            R.id.blockthree2,
            R.id.blockfour2,
            R.id.blockone3,
            R.id.blocktwo3,
            R.id.blockthree3,
            R.id.blockfour3,
            R.id.blockone4,
            R.id.blocktwo4,
            R.id.blockthree4,
            R.id.blockfour4,
            R.id.blockone5,
            R.id.blocktwo5,
            R.id.blockthree5,
            R.id.blockfour5,
            R.id.blockone6,
            R.id.blocktwo6,
            R.id.blockthree6,
            R.id.blockfour6,
            R.id.blockone7,
            R.id.blocktwo7
    };

    private final static int[] buttons = {
            R.id.b1,
            R.id.b2,
            R.id.b3,
            R.id.b4,
            R.id.b5,
            R.id.b6,
            R.id.b7
    };

    private int currentButton = 1;

    private final static int BUTTONS_AMOUNT = 4,
            BLOCKS_AMOUNT = 4;

    Button sarrow, farrow, sarrow2, farrow2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ber2);
        proceedButtons();
    }

    private void proceedButtons() {
        sarrow = findViewById(R.id.sarrow);
        sarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVisibility(1,BUTTONS_AMOUNT);
                changeButtonsVisibility(1, BUTTONS_AMOUNT);
                currentButton = 1;
            }
        });
        sarrow2 = findViewById(R.id.sarrow2);
        sarrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentButton > 1)
                    if (currentButton-2+ BUTTONS_AMOUNT>buttons.length)
                        changeButtonsVisibility(buttons.length - BUTTONS_AMOUNT, buttons.length);
                    else
                        changeButtonsVisibility(currentButton-1, currentButton-2+ BUTTONS_AMOUNT);
                else if (currentButton == 1)
                    changeButtonsVisibility(1,BUTTONS_AMOUNT);
                if (currentButton>=2) {
                    changeVisibility((currentButton-2)*BLOCKS_AMOUNT+1, (currentButton-1)*BLOCKS_AMOUNT);
                    currentButton-=1;
                }
            }
        });
        farrow = findViewById(R.id.farrow);
        farrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blocks.length%BLOCKS_AMOUNT == 0)
                    changeVisibility(blocks.length - BLOCKS_AMOUNT + 1, blocks.length);
                else
                    changeVisibility(blocks.length - blocks.length%BLOCKS_AMOUNT + 1, blocks.length);
                changeButtonsVisibility(buttons.length - BUTTONS_AMOUNT + 1, buttons.length);
                currentButton = buttons.length;
            }
        });
        farrow2 = findViewById(R.id.farrow2);
        farrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentButton == 1)
                    changeButtonsVisibility(1, BUTTONS_AMOUNT);
                else if (currentButton < buttons.length-1)
                    changeButtonsVisibility(currentButton-1, currentButton-2+ BUTTONS_AMOUNT);
                else if (currentButton == buttons.length || currentButton == buttons.length-1)
                    changeButtonsVisibility(buttons.length - BUTTONS_AMOUNT + 1,buttons.length);
                if (currentButton<buttons.length) {
                    if ((currentButton+1)*BLOCKS_AMOUNT > blocks.length)
                        changeVisibility(currentButton*BLOCKS_AMOUNT + 1, blocks.length);
                    else
                        changeVisibility(currentButton*BLOCKS_AMOUNT + 1, (currentButton+1)*BLOCKS_AMOUNT);
                    currentButton+=1;
                }
            }
        });
        for (int i = 0; i < buttons.length; i++) {
            int currIndex = i;
            Button btn = findViewById(buttons[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (blocks.length < (currIndex+1)*BLOCKS_AMOUNT) {
                        changeVisibility(currIndex*BLOCKS_AMOUNT+1, blocks.length);
                    } else {
                        changeVisibility(currIndex*BLOCKS_AMOUNT+1, (currIndex+1)*BLOCKS_AMOUNT);
                    }
                    if (currIndex == 0) {
                        changeButtonsVisibility(currIndex+1, currIndex+BUTTONS_AMOUNT);
                    } else if (currIndex < buttons.length-2) {
                        changeButtonsVisibility(currIndex, currIndex+BUTTONS_AMOUNT-1);
                    } else if (currIndex == buttons.length-2) {

                    } else if (currIndex == buttons.length-1) {
                        changeButtonsVisibility(currIndex+1, currIndex+ BUTTONS_AMOUNT);
                    }
                    currentButton = currIndex+1;
                }
            });
        }
    }

    private void changeVisibility(int s, int f) {
        for (int i = 0; i < s-1; i++) {
            View v = findViewById(blocks[i]);
            v.setVisibility(View.GONE);
        }
        for (int i = s-1; i < f; i++) {
            View v = findViewById(blocks[i]);
            v.setVisibility(View.VISIBLE);
        }
        for (int i = f; i < blocks.length; i++) {
            View v = findViewById(blocks[i]);
            v.setVisibility(View.GONE);
        }
    }

    private void changeButtonsVisibility(int s, int f) {
        for (int i = 0; i < s-1; i++) {
            Button b = findViewById(buttons[i]);
            b.setVisibility(Button.GONE);
        }
        for (int i = s-1; i < f; i++) {
            Button b = findViewById(buttons[i]);
            b.setVisibility(View.VISIBLE);
        }
        for (int i = f; i < buttons.length; i++) {
            Button b = findViewById(buttons[i]);
            b.setVisibility(Button.GONE);
        }
    }
}