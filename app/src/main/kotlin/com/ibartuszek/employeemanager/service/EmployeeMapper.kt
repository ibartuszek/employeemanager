package com.ibartuszek.employeemanager.service

import com.ibartuszek.employeemanager.data.EmployeeContactEntity
import com.ibartuszek.employeemanager.data.EmployeeEntity
import com.ibartuszek.employeemanager.model.EmployeeModel
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class EmployeeMapper {

    fun map(entity: EmployeeEntity): EmployeeModel =
        EmployeeModel(
            id = entity.id,
            employeeCode = entity.employeeCode,
            status = entity.status,
            started = entity.started,
            name = entity.employeeContact.name,
            email = entity.employeeContact.email,
            jobTitle = entity.employeeContact.jobTitle,
            phoneNumber = entity.employeeContact.phoneNumber,
            imageUrl = entity.employeeContact.imageUrl
        )

    fun map(model: EmployeeModel, id: Long = 0, contactId: UUID? = null): EmployeeEntity =
        EmployeeEntity(
            id = id,
            employeeCode = model.employeeCode,
            status = model.status,
            started = model.started,
            employeeContact = EmployeeContactEntity(
                id = contactId,
                name = model.name,
                email = model.email,
                jobTitle = model.jobTitle,
                phoneNumber = model.phoneNumber,
                imageUrl = model.imageUrl
            )
        )

}
