package com.codechamps.data;

import lombok.Data;

@Data
public class Address {
	
    private String street;
    private String city;
    private String state;
    private String careOf;
    private String zipCode;
}