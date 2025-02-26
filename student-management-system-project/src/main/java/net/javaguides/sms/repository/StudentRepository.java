package net.javaguides.sms.repository;

import net.javaguides.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    // in jpa repository here student is the entity name and Long is the datatype of the id in student class.
    // now after defining this student repository will get Crude methods to perform database operations for this student jpa entity

}
