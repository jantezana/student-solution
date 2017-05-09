package com.truextend.studentsolution.services;

import com.truextend.studentsolution.services.stub.StudentServiceStub;

/**
 * Factory to create Services.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public class ServiceFactory {

    /**
     * Creates an student service.
     *
     * @return the student service
     */
    public static StudentService createStudentService() {
        return new StudentServiceStub();
    }
}