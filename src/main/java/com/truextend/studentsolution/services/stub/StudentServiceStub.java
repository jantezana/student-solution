package com.truextend.studentsolution.services.stub;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.truextend.studentsolution.model.EducationalStage;
import com.truextend.studentsolution.model.Gender;
import com.truextend.studentsolution.model.Student;
import com.truextend.studentsolution.services.ServiceException;
import com.truextend.studentsolution.services.StudentService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

import static com.truextend.studentsolution.utils.Constants.DELIMITER;
import static com.truextend.studentsolution.utils.Constants.HEADERS;
import static com.truextend.studentsolution.utils.Constants.RECORD_SEPARATOR;

/**
 * StudentServiceStub class, this class saves the data in a csv file.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public class StudentServiceStub implements StudentService {
    private final static Logger LOGGER = Logger.getLogger(StudentServiceStub.class);
    private Map<String, Student> students;

    /**
     * Creates an instance of {@link com.truextend.studentsolution.services.stub.StudentServiceStub}
     */
    public StudentServiceStub() {
        this.students = new LinkedHashMap<>();
        loadingStudents();
    }

    /**
     * Initialize list of student.
     */
    private void loadingStudents() {
        LOGGER.info("Initialize the list of students");
        URL url = this.getClass().getResource("/students.csv");

        try {
            final Reader reader = new FileReader(url.getPath());
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                EducationalStage stage = EducationalStage.fromString(record.get("Student Type")).get();
                String name = record.get("Student Name");
                Gender gender = Gender.fromString(record.get("Gender")).get();
                long timestamp = Long.parseLong(record.get("Timestamp"));

                Student student = new Student(name, gender, stage, timestamp);
                this.students.put(student.getName(), student);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Collection<Student> getStudents()
    throws ServiceException {
        return Collections.unmodifiableCollection(new TreeSet<>(this.students.values()));
    }

    @Override
    public Optional<Student> getStudent(String name)
    throws ServiceException {
        Preconditions.checkNotNull(name, "The name is null");
        Student result = this.students.get(name);
        return Optional.fromNullable(result);
    }

    @Override
    public Collection<Student> getStudents(Predicate<Student> predicate)
    throws ServiceException {
        Preconditions.checkNotNull(predicate, "The predicate is null");
        Collection<Student> students = this.students.values();
        return Collections2.filter(students, predicate);
    }

    @Override
    public void addStudent(Student student)
    throws ServiceException {
        Preconditions.checkNotNull(student, "The student is null");
        this.students.put(student.getName(), student);

        this.persistData();
    }

    @Override
    public void addStudents(Collection<Student> students,
                            Predicate<Student> predicate)
    throws ServiceException {
        Preconditions.checkNotNull(students, "The list of students is null");
        Preconditions.checkNotNull(predicate, "The predicate is null");
        Collection<Student> studentCollection = Collections2.filter(students, predicate);
        for (Student student : studentCollection) {
            this.students.put(student.getName(), student);
        }

        this.persistData();
    }

    @Override
    public void removeStudent(Student student)
    throws ServiceException {
        Preconditions.checkNotNull(student, "The student is null");
        this.students.remove(student.getName());

        this.persistData();
    }

    @Override
    public void removeStudents(Predicate<Student> predicate)
    throws ServiceException {
        Preconditions.checkNotNull(predicate, "The predicate is null");
        Collection<Student> students = Collections2.filter(this.students.values(), predicate);
        for (Student student : students) {
            this.students.remove(student.getName());
        }

        this.persistData();
    }

    /**
     * Persist data.
     */
    private void persistData() {
        Preconditions.checkNotNull(students, "The list of students is null");
        URL url = this.getClass().getResource("/students.csv");
        File file = new File(url.getPath());
        if (file.exists()) {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(file);
                CSVPrinter printer = CSVFormat.newFormat(DELIMITER).withIgnoreSurroundingSpaces().withAllowMissingColumnNames().withRecordSeparator(
                    RECORD_SEPARATOR).print(fileWriter);
                for (String header : HEADERS) {
                    printer.print(header);
                }
                printer.println();

                for (Student student : this.students.values()) {
                    printer.print(student.getStage());
                    printer.print(student.getName());
                    printer.print(student.getGender());
                    printer.print(student.getTimestamp());
                    printer.println();
                }
                printer.close();
                fileWriter.close();
            } catch (IOException e) {
                LOGGER.error("mistakes persisting the data.");
            }
        }
    }
}
