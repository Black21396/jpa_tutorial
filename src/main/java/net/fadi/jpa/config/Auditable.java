package net.fadi.jpa.config;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

// to tell Hibernate, this class we dont want it as Table
@MappedSuperclass
// this annotation to put the date automatically to the variables in this class
@EntityListeners(AuditingEntityListener.class)
// add set an get to variables (using Lombok library)
@Getter
@Setter
@NoArgsConstructor
// U: every table can specific what the type of user I want to add it (normally we put user name as String)
public abstract class Auditable<U> {

    // put the user who created new row in DB
    @CreatedBy
    protected U createdBy;
    // put the Time when the row is created
    @CreatedDate
    protected LocalDate createdDate;
    // if user modified this value, then put who is the user
    @LastModifiedBy
    protected U lastModifiedBy;
    // put the Time when the row is modified
    @LastModifiedDate
    protected LocalDate lastModifiedDate;
}
