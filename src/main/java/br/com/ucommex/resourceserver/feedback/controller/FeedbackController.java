package br.com.ucommex.resourceserver.feedback.controller;

import br.com.ucommex.resourceserver.feedback.dto.CustomerFeedbackDTO;
import br.com.ucommex.resourceserver.feedback.dto.EmployeeFeedbackDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @CrossOrigin
    @PostMapping("/customer")
    public CustomerFeedbackDTO postCustomerFeedback(@RequestBody CustomerFeedbackDTO customerFeedback) {
        System.out.println(customerFeedback);
        return customerFeedback;
    }

    @CrossOrigin
    @PostMapping("/employee")
    public EmployeeFeedbackDTO postEmployeeFeedback(@RequestBody EmployeeFeedbackDTO employeeFeedback) {
        System.out.println(employeeFeedback);
        return employeeFeedback;
    }
}
