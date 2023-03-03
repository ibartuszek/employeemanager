package com.ibartuszek.employeemanager.data

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.hibernate.annotations.Type
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "employee")
data class EmployeeEntity(
    @Id
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @Column(nullable = false, updatable = false)
    val id: Long = 0,
    @Column(nullable = false, updatable = false)
    val employeeCode: String,
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType::class)
    val status: EmployeeStatus,
    val started: Instant,
    @OneToOne(
        targetEntity = EmployeeContactEntity::class,
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    val employeeContact: EmployeeContactEntity
)

@Entity
@Table(name = "employee_contact")
data class EmployeeContactEntity(
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(nullable = false, updatable = false)
    var id: UUID? = null,
    val name: String,
    val email: String,
    val jobTitle: String,
    val phoneNumber: String,
    val imageUrl: String,
)

enum class EmployeeStatus {
    ACTIVE, INACTIVE
}
