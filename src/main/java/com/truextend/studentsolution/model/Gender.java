package com.truextend.studentsolution.model;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import lombok.Getter;

/**
 * Gender enumerator.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public enum Gender {
    FEMALE("F"),
    MALE("M");

    @Getter
    String name;

    /**
     * Builds an instance of {@link com.truextend.studentsolution.model.Gender}
     *
     * @param name the name
     */
    Gender(final String name) {
        Preconditions.checkNotNull(name, "The name is null");
        this.name = name;
    }

    /**
     * Gets a gender by string name
     *
     * @param name the name
     * @return the gender
     */
    public static Optional<Gender> fromString(String name) {
        Preconditions.checkNotNull(name, "The name is null");
        Gender result = null;
        for (Gender gender : Gender.values()) {
            if (gender.getName().equalsIgnoreCase(name)) {
                result = gender;
                break;
            }
        }

        return Optional.fromNullable(result);
    }

    @Override
    public String toString() {
        return name;
    }
}
