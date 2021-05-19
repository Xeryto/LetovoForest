package com.example.mapkittest;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Sosna extends AppCompatActivity {

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
            R.id.blockfour3
    };

    private final static int[] buttons = {
            R.id.b1,
            R.id.b2,
            R.id.b3
    };

    private int currentButton = 1;

    private final static int BUTTONS_AMOUNT = 3,
            BLOCKS_AMOUNT = 4;

    Button sarrow, farrow, sarrow2, farrow2, contacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosna);
        proceedButtons();
    }

    private void proceedButtons() {
        contacts = findViewById(R.id.contacts);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Contacts.class);
                startActivity(intent);
            }
        });
        contacts.setBackgroundColor(Color.GREEN);
        sarrow = findViewById(R.id.sarrow);
        sarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVisibility(1,BUTTONS_AMOUNT);
                changeButtonsVisibility(1, BUTTONS_AMOUNT);
                currentButton = 1;
                for (int i = 0; i < buttons.length; i++) {
                    Button btn = findViewById(buttons[i]);
                    btn.setBackgroundColor(Color.GREEN);
                }
                Button curBtn = findViewById(buttons[currentButton-1]);
                curBtn.setBackgroundColor(Color.BLACK);
            }
        });
        sarrow.setBackgroundColor(Color.GREEN);
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
                    Button lastBtn = findViewById(buttons[currentButton-1]);
                    lastBtn.setBackgroundColor(Color.GREEN);
                    currentButton-=1;
                    Button curBtn = findViewById(buttons[currentButton-1]);
                    curBtn.setBackgroundColor(Color.BLACK);
                }
            }
        });
        sarrow2.setBackgroundColor(Color.GREEN);
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
                for (int i = 0; i < buttons.length; i++) {
                    Button btn = findViewById(buttons[i]);
                    btn.setBackgroundColor(Color.GREEN);
                }
                Button curBtn = findViewById(buttons[currentButton-1]);
                curBtn.setBackgroundColor(Color.BLACK);
            }
        });
        farrow.setBackgroundColor(Color.GREEN);
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
                    Button lastBtn = findViewById(buttons[currentButton-1]);
                    lastBtn.setBackgroundColor(Color.GREEN);
                    currentButton+=1;
                    Button curBtn = findViewById(buttons[currentButton-1]);
                    curBtn.setBackgroundColor(Color.BLACK);
                }
            }
        });
        farrow2.setBackgroundColor(Color.GREEN);
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
                    } else if (currIndex == buttons.length-2 || currIndex == buttons.length-1) {
                        changeButtonsVisibility(buttons.length - BUTTONS_AMOUNT+1, buttons.length);
                    }
                    currentButton = currIndex+1;
                    for (int i = 0; i < buttons.length; i++) {
                        Button btn = findViewById(buttons[i]);
                        btn.setBackgroundColor(Color.GREEN);
                    }
                    Button curBtn = findViewById(buttons[currentButton-1]);
                    curBtn.setBackgroundColor(Color.BLACK);
                }
            });
            if (i == 0)
                btn.setBackgroundColor(Color.BLACK);
            else
                btn.setBackgroundColor(Color.GREEN);
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