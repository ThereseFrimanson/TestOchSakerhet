package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

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
        //assertFalse(Username.validate(null));  går inte igenom då det vill användas length() i annat läge
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

    @ParameterizedTest
    @ValueSource(strings = {"bosse", "AnnA", "usernam123", "U-s_e_R-99"})
    void correctUsernames(String strings) {
        boolean result = Username.validate(strings);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"bo", " ", "/=€%", "å äö å äö"})
    void incorrectUsernames(String strings) {
        boolean result = Username.validate(strings);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/correctUsernames.csv", numLinesToSkip  = 1) 
        void correctUsernameWithCsv (String username){
            assertThat(Username.validate(username)).isTrue();
        }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/incorrectUsernames.csv", numLinesToSkip  = 1) 
        void incorrectUsernameWithCsv (String username){
            assertThat(Username.validate(username)).isFalse();
        }
    
    @ParameterizedTest
    @NullSource
        void nullUsername (String username){
            assertThat(Username.validate(username)).isFalse();
        }


}
