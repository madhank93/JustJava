package com.example.sample.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int initialCoffeeQuanity = 2;
    private int oneCoffeeCupPrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the increment button is clicked.
     */

    public void increment(View view) {
        initialCoffeeQuanity = initialCoffeeQuanity + 1;
        displayQuanity(initialCoffeeQuanity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */

    public void decrement(View view) {
        if (initialCoffeeQuanity > 1) {
            initialCoffeeQuanity = initialCoffeeQuanity - 1;
            displayQuanity(initialCoffeeQuanity);
        }
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view){
        String priceMessage = String.valueOf(initialCoffeeQuanity * oneCoffeeCupPrice);
        displayMessage(priceMessage);
        //displayPrice(initialCoffeeQuanity * oneCoffeeCupPrice);
    }

    /**
     * This method displays the total price on the screen.
     */

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Total: " + "\u20B9" + " "+ message + "\nThank You!");
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuanity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}
