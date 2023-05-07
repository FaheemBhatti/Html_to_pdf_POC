package com.codechamps.data;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billing {

	private String id;
	private Double taxedVarCost;
	private Double netVarCost;
	private Double taxVarCost;
	private Double taxPercentVar;
	private Double taxedFixCost;
	private Double netFixCost;
	private Double taxFixCost;
	private Double taxPercentFix;
	private Double totalNetCost;
	private Double totalTaxCost;
	private Double totalTaxedCost;
	private String billingNumber;
	private String billingPdfName;
	private Float totalEnergyLoaded;
	private String cpoId;
	private LocalDateTime billGenerationDate;
	private String endCustomerId;
	private String contractKey;
	private String endCustomerFirstName;
	private String endCustomerLastName;
	private Address endCustomerAddress;
	private Address locationAddress;
	private String useQuantity;
    private LocalDateTime creationTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BillingStatus billingStatus;
    private Double totalChargingTime;
}