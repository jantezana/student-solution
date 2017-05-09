package com.truextend.studentsolution.services;

/**
 * Singleton to access underlying services (MULTITON of services)
 *
 * @author jantezana
 * @version 2017-05-08
 */
public enum ServiceFacade {
    /**
     * Instance.
     */
    INSTANCE;

    /**
     * Student services
     */
    StudentService studentService;

    /**
     * Default constructor of ServiceFacade.
     */
    ServiceFacade() {
    }

    /**
     * Get jobs service.
     *
     * @return the jobs service
     */
    public synchronized StudentService getStudentService() {
        if (studentService == null) {
            studentService = ServiceFactory.createStudentService();
        }
        return studentService;
    }

    /**
     * Reset.
     */
    public synchronized void reset() {
        studentService = null;
    }
}
