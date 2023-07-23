package com.example.sneaky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SnapshotMetadata;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SneakerDetails extends AppCompatActivity implements PaymentResultListener {

    TextView tvName,tvDes,tvStyle,tvPrice;

    String des,coverimg,style,price;

    ImageView ivImg;

    Map<String, Object> booking = new HashMap<>();

    Button btnPay;

    int money;

    String sname,email,uid,tid;

    String fmoney;
    FirebaseFirestore dw,db;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sneaker_details);

        btnPay = findViewById(R.id.btnPay);

        tvName = findViewById(R.id.namelb);
        sname = getIntent().getStringExtra("NAME");
        tvName.setText(sname);

        dw = FirebaseFirestore.getInstance();

        ivImg = findViewById(R.id.ivImg);
        tvDes = findViewById(R.id.des);
        tvStyle = findViewById(R.id.style);
        tvPrice = findViewById(R.id.price);



        dw.collection("Sneakers")
                .document(sname)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if(documentSnapshot!=null && documentSnapshot.exists()){
                                des = documentSnapshot.getString("des");
                                tvDes.setText(des);
                                style = documentSnapshot.getString("style");
                                tvStyle.setText(style);
                                price = documentSnapshot.getString("price");
                                tvPrice.setText(price);
                                coverimg = documentSnapshot.getString("coverimg");
                                Picasso.get().load(coverimg).into(ivImg);

                                price = price.replaceAll("\\s", "");

                                money = Integer.parseInt(price);
                                money = money * 100;
                                fmoney = String.valueOf(money);

                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SneakerDetails.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay();
            }
        });


    }

    private void pay() {
        Checkout co = new Checkout();

        JSONObject object = new JSONObject();

        try {
            object.put("name","Fiesta");
            object.put("description","The Event Management App");
            object.put("image","https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhVztmYgh4yFOkJeWu-VVJvJ6nlAoPGrp_yHtMR3HofAm1tggX7pdNz3IdtvBu8g2lIeKs1YzyFxciMVWhPlkz63h-4T6np_eUhthmvv5rer4IvUqSuwqNP939HSG8e3Kw70-rXnhHqXf6q38PLoiile3dHH_nYAs6FRIag9xp1lypPp9An3Jq8d9tS7g/s320/F%20(4).png");
            object.put("currency","INR");
            object.put("amount",fmoney);

            JSONObject prefill = new JSONObject();
            prefill.put("contact","9082286717");
            prefill.put("email","nileshparab5623@gmail.com");
            object.put("prefill",prefill);

            co.open(SneakerDetails.this,object);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        email=mAuth.getCurrentUser().getEmail();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        db = FirebaseFirestore.getInstance();
        uid=mAuth.getCurrentUser().getUid();
        tid = uid.substring(0,5)+currentDateandTime;

        Toast.makeText(this, "Payment Successful !", Toast.LENGTH_SHORT).show();
        booking.put("email", email);
        booking.put("img", coverimg);
        booking.put("date", "22/07/2023");
        booking.put("price", price);
        booking.put("sname", sname);
        booking.put("style", style);

        dw.collection("Bookings").document(tid)
                .set(booking)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SneakerDetails.this, "Data added succesfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SneakerDetails.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
    }
}