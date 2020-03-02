package br.com.ucommex.resourceserver.feedback.mapper;

import br.com.ucommex.resourceserver.feedback.dto.CustomerFeedbackDTO;
import br.com.ucommex.resourceserver.feedback.dto.EmployeeFeedbackDTO;
import br.com.ucommex.resourceserver.feedback.pojo.CustomerFeedback;
import br.com.ucommex.resourceserver.feedback.pojo.EmployeeFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerFeedbackMapper {
    CustomerFeedbackMapper CUSTOMER_FEEDBACK_MAPPER = Mappers.getMapper(CustomerFeedbackMapper.class);
    CustomerFeedbackDTO toDto(CustomerFeedback customerFeedback);
    CustomerFeedback toPojo(CustomerFeedbackDTO customerFeedbackDTO);
}