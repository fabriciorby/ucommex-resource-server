package br.com.ucommex.resourceserver;

import br.com.ucommex.resourceserver.helper.CustomerHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
//        CustomerHelper.getCustomerStats(CustomerHelper.getRawCustomerById("59532165720"));
    }

}