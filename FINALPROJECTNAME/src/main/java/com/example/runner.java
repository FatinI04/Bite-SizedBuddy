package com.example;

import com.example.API.API;

public class runner {

    public static void main(String[] args) {
        API ap1 = new API();
        ap1.setAPIData("beef");
        System.out.println(ap1.getInfo());
        
    }

}
