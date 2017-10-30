package com.example.vibrant.creditcard;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardUtils;
import com.cooltechworks.creditcarddesign.CreditCardView;

public class MainActivity extends AppCompatActivity {

    CreditCardView creditCardView;
    final int GET_NEW_CARD = 2;

    Boolean bool = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button flipBt = findViewById(R.id.flipBt);

        creditCardView = findViewById(R.id.card_5);


        creditCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardEditActivity cardEditActivity = new CardEditActivity();
                Intent intent = new Intent(MainActivity.this, CardEditActivity.class);
                startActivityForResult(intent, GET_NEW_CARD);

            }
        });

        flipBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bool)
                    creditCardView.showBack();
                else
                    creditCardView.showFront();

                bool = !bool;

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == GET_NEW_CARD) {

            String cardHolderName = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
            String cardNumber = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
            String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);

            creditCardView.setCardHolderName(cardHolderName);
            creditCardView.setCardNumber(cardNumber);
            creditCardView.setCardExpiry(expiry);
            creditCardView.setCVV(cvv);


        }


    }
}
