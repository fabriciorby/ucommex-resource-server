package br.com.ucommex.resourceserver.feedback.controller;

import br.com.ucommex.resourceserver.feedback.dto.CustomerFeedbackDTO;
import br.com.ucommex.resourceserver.feedback.dto.EmployeeFeedbackDTO;
import br.com.ucommex.resourceserver.feedback.helper.FeedbackDbHelper;
import br.com.ucommex.resourceserver.feedback.mapper.FeedbackMapper;
import br.com.ucommex.resourceserver.feedback.pojo.CustomerFeedback;
import br.com.ucommex.resourceserver.feedback.pojo.EmployeeFeedback;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private FeedbackMapper mapper = FeedbackMapper.MAPPER;

    @CrossOrigin
    @PostMapping("/customer")
    public CustomerFeedback postCustomerFeedback(@RequestBody CustomerFeedbackDTO customerFeedbackDTO) {
        CustomerFeedback customerFeedback = mapper.customerFeedbackDtoToCustomerFeedback(customerFeedbackDTO);
        FeedbackDbHelper.insert(customerFeedback);
        return customerFeedback;
    }

    @CrossOrigin
    @PostMapping("/employee")
    public EmployeeFeedback postEmployeeFeedback(@RequestBody EmployeeFeedbackDTO employeeFeedbackDTO) {
        EmployeeFeedback employeeFeedback = mapper.employeeFeedbackDtoToEmployeeFeedback(employeeFeedbackDTO);
        FeedbackDbHelper.insert(employeeFeedback);
        return employeeFeedback;
    }

    @CrossOrigin
    @GetMapping("/customer")
    public Set<CustomerFeedback> getCustomerFeedbacks() {
        return FeedbackDbHelper.getCustomerFeedbacks();
    }

    @CrossOrigin
    @GetMapping("/employee")
    public Set<EmployeeFeedback> getEmployeeFeedbacks() {
        return FeedbackDbHelper.getEmployeeFeedbacks();
    }
}
