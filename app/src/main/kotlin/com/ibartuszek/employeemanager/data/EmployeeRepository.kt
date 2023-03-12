package com.ibartuszek.employeemanager.data

import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository: JpaRepository<EmployeeEntity, Long>