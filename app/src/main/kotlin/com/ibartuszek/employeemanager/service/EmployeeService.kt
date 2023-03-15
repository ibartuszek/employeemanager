package com.ibartuszek.employeemanager.service

import com.ibartuszek.employeemanager.data.EmployeeEntity
import com.ibartuszek.employeemanager.data.EmployeeRepository
import com.ibartuszek.employeemanager.exception.UserNotFoundException
import com.ibartuszek.employeemanager.model.EmployeeModel
import com.ibartuszek.employeemanager.utils.EmployeePrinter
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val employeeMapper: EmployeeMapper,
    private val employeePrinter: EmployeePrinter
) {

    private val logger = KotlinLogging.logger { }

    fun add(employee: EmployeeModel): EmployeeEntity {
        logger.info { "Saving employee:${employeePrinter.print(employee)}" }
        return employeeRepository.save(employeeMapper.map(employee))
    }

    fun findById(employeeId: Long): EmployeeModel = findEntityById(employeeId).let { employeeMapper.map(it) }

    private fun findEntityById(employeeId: Long): EmployeeEntity =
        employeeRepository.findById(employeeId).orElseThrow { UserNotFoundException(employeeId) }

    fun findAll(): List<EmployeeModel> = employeeRepository.findAll().map { employeeMapper.map(it) }

    fun update(id: Long, employee: EmployeeModel): EmployeeModel {
        logger.info { "Updating employee:${employeePrinter.print(employee)}" }
        val oldEntity = findEntityById(id)
        return employeeRepository.save(employeeMapper.map(employee, id, oldEntity.employeeContact.id))
            .let { employeeMapper.map(it) }
    }


    fun delete(employeeId: Long) {
        logger.info { "Deleting employee with id=$" }
        employeeRepository.deleteById(employeeId)
    }

}
