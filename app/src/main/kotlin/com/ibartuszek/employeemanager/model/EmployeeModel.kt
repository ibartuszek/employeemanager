package com.ibartuszek.employeemanager.model

import com.ibartuszek.employeemanager.data.EmployeeStatus
import java.time.Instant

data class EmployeeModel(
    val id: Long?,
    val employeeCode: String,
    val status: EmployeeStatus,
    val started: Instant,
    val name: String,
    val email: String,
    val jobTitle: String,
    val phoneNumber: String,
    val imageUrl: String
)
