package com.careem.hacareem.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.careem.hacareem.R;
import com.careem.hacareem.activity.MainActivity;
import com.careem.hacareem.models.Booking;
import com.careem.hacareem.models.EstimatedPriceList;
import com.careem.hacareem.models.EstimatedTime;
import com.careem.hacareem.utils.Helper;
import com.careem.hacareem.utils.IWebService;
import com.careem.hacareem.utils.ServiceGenerator;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anas__000 on 30-Apr-17.
 */
public class CaptainDetailFragment extends Fragment{

    TextView tvCaptainName;
    TextView tvETA;
    TextView tvDriverRegistrationNo;
    Button btnExit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_captain_detail, container, false);
        Helper.showSpinner("Getting Captain Details....",getContext());
        tvCaptainName = (TextView) rootView.findViewById(R.id.tv_CaptainName);
        btnExit = (Button) rootView.findViewById(R.id.btn_exit);
        tvETA = (TextView) rootView.findViewById(R.id.tv_ETA);
        tvDriverRegistrationNo = (TextView) rootView.findViewById(R.id.tv_BookingNo);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        getEstimatedTime(Double.valueOf(Booking.getStart_Point().latitude).floatValue(),
                Double.valueOf(Booking.getStart_Point().latitude).floatValue(),
                Booking.getProductId());
        return rootView;
    }

    public void getEstimatedTime(float start_latitude, float start_longitude, Integer product_id){
        ServiceGenerator sg = new ServiceGenerator();
        IWebService service = sg.createService(IWebService.class,getString(R.string.Token));
        final Call<EstimatedTime> postingService = service.getEstimatedTime(start_latitude,start_longitude,product_id);
        postingService.enqueue(new Callback<EstimatedTime>() {
            @Override
            public void onResponse(Call<EstimatedTime> call, Response<EstimatedTime> response) {
                if (response.isSuccessful()){
                    EstimatedTime estimatedTime = response.body();
                    tvCaptainName.setText("Car Type : "+Booking.getCarType().toString());
                    tvETA.setText("Estimated Arrival Time"
                            +String.valueOf(estimatedTime.times.get(0).eta)+" seconds");
                    tvDriverRegistrationNo.setText("Reg NO: "+estimatedTime.times.get(0).product_id.toString());
                } else {
                    Toast.makeText(getActivity(),"No internet connection Found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EstimatedTime> call, Throwable t) {
                Toast.makeText(getActivity(),"No internet connection Found",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getEstimatedPrice(double start_latitude, double start_longitude,
                                  double end_latitude, double end_longitude,
                                  String booking_type, Integer product_id,
                                  long pickup_time){
        ServiceGenerator sg = new ServiceGenerator();
        IWebService service = sg.createService(IWebService.class,getString(R.string.Token));
        final Call<EstimatedPriceList> postingService = service.getEstimatedPrice(start_latitude,
                start_longitude,end_latitude,end_longitude,booking_type,product_id,pickup_time);
        postingService.enqueue(new Callback<EstimatedPriceList>() {
            @Override
            public void onResponse(Call<EstimatedPriceList> call, Response<EstimatedPriceList> response) {
                if (response.isSuccessful()){
                    EstimatedPriceList estimatedPriceList = response.body();

                } else {
                    Toast.makeText(getActivity(),"No internet connection Found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EstimatedPriceList> call, Throwable t) {
                Toast.makeText(getActivity(),"No internet connection Found",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
