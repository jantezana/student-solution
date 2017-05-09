package com.truextend.studentsolution.model;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import lombok.Getter;

/**
 * EducationalStage enumerator.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public enum EducationalStage {
    KINDER("kinder"),
    ELEMENTARY("Elementary"),
    HIGH("High"),
    UNIVERSITY("University");

    @Getter
    String stage;

    /**
     * Creates an instance of {@link com.truextend.studentsolution.model.EducationalStage}
     *
     * @param stage the stage
     */
    EducationalStage(final String stage) {
        Preconditions.checkNotNull(stage, "The stage is null");
        this.stage = stage;
    }

    /**
     * Gets a educational stage by string stage.
     *
     * @param stage the stage
     * @return the educational stage
     */
    public static Optional<EducationalStage> fromString(String stage) {
        EducationalStage result = null;
        for (EducationalStage educationalStage : EducationalStage.values()) {
            if (educationalStage.getStage().equalsIgnoreCase(stage)) {
                result = educationalStage;
                break;
            }
        }

        return Optional.fromNullable(result);
    }

    @Override
    public String toString() {
        return stage;
    }
}
