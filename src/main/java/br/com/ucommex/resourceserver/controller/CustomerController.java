package br.com.ucommex.resourceserver.controller;

import br.com.ucommex.resourceserver.dto.CustomerStatsDTO;
import br.com.ucommex.resourceserver.helper.CustomerHelper;
import br.com.ucommex.resourceserver.pojo.ClienteDB;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/sync")
    public String sync() {
        return "sync";
    }

    @CrossOrigin
    @GetMapping("/customer")
    public ClienteDB getRawCustomer(@RequestParam(value = "cpf") String idCliente) {
        return CustomerHelper.getRawCustomerById(idCliente);
    }

    @CrossOrigin
    @GetMapping("/customer/stats")
    public CustomerStatsDTO getCustomerStats(@RequestParam(value = "cpf") String idCliente) {
        return CustomerHelper.getCustomerStats(CustomerHelper.getRawCustomerById(idCliente));
    }

}