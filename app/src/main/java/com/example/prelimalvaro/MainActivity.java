package com.example.prelimalvaro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int balance = 1000;
    private int betAmount = 0;
    private int multiplier = 2;

    private TextView BALANCETV;
    private EditText BETAmountET;
    private EditText BET1ET;
    private EditText BET2ET;
    private EditText BET3ET;
    private TextView R1TEXTview;
    private TextView R2TEXTview;
    private TextView R3TEXTview;
    private TextView RESTEXTview;
    private TextView MULTITextView;
    private Button SETbutton;
    private Button PLAYbutton;
    private Button RESbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BALANCETV = findViewById(R.id.BALANCETV);
        BETAmountET = findViewById(R.id.BETAmountET);
        BET1ET = findViewById(R.id.BET1ET);
        BET2ET = findViewById(R.id.BET2ET);
        BET3ET = findViewById(R.id.BET3ET);

        R1TEXTview = findViewById(R.id.R1TEXTview);
        R2TEXTview = findViewById(R.id.R2TEXTview);
        R3TEXTview = findViewById(R.id.R3TEXTview);
        RESTEXTview = findViewById(R.id.RESTEXTview);
        MULTITextView = findViewById(R.id.MULTITextView);
        
        SETbutton = findViewById(R.id.SETbutton);
        PLAYbutton = findViewById(R.id.PLAYbutton);
        RESbutton = findViewById(R.id.RESbutton);

        SETbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                setBet();
            }
        });

        PLAYbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                play();
            }
        });

        RESbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                resetGame();
            }
        });

        SETbutton.setVisibility(View.VISIBLE);

    }

    private void setBet() {

        String betAmountStr = BETAmountET.getText().toString();
        String bet1Str = BET1ET.getText().toString();
        String bet2Str = BET2ET.getText().toString();
        String bet3Str = BET3ET.getText().toString();

        if(balance == 0) {
            PLAYbutton.setVisibility(View.GONE);
            SETbutton.setVisibility(View.GONE);
        }

        if (betAmountStr.isEmpty() || bet1Str.isEmpty() || bet2Str.isEmpty() || bet3Str.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter a value", Toast.LENGTH_SHORT).show();
            BET1ET.requestFocus();
            BET2ET.requestFocus();
            BET3ET.requestFocus();
            BETAmountET.requestFocus();
            return;
        }

        int betAmountValue = Integer.parseInt(betAmountStr);
        int bet1Value = Integer.parseInt(bet1Str);
        int bet2Value = Integer.parseInt(bet2Str);
        int bet3Value = Integer.parseInt(bet3Str);

        if (betAmountValue > balance || bet1Value > 9 || bet2Value > 9 || bet3Value > 9) {
            Toast.makeText(getApplicationContext(), "Value is too High.", Toast.LENGTH_SHORT).show();
            BET1ET.requestFocus();
            BET2ET.requestFocus();
            BET3ET.requestFocus();
            BETAmountET.requestFocus();
            return;
        }

        betAmount = betAmountValue;
        MULTITextView.setText("Multiplier: x" + multiplier);
        BETAmountET.setText("");
        BET1ET.setText("");
        BET2ET.setText("");
        BET3ET.setText("");
        SETbutton.setVisibility(View.GONE);
        PLAYbutton.setVisibility(View.VISIBLE);

    }

    private void play() {

        if (betAmount == 0) {
            return;
        }

        if(balance == 0) {
            PLAYbutton.setVisibility(View.GONE);
            SETbutton.setVisibility(View.GONE);
        }

        Random random = new Random();
        double randomNumber = random.nextDouble();
        double winChance = 0.25;

        if (randomNumber < winChance) {
            balance = balance + betAmount * multiplier;
            multiplier ++;
            RESTEXTview.setText("You WIN!");
        } else {
            balance = balance - betAmount;
            multiplier = 2;
            RESTEXTview.setText("You LOSE!");
        }

        R1TEXTview.setText(String.valueOf(random.nextInt(10)));
        R2TEXTview.setText(String.valueOf(random.nextInt(10)));
        R3TEXTview.setText(String.valueOf(random.nextInt(10)));

        BALANCETV.setText("Balance: $" + balance);
        MULTITextView.setText("Multiplier: " + multiplier);
        SETbutton.setVisibility(View.VISIBLE);
        PLAYbutton.setVisibility(View.GONE);

    }

    private void resetGame() {

        balance = 1000;
        multiplier = 2;
        BALANCETV.setText("Balance: $" + balance);
        R1TEXTview.setText("");
        R2TEXTview.setText("");
        R3TEXTview.setText("");
        RESTEXTview.setText("");
        MULTITextView.setText("");
        PLAYbutton.setVisibility(View.GONE);
        SETbutton.setVisibility(View.VISIBLE);

    }
}