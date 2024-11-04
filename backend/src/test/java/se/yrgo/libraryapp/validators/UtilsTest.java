package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/correctNames.csv", numLinesToSkip = 1)
    void correctNameInLowerCase(String nameTest) {
        assertThat(Utils.onlyLettersAndWhitespace(nameTest)).isLowerCase()
                                                            .matches("^[a-z\\s]+$")
                                                            .isNotNull()
                                                            .isNotBlank();

    }

    @ParameterizedTest
    @ValueSource(strings = {"1337", "4nn4", "80553"})
    void leetToCorrectName(String strings) {
        String result = Utils.cleanAndUnLeet(strings);
        assertThat(result).doesNotContainPattern("[0-9]");
    }
}
