package com.transactionorder.transactionorderservice.model;

public class CombinedCustomerProfileTO {
    private CustomerProfileDTO customerProfileDTO1;
    private CustomerProfileDTO customerProfileDTO2;
    public CombinedCustomerProfileTO(CustomerProfileDTO customerProfileDTO1, CustomerProfileDTO customerProfileDTO2) {
        this.customerProfileDTO1 = customerProfileDTO1;
        this.customerProfileDTO2 = customerProfileDTO2;
    }

    public CustomerProfileDTO getCustomerProfileDTO1() { return customerProfileDTO1; }
    public CustomerProfileDTO getCustomerProfileDTO2() { return customerProfileDTO2; }
}
