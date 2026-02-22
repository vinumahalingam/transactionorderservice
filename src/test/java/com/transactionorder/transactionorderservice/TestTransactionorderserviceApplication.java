package com.transactionorder.transactionorderservice;

import org.springframework.boot.SpringApplication;

public class TestTransactionorderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(TransactionorderserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
