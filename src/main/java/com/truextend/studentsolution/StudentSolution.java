package com.truextend.studentsolution;

import com.truextend.studentsolution.model.EducationalStage;
import com.truextend.studentsolution.model.Gender;
import com.truextend.studentsolution.model.Student;
import com.truextend.studentsolution.services.ServiceFacade;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * StudentSolution class.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public class StudentSolution {
    private final static Logger LOGGER = Logger.getLogger(StudentSolution.class);

    /**
     * Main method.
     *
     * @param args the arguments
     */
    public static void main(String args[]) {
        try {
            ServiceFacade.INSTANCE.getStudentService().addStudent(new Student("Juan", Gender.MALE, EducationalStage.UNIVERSITY, 2017012456456L));
            Collection<Student> students = ServiceFacade.INSTANCE.getStudentService().getStudents();
            for (Student student : students) {
                LOGGER.info(student);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
        }
    }
}
