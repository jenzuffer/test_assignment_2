import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStringUtelizer {

    @Test
    public void Test_constructor(){
        //Arrange
        String str = "";
        //Act
        StringUtelizer su = new StringUtelizer(str);
        //Assert
        Assertions.assertNotNull(su);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    public void Test_invalid_input(String input){
        //arrange
        String expected_result = "invalid input";
        //act
        StringUtelizer su = new StringUtelizer(input);
        //assert
        Assertions.assertEquals(su.reversify_string(), expected_result);
        Assertions.assertEquals(su.uppercase_string(), expected_result);
        Assertions.assertEquals(su.lowercase_string(), expected_result);
    }

    @ParameterizedTest
    @CsvSource({"ABC,CBA", "tEst,tsEt", "Java,avaJ"})
    public void test_reversify_input(String input, String expected){
        //arrange
        //act
        StringUtelizer su = new StringUtelizer(input);
        //assert
        Assertions.assertEquals(su.reversify_string(), expected);
    }

    @ParameterizedTest
    @CsvSource({"abc,ABC", "tEst,TEST", "Java,JAVA"})
    public void test_upper_case_input(String input, String expected){
        //arrange
        //act
        StringUtelizer su = new StringUtelizer(input);
        //assert
        Assertions.assertEquals(su.uppercase_string(), expected);
    }

    @ParameterizedTest
    @CsvSource({"ABC,abc", "TEST,test", "JAVA,java"})
    public void test_lower_case_input(String input, String expected){
        //arrange
        //act
        StringUtelizer su = new StringUtelizer(input);
        //assert
        Assertions.assertEquals(su.lowercase_string(), expected);
    }
}
