package br.com.ucommex.resourceserver.feedback.mapper;

import br.com.ucommex.resourceserver.feedback.dto.CustomerFeedbackDTO;
import br.com.ucommex.resourceserver.feedback.dto.EmployeeFeedbackDTO;
import br.com.ucommex.resourceserver.feedback.pojo.CustomerFeedback;
import br.com.ucommex.resourceserver.feedback.pojo.EmployeeFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeedbackMapper {
    FeedbackMapper MAPPER = Mappers.getMapper(FeedbackMapper.class);
    CustomerFeedbackDTO customerFeedbackToDto(CustomerFeedback customerFeedback);
    CustomerFeedback customerFeedbackDtoToCustomerFeedback(CustomerFeedbackDTO customerFeedbackDTO);
    EmployeeFeedbackDTO employeeFeedbackToDto(EmployeeFeedback employeeFeedback);
    EmployeeFeedback employeeFeedbackDtoToEmployeeFeedback(EmployeeFeedbackDTO employeeFeedbackDTO);
}