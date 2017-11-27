package com.example.assertions;

import com.example.models.People;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class PeopleAssertion extends AbstractAssert<PeopleAssertion, People> {

    private PeopleAssertion(People people, Class<?> selfType) {
        super(people, selfType);
    }

    public static final PeopleAssertion assertThat(final People actual) {
        return new PeopleAssertion(actual, PeopleAssertion.class);
    }

    // Here custom assertion methods starts
    public PeopleAssertion hasName(final String expectedName) {
        Assertions.assertThat(actual.getName()).isEqualTo(expectedName);
        return this;
    }
}
