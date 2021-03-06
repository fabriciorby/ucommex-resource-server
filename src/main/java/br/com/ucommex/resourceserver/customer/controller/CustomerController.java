package br.com.ucommex.resourceserver.customer.controller;

import br.com.ucommex.resourceserver.customer.dto.CustomerStatsDTO;
import br.com.ucommex.resourceserver.customer.helper.CustomerDbHelper;
import br.com.ucommex.resourceserver.customer.helper.CustomerStatsHelper;
import br.com.ucommex.resourceserver.customer.pojo.ClienteDB;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @CrossOrigin
    @GetMapping("/raw")
    public ClienteDB getRawCustomer(@RequestParam(value = "cpf") String idCliente) {
        return CustomerDbHelper.getRawCustomerById(idCliente);
    }

    @CrossOrigin
    @GetMapping("/stats")
    public CustomerStatsDTO getCustomerStats(@RequestParam(value = "cpf") String idCliente) {
        return CustomerStatsHelper.getCustomerStatsById(idCliente);
    }

}