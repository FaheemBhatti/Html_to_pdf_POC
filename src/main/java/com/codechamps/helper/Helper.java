package com.codechamps.helper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.codechamps.data.*;

@Component
public class Helper {

    public static Order getOrder() {
        Order order = new Order();
        order.setOrderId(1234);
        order.setDate("2077-09-05");
        Address address = new Address();
        address.setCity("New York");
        address.setStreet("2897  Geneva Street");
        address.setZipCode("10016");
        address.setState("New York");
        Account account = new Account();
        account.setPhoneNumber("917-483-5146");
        account.setEmail("628jh4h624y@temporary-mail.net");
        account.setName("Mr. Eugene A King");
        account.setAddress(address);
        order.setAccount(account);

        List<Item> items = new ArrayList<>();
        order.setItems(items);
        Item item1 = new Item();
        item1.setName("Blue T-Shirt");
        item1.setPrice(BigDecimal.valueOf(9.99));
        item1.setQuantity(2);
        item1.setSku("100034");
        items.add(item1);
        Item item2 = new Item();
        item2.setName("Green SweatShirt");
        item2.setPrice(BigDecimal.valueOf(12.49));
        item2.setSku("100075");
        item2.setQuantity(3);

        items.add(item2);
        Item item3 = new Item();
        item3.setName("Grey Sports Shoes");
        item3.setPrice(BigDecimal.valueOf(14.49));
        item3.setSku("100022");
        item3.setQuantity(1);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        items.add(item3);
        
        

        Payment payment = new Payment();
        BigDecimal totalPrice = items.stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        payment.setAmount(totalPrice);
        payment.setCardNumber("4111111111111111");
        payment.setCvv("123");
        payment.setMonth("04");
        payment.setYear("2030");
        order.setPayment(payment);
        return order;
    }
    
    public static Billing getBill() {
    	Address address = new Address();
    	address.setCity("Hamburg");
    	address.setStreet("Streetee");
    	address.setZipCode("11223344");
    	address.setCareOf("Faheem");
    	Billing bill = new Billing();
    	bill.setEndCustomerFirstName("Faheem");
    	bill.setEndCustomerLastName("Bhatti ");
    	bill.setEndCustomerAddress(address);
    	bill.setBillingNumber("902-0030-754");
    	bill.setBillGenerationDate(LocalDateTime.now());
    	bill.setEndDate(LocalDateTime.now());
    	bill.setStartDate(LocalDateTime.of(12, 02, 14, 0, 0));
    	bill.setTotalTaxedCost(7570.5d);
    	bill.setNetFixCost(75d);
    	bill.setTaxFixCost(42d);
    	bill.setTotalEnergyLoaded(555f);
    	bill.setNetVarCost(524d);
    	bill.setTaxVarCost(36d);
    	return bill;
    }
    
}
