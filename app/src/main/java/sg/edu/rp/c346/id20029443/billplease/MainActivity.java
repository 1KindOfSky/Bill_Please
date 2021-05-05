package sg.edu.rp.c346.id20029443.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

   EditText amount;
   EditText pax;
   EditText discount;
   ToggleButton svs;
   ToggleButton gst;
   TextView total;
   TextView eachpay;
   Button split;
   Button reset;
   RadioGroup payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editInputAmount);
        pax =findViewById(R.id.editInputPax);
        discount = findViewById(R.id.editInputDiscount);
        svs = findViewById(R.id.tgSVS);
        gst = findViewById(R.id.tgGST);
        total = findViewById(R.id.textBill);
        split = findViewById(R.id.tgSplit);
        reset = findViewById(R.id.tgReset);
        eachpay = findViewById(R.id.textEach);
        payment = findViewById(R.id.payment);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }

        });

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double FinalAmount = 0.0;
                int checkedRadioId = payment.getCheckedRadioButtonId();
                if (svs.isChecked() == false && gst.isChecked() == false) {
                    FinalAmount = Double.parseDouble(amount.getText().toString());
                } else if(svs.isChecked() == false && gst.isChecked() == true) {
                FinalAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                } else if(svs.isChecked() == true && gst.isChecked() == false) {
                    FinalAmount = Double.parseDouble(amount.getText().toString()) * 1.1;
                } else if(svs.isChecked() == true && gst.isChecked() == true)
                {
                    FinalAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                }

                if (discount.getText().toString().trim().length() != 0) {
                    FinalAmount = FinalAmount * (1 - Double.parseDouble(discount.getText().toString()) / 100);
                }

                total.setText("Total Bill: $" + String.format("%.2f", FinalAmount));
                int numpax = Integer.parseInt(pax.getText().toString());

                if (numpax != 1) {
                    if (checkedRadioId == R.id.cash) {

                        eachpay.setText("Each Pays: $" + String.format("%.2f", FinalAmount / numpax + " in cash"));
                    }
                    else {
                        eachpay.setText("Each Pays: $" + String.format("%.2f", FinalAmount / numpax + " via PayNow to 91234567"));
                    }

                } else
                    if (checkedRadioId == R.id.cash) {
                        eachpay.setText("Each Pay: $" + FinalAmount + " in cash");
                    }
                    else {
                        eachpay.setText("Each Pay: $" + FinalAmount + " via PayNow to 912345678");
                    }
            }




            });




        }



    


}