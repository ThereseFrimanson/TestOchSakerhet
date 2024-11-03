package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class UsernameTest {
    @Test
    void correctUsername() {
        assertTrue(Username.validate("bosse"));
        assertTrue(Username.validate("annalena1")); // detta test gick ej igenom.
        assertTrue(Username.validate("bosse_lena"));
        assertTrue(Username.validate("oSk@r"));
    }

    @Test
    void incorrectUsername() {
        assertFalse(Username.validate("name with space")); // spaces
        assertFalse(Username.validate("")); // empty
        assertFalse(Username.validate("    ")); // four spaces
        assertFalse(Username.validate("USERNAME!")); //using !
    }

    @Test
    void validUsername() {
        assertThat(Username.validate("bosse")).isTrue();
        assertThat(Username.validate("annalena0")).isTrue();
        assertThat(Username.validate("bosse_lena")).isTrue();
        assertThat(Username.validate("oSk@r")).isTrue();
    }

    @Test
    void invalidUsername() {
        assertThat(Username.validate("?????")).isFalse();
        assertThat(Username.validate("23")).isFalse();
        assertThat(Username.validate("")).isFalse();
        assertThat(Username.validate("blank space")).isFalse();
    }
}
