package com.ibartuszek.employeemanager.exception

class UserNotFoundException(private val employeeId: Long): RuntimeException("User with id=$employeeId not found!")
