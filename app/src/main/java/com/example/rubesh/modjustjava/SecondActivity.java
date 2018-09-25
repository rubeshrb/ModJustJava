package com.example.rubesh.modjustjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    int quantity = 1;


    /** Display the quantity
     *
     * @param
     */

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantityTextView.setText("" + number);

    }



    private void displayMessage(String message) {
        TextView OrderSummaryTextView = (TextView) findViewById(R.id.Order_summary_view);
        OrderSummaryTextView.setText("" + message);

    }


    public void decrease(View view) {

        if(quantity == 1)
        {
            Toast.makeText(this,"Must be Atleast One Order",Toast.LENGTH_SHORT).show();

            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    public void increase(View view) {

        if (quantity == 100)
        {
            Toast.makeText(this,"You Cannot Have more than 100 Coffes",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;

        displayQuantity(quantity);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // First calculate the price of one cup of coffee
        int basePrice = 5;

        // If the user wants whipped cream, add $1 per cup
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // If the user wants chocolate, add $2 per cup
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        // Calculate the total order price by multiplying by the quantity
        return quantity * basePrice;
    }

    public void SubmitOrder(View view) {









        // Figure out if the user wants choclate topping



        EditText cusName = (EditText) findViewById(R.id.name_of_customer);
        String name = cusName.getText().toString();




        CheckBox topChocolate = (CheckBox) findViewById(R.id.topchoci);
        Boolean hasTopChocolate = topChocolate.isChecked();

        CheckBox WhippedCream = (CheckBox) findViewById(R.id.haswhippedcream);
        Boolean hasWhippedCream = WhippedCream.isChecked();

        int price = calculatePrice(hasTopChocolate,hasWhippedCream);
        String message = createOrderSummary(price,hasWhippedCream,hasTopChocolate,name);
        displayMessage(message);

        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.order_summary,name));
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }







    /**
     * Calculates the price of the order.
     * <p>
     * is the number of cups of coffee ordered
     */
//



    /**
     *
     * @param
     * @return return the message that takes order of the summary
     * @param addwhipedcream it tells that whipped cream is selected or not
     */

    private String createOrderSummary(int price,boolean addwhipedcream , boolean addchocolate,String name)
    {


        String message = "Name :"+name;
        message  += "\n Has Whipped Cream ? "+addwhipedcream;
        message  += "\n Has Chocolate Topic ?"+addchocolate;
        message  += "\n Quantity : "+ quantity;
        message  += "\n Price : "+ price;
        message  += " \n Thank You !";


        return message;

    }

}

