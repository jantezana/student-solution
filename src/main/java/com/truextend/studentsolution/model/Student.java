package com.truextend.studentsolution.model;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Student class.
 *
 * @author jantezana
 * @version 2017-05-08
 */
@Data
@AllArgsConstructor
public class Student implements Comparable<Student> {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private String name;
    private Gender gender;
    private EducationalStage stage;
    private long timestamp;

    /**
     * Creates an instance of {@link com.truextend.studentsolution.model.Student}
     *
     * @param name   the name
     * @param gender the gender
     * @param stage  the stage
     */
    public Student(final String name,
                   final Gender gender,
                   final EducationalStage stage) {
        Preconditions.checkNotNull(name, "The name is null");
        Preconditions.checkNotNull(gender, "The gender is null");
        Preconditions.checkNotNull(stage, "The stage is null");
        this.name = name;
        this.gender = gender;
        this.stage = stage;
        this.timestamp = Long.parseLong(formatter.format(LocalDateTime.now()));
    }

    @Override
    public int compareTo(Student student) {
        Preconditions.checkNotNull(student, "The student is null");
        return this.getName().compareTo(student.getName());
    }
}
