package com.ibartuszek.employeemanager.rest

import com.ibartuszek.employeemanager.exception.UserNotFoundException
import com.ibartuszek.employeemanager.model.EmployeeModel
import com.ibartuszek.employeemanager.service.EmployeeService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest

@RestController
@RequestMapping("/v1/employees")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    private val logger = KotlinLogging.logger { }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun getAll(): List<EmployeeModel> = employeeService.findAll()

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getById(@PathVariable id: Long): EmployeeModel = employeeService.findById(id!!)

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun create(@RequestBody employee: EmployeeModel) {
        employeeService.add(employee)
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun update(@PathVariable id: Long, @RequestBody employee: EmployeeModel) {
        employeeService.update(id, employee)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun delete(@PathVariable id: Long) {
        employeeService.delete(id)
    }

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun handleUserNotFoundException(exception: UserNotFoundException, request: WebRequest) {
        logger.info { "Request=$request failed" }
        logger.error(exception) { exception.message }
    }

}
