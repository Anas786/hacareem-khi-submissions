package com.careem.hacareem.models;

/**
 * Created by anas__000 on 29-Apr-17.
 */
public class Product {
    private Integer capacity;
    private String image;
    public String display_name;
    private Boolean availibility_now;
    private Boolean availibility_later;
    private Integer minimum_time_to_book;
    private Integer maximum_time_to_cancel_now;
    private Integer maximum_time_to_cancel_later;
    private PriceDetails price_details;
    public Integer product_id;
    private Integer display_order;
}
