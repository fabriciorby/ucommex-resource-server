package br.com.ucommex.resourceserver.feedback.mapper;

import br.com.ucommex.resourceserver.feedback.dto.EmployeeFeedbackDTO;
import br.com.ucommex.resourceserver.feedback.pojo.EmployeeFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeFeedbackMapper {
    EmployeeFeedbackMapper EMPLOYEE_FEEDBACK_MAPPER = Mappers.getMapper(EmployeeFeedbackMapper.class);
    EmployeeFeedbackDTO toDto(EmployeeFeedback employeeFeedback);
    EmployeeFeedback toPojo(EmployeeFeedbackDTO employeeFeedbackDTO);
}
