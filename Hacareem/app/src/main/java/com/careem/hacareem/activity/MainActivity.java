package com.careem.hacareem.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.careem.hacareem.R;
import com.careem.hacareem.fragments.MapFragment;
import com.careem.hacareem.fragments.StartFragment;
import com.careem.hacareem.models.Amount;
import com.careem.hacareem.models.EstimatedPriceList;
import com.careem.hacareem.models.EstimatedTime;
import com.careem.hacareem.models.ProductList;
import com.careem.hacareem.utils.IWebService;
import com.careem.hacareem.utils.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    double latitude;
//    double longitude;
    Button btnRideNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm;
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        StartFragment myFragment = new StartFragment();
        ft.replace(R.id.mainLayout, myFragment);
        ft.commit();
//        latitude = 24.162926;
//        longitude = 61.266565;

//          getProduct(latitude,longitude);
//          getEstimatedTime(24.56f,64.21f,121);
//          getEstimatedPrice(24.56556,64.2165652,28.66984,68.65629,"NOW",12,14893654);
    }

    public void getProduct(double latitude, double longitude){
        ServiceGenerator sg = new ServiceGenerator();
        IWebService service = sg.createService(IWebService.class,getString(R.string.Token));
        final Call<ProductList> postingService = service.getProduct(latitude,longitude);
        postingService.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                if (response.isSuccessful()){
                    ProductList productList = response.body();
                } else {
                    Toast.makeText(getApplicationContext(),"No internet connection Found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No internet connection Found",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
