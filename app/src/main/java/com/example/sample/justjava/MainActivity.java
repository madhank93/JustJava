package com.example.sample.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int initialCoffeeQuantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        if (initialCoffeeQuantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        initialCoffeeQuantity = initialCoffeeQuantity + 1;
        displayQuanity(initialCoffeeQuantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if (initialCoffeeQuantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        initialCoffeeQuantity = initialCoffeeQuantity - 1;
        displayQuanity(initialCoffeeQuantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = 0;

        // To figure out whipped cream is checked
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.checkWhippedCream);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        // To figure out chocolate is checked
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.checkChocolate);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        // To get User's name
        EditText summaryNameEditText = (EditText) findViewById(R.id.order_summary_name);
        String editTextValue = summaryNameEditText.getText().toString();

        // Calling the calculatePrice method by passing extra toppings info (choco/whip cream)
        price = calculatePrice(hasChocolate, hasWhippedCream);

        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, editTextValue);
        displayMessage(priceMessage);
        emailOrderSummary(priceMessage);

    }

    /**
     * This method is used to calculate order price.
     *
     * @param hasChoco      is whether or not user wants chocolate toppings.
     * @param hasWhippCream is whether or not user wants whipped cream.
     * @return total price of the Coffee
     */
    public int calculatePrice(Boolean hasChoco, Boolean hasWhippCream) {
        int toppingsPrice = 0;
        int oneCoffeeCupPrice = 5;

        // Add $1 if user wants whipped cream
        if (hasWhippCream) {
            toppingsPrice = toppingsPrice + 1;
        }
        // Add $2 if user wants chocolate
        if (hasChoco) {
            toppingsPrice = toppingsPrice + 2;
        }
        return (toppingsPrice + oneCoffeeCupPrice) * initialCoffeeQuantity;
    }

    /**
     * This method is used to generate order summary.
     */
    public String createOrderSummary(int totalPrice, boolean hasWhippedCream,
                                     boolean hasChocolate, String editTextValue) {
        return "Name: " + editTextValue
                + "\nAdd whipped cream? " + hasWhippedCream
                + "\nAdd chocolate? " + hasChocolate
                + "\nQuantity: " + initialCoffeeQuantity
                + "\nTotal: " + "\u20B9" + " " + totalPrice
                + "\nThank You!";
    }

    /**
     * This method displays the total price on the screen.
     */

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuanity(int numberofCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberofCoffees);
    }

    /**
     * This method is used to set the oder summary contents in email app.
     */
    public void emailOrderSummary(String priceMessage) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:starmugs@coffee.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee - Order summary");
        emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }

    }
}