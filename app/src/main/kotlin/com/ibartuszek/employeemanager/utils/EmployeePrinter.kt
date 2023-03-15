package com.ibartuszek.employeemanager.utils

import com.ibartuszek.employeemanager.data.EmployeeContactEntity
import com.ibartuszek.employeemanager.data.EmployeeEntity
import com.ibartuszek.employeemanager.model.EmployeeModel
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.security.MessageDigest

@Component
class EmployeePrinter {

    fun print(employee: EmployeeModel): String =
        "id=${employee.id}, employeeCode=${employee.employeeCode}, status=${employee.status}, " +
                "started=${employee.started}, name=${md5(employee.name)}, email=${md5(employee.email)} " +
                "jobTitle=${employee.jobTitle}, phoneNumber=${md5(employee.phoneNumber)}, " +
                "imageUrl=${employee.imageUrl}"

    fun print(employee: EmployeeEntity): String =
        "id=${employee.id}, employeeCode=${employee.employeeCode}, status=${employee.status}, " +
                "started=${employee.started} employeeContact={${convert(employee.employeeContact)}}"

    private fun convert(employeeContact: EmployeeContactEntity): String =
        "id=${employeeContact.id}, name=${md5(employeeContact.name)}, email=${md5(employeeContact.email)} " +
                "jobTitle=${employeeContact.jobTitle}, phoneNumber=${md5(employeeContact.phoneNumber)}, " +
                "imageUrl=${employeeContact.imageUrl}"

    private fun md5(input: String): String = BigInteger(1, md.digest(input.toByteArray()))
        .toString(16)
        .padStart(32, '0')

    companion object {
        private val md = MessageDigest.getInstance("MD5")
    }

}
