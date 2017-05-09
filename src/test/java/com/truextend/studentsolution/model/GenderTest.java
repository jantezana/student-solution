package com.truextend.studentsolution.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * EducationalStageTest class.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public class GenderTest {

    @Test
    public void fromString()
    throws Exception {
        Gender expected = Gender.MALE;
        Gender actual = Gender.fromString("M").get();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void fromInvalidString()
    throws Exception {
        Gender.fromString("not exist").get();
    }
}