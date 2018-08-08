package com.example.sample.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private int initialCoffeeQuantity = 2;
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
		initialCoffeeQuantity = initialCoffeeQuantity + 1;
		displayQuanity(initialCoffeeQuantity);
	}

	/**
	 * This method is called when the decrement button is clicked.
	 */
	public void decrement(View view) {
		if (initialCoffeeQuantity > 1) {
			initialCoffeeQuantity = initialCoffeeQuantity - 1;
			displayQuanity(initialCoffeeQuantity);
		}
	}

	/**
	 * This method is called when the order button is clicked.
	 */
	public void submitOrder(View view) {
		int price = calculatePrice();

		CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.checkWhippedCream);
		boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.checkChocolate);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        EditText summaryNameEditText = (EditText) findViewById(R.id.order_summary_name);
        String editTextValue = summaryNameEditText.getText().toString();

		String priceMessage = createOrderSummary(price,hasWhippedCream,hasChocolate,editTextValue);
		displayMessage(priceMessage);
	}

	/**
	 * This method is used to calculate coffee price.
	 *
	 * @return total price of the Coffee
	 */
	public int calculatePrice() {
		return initialCoffeeQuantity * oneCoffeeCupPrice;
	}

	/**
	 * This method is used to generate order summary.
	 */
	public String createOrderSummary(int totalPrice, boolean hasWhippedCream,
                                     boolean hasChocolate, String editTextValue) {
		return "Name: " + editTextValue
				+ "\nAdd whipped cream? " + hasWhippedCream
                + "\nAdd chocolate? " + hasChocolate
				+ "\nQunatity: " + initialCoffeeQuantity
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

}
