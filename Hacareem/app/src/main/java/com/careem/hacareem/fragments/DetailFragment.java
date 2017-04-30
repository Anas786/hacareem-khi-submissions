package com.careem.hacareem.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.careem.hacareem.R;
import com.careem.hacareem.models.Booking;
import com.careem.hacareem.models.ProductList;
import com.careem.hacareem.utils.Helper;
import com.careem.hacareem.utils.IWebService;
import com.careem.hacareem.utils.ServiceGenerator;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anas__000 on 30-Apr-17.
 */

public class DetailFragment extends Fragment{


    Button btnDropOf;
    EditText etName,etPhone;
    Spinner spCarType;
    final LatLng CurrentPosition=new LatLng(24.8668,67.0255);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        getProduct(CurrentPosition.latitude,CurrentPosition.longitude);
        btnDropOf = (Button) rootView.findViewById(R.id.btn_drop);
        etName = (EditText) rootView.findViewById(R.id.et_name);
        etPhone = (EditText) rootView.findViewById(R.id.et_Phone);
        spCarType = (Spinner) rootView.findViewById(R.id.sp_car);
        btnDropOf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Booking.setName(etName.getText().toString());
                Booking.setPhone(etPhone.getText().toString());
                Booking.setCarType("Max");
                if(etName.getText()!=null && etPhone.getText()!=null && Booking.getCarType()!=null) {
                    FragmentManager fm;
                    fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    MapFragment myFragment = new MapFragment();
                    ft.replace(R.id.mainLayout, myFragment);
                    ft.commit();
                }else {
                    Toast.makeText(getActivity(),"Fill the required Fields to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
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
                    List<String> spinnerArray =  new ArrayList<String>();
                    for(int index=0; index<productList.products.size(); index++){
                        spinnerArray.add(productList.products.get(index).display_name.toString());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, spinnerArray);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spCarType.setAdapter(adapter);
                    Helper.dialog.dismiss();
                    String selected = spCarType.getSelectedItem().toString();
                    if (selected.equals(productList.products.get(0).display_name.toString())) {
                        Booking.setProductId(productList.products.get(0).product_id);
                    }else if (selected.equals(productList.products.get(1).display_name.toString())) {
                        Booking.setProductId(productList.products.get(1).product_id);
                    }else if (selected.equals(productList.products.get(2).display_name.toString())) {
                        Booking.setProductId(productList.products.get(2).product_id);
                    }
                } else {
                    Toast.makeText(getActivity(),"No internet connection Found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                Toast.makeText(getActivity(),"No internet connection Found",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
