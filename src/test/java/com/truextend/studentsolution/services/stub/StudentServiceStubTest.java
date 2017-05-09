package com.truextend.studentsolution.services.stub;

import com.google.common.base.Predicate;
import com.truextend.studentsolution.model.EducationalStage;
import com.truextend.studentsolution.model.Gender;
import com.truextend.studentsolution.model.Student;
import com.truextend.studentsolution.services.ServiceFacade;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * StudentServiceStubTest class.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public class StudentServiceStubTest {
    @Test
    public void getStudents()
    throws Exception {
        Collection<Student> students = ServiceFacade.INSTANCE.getStudentService().getStudents();
        assertNotNull(students);
        assertTrue(students.size() > 0);
    }

    @Test
    public void getStudent()
    throws Exception {
        Student student = ServiceFacade.INSTANCE.getStudentService().getStudent("Luke").get();
        assertNotNull(student);
        assertEquals(Gender.MALE, student.getGender());
        assertEquals(EducationalStage.HIGH, student.getStage());
    }

    @Test
    public void getStudentsPredicate()
    throws Exception {
        Predicate<Student> males = student -> student.getGender().equals(Gender.MALE);

        Collection<Student> maleStudents = ServiceFacade.INSTANCE.getStudentService().getStudents(males);
        assertNotNull(maleStudents);
        assertTrue(maleStudents.size() > 0);
    }

    @Test
    public void addStudent()
    throws Exception {
        Student student = new Student("Juan", Gender.MALE, EducationalStage.UNIVERSITY);
        ServiceFacade.INSTANCE.getStudentService().addStudent(student);

        Student expected = ServiceFacade.INSTANCE.getStudentService().getStudent("Juan").get();
        assertNotNull(expected);
        assertEquals(Gender.MALE, expected.getGender());
        assertEquals(EducationalStage.UNIVERSITY, expected.getStage());
        assertNotNull(expected.getTimestamp());
    }

    @Test
    public void removeStudents()
    throws Exception {
        Predicate<Student> kinder = student -> student.getStage().equals(EducationalStage.UNIVERSITY);
        ServiceFacade.INSTANCE.getStudentService().removeStudents(kinder);

        Collection<Student> students = ServiceFacade.INSTANCE.getStudentService().getStudents(kinder);
        assertTrue(students.isEmpty());
    }

}