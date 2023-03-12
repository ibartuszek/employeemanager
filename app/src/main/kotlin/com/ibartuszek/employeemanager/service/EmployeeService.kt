package com.ibartuszek.employeemanager.service

import com.ibartuszek.employeemanager.data.EmployeeEntity
import com.ibartuszek.employeemanager.data.EmployeeRepository
import com.ibartuszek.employeemanager.exception.UserNotFoundException
import com.ibartuszek.employeemanager.utils.EmployeePrinter
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val employeePrinter: EmployeePrinter
) {

    private val logger = KotlinLogging.logger { }

    fun add(employee: EmployeeEntity): EmployeeEntity {
        logger.info { "Saving employee=${employeePrinter.print(employee)}" }
        return employeeRepository.save(employee)
    }

    fun findById(employeeId: Long): EmployeeEntity? = employeeRepository.findById(employeeId).orElseThrow {
        UserNotFoundException(employeeId)
    }

    fun findAll(): List<EmployeeEntity> = employeeRepository.findAll()

    fun update(employee: EmployeeEntity): EmployeeEntity = add(employee)

    fun delete(employeeId: Long) {
        logger.info { "Deleting employee with id=$" }
        employeeRepository.deleteById(employeeId)
    }

}
