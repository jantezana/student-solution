package com.truextend.studentsolution.services;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.truextend.studentsolution.model.Student;

import java.util.Collection;

/**
 * Student Service interface.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public interface StudentService {

    /**
     * Gets students.
     *
     * @return the students
     * @throws ServiceException the exception
     */
    Collection<Student> getStudents()
    throws ServiceException;

    /**
     * Gets a student by name.
     *
     * @param name the name
     * @return the student
     * @throws ServiceException the exception
     */
    Optional<Student> getStudent(final String name)
    throws ServiceException;

    /**
     * Gets a list of students by predicate
     *
     * @param predicate the predicate
     * @return the list of students
     * @throws ServiceException the exception
     */
    Collection<Student> getStudents(final Predicate<Student> predicate)
    throws ServiceException;

    /**
     * Adds a student.
     *
     * @throws ServiceException the exception
     */
    void addStudent(Student student)
    throws ServiceException;

    /**
     * Adds a list of students.
     *
     * @param students the list of students
     * @throws ServiceException the exception
     */
    void addStudents(Collection<Student> students,
                     Predicate<Student> predicate)
    throws ServiceException;

    /**
     * Remove a student
     *
     * @param student the student to delete
     * @throws ServiceException the exception
     */
    void removeStudent(Student student)
    throws ServiceException;

    /**
     * Remove students by predicate
     *
     * @param predicate the predicate
     * @throws ServiceException the exception
     */
    void removeStudents(Predicate<Student> predicate)
    throws ServiceException;
}
