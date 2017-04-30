package com.careem.hacareem.utils;

import com.careem.hacareem.models.EstimatedPriceList;
import com.careem.hacareem.models.EstimatedTime;
import com.careem.hacareem.models.ProductList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anas__000 on 29-Apr-17.
 */
public interface IWebService {

    @GET("/v1/products")
    Call<ProductList> getProduct(@Query("latitude") double latitude, @Query("longitude") double longitude);

    @GET("/v1/estimates/time")
    Call<EstimatedTime> getEstimatedTime(@Query("start_latitude") float start_latitude,
                                         @Query("start_longitude") float start_longitude,
                                         @Query("product_id") Integer product_id);

    @GET("/v1/estimates/price")
    Call<EstimatedPriceList> getEstimatedPrice(@Query("start_latitude") double start_latitude,
                                               @Query("start_longitude") double start_longitude,
                                               @Query("end_latitude") double end_latitude,
                                               @Query("end_longitude") double end_longitude,
                                               @Query("booking_type") String booking_type,
                                               @Query("product_id") int product_id,
                                               @Query("pickup_time") long pickup_time);

//    @POST("api/authenticate/")
//    Call<ResponseBody> signin(@Body Map<String, String> params);
//
//    @POST("users/signup.json")
//    Call<ResponseBody> signup(@Body Map<String, String> params);
//
//    @GET("services/pharmacies.json")
//    Call<PharmaciesList> getPharmaciesList(@Query("northEast") String northEast, @Query("southWest") String southWest);

//    @GET("orders/index.json/")
//    Call<OrderList> getOrder(@Query("user_id") String userId);
//
//
//    @Multipart
//    @POST("services/orders.json")
//    Call<ResponseBody> sendOrder(
//            @Part("data") Map<String,String> orderData,
//            @Part List<MultipartBody.Part> files
//    );
//    @GET("services/order_status/{orderId}.json")
//    Call<ResponseBody> checkOrderStatus(@Path("orderId") String orderId);
//
//    @GET("services/order_details/{orderId}.json")
//    Call<ItemsList> getOrderDetails(@Path("orderId") String orderId);
//
//    @PUT("services/orders/{orderId}.json")
//    Call<ResponseBody> confirmOrder(@Path("orderId") String orderId, @Body Map<String, String> params);
//
//    @PUT("users/change_password/{userId}.json")
//    Call<ResponseBody> changePassword(@Path("userId") String orderId, @Body Map<String, String> params);
//
//    @POST("sms_verification/index.json")
//    Call<ResponseBody> sendCode(@Body Map<String, String> params);
//
//    @PUT("users/index/{userId}.json")
//    Call<ResponseBody> updatePhoneVerified(@Path("userId") String userId, @Body Map<String, String> params);

}
