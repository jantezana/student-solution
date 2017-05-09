package com.truextend.studentsolution.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * EducationalStageTest class.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public class EducationalStageTest {

    @Test
    public void fromString()
    throws Exception {
        EducationalStage expected = EducationalStage.ELEMENTARY;
        EducationalStage actual = EducationalStage.fromString("Elementary").get();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void fromInvalidString()
    throws Exception {
        EducationalStage.fromString("not exist").get();
    }

}