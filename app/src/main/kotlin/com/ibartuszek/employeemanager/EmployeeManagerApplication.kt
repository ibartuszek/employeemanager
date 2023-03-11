package com.ibartuszek.employeemanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmployeeManagerApplication

fun main(args: Array<String>) {
	runApplication<EmployeeManagerApplication>(*args)
}
