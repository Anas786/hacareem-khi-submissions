package com.careem.hacareem.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by anas__000 on 29-Apr-17.
 */
public class Booking {

    public static ProductList productList = new ProductList();
    public static EstimatedTime estimatedTime = new EstimatedTime();
    public static EstimatedPriceList priceList = new EstimatedPriceList();
    public static LatLng start_Point;
    public static LatLng end_Point;
    public static String Name;
    public static String Phone;

    public static String getCarType() {
        return carType;
    }

    public static void setCarType(String carType) {
        Booking.carType = carType;
    }

    public static String carType;


    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

    public static String getPhone() {
        return Phone;
    }

    public static void setPhone(String phone) {
        Phone = phone;
    }

    public static Integer getProductId() {
        return productId;
    }

    public static void setProductId(int productId) {
        Booking.productId = productId;
    }

    public static int productId;

    public static LatLng getEnd_Point() {
        return end_Point;
    }

    public static void setEnd_Point(LatLng end_Point) {
        Booking.end_Point = end_Point;
    }

    public static EstimatedPriceList getPriceList() {
        return priceList;
    }

    public static void setPriceList(EstimatedPriceList priceList) {
        Booking.priceList = priceList;
    }

    //getter
    public static LatLng getStart_Point() {
        return start_Point;
    }

    public static ProductList getProductList(){
        return productList;
    }

    public static EstimatedTime getEstimatedTime(){
        return estimatedTime;
    }

    //setter
    public static void setStart_Point(LatLng start_Point) {
        Booking.start_Point = start_Point;
    }


    public static void setProductList(ProductList productList){
        Booking.productList = productList;
    }

    public static void setEstimatedPriceList(EstimatedPriceList estimatedPriceList){
        Booking.priceList = estimatedPriceList;
    }
}
