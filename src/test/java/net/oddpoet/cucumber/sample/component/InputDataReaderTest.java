package net.oddpoet.cucumber.sample.component;

import net.oddpoet.cucumber.sample.model.InputData;
import net.oddpoet.cucumber.sample.testsupport.MockBaseTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.BufferedReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 29.
 * Time: 오전 12:33
 * To change this template use File | Settings | File Templates.
 */

public class InputDataReaderTest extends MockBaseTest {

    @Mock
    @Qualifier("inputReader")
    BufferedReader inputReader;

    @Spy
    @Autowired
    InputValidator inputValidator;

    @InjectMocks
    @Autowired
    InputDataReader sut;

    @Test
    public void test_no_error() throws IOException {
        // Given
        when(inputReader.readLine()).thenReturn(
                "5",
                "1 1 2",
                "2 1 3",
                "3 0",
                "4 2 2 3",
                "5 1 4"
        );

        // When
        InputData data = sut.readInput();

        // Then
        verify(inputValidator, times(1)).isValidCount(anyString());
        verify(inputValidator, times(5)).isValidPlayerInfo(eq(5), anyInt(), anyString());
        assertThat(data.getExclusivePlayers(3), hasItem(4));
    }

    @Test
    public void test_fail_case() throws IOException {
        // Given
        when(inputReader.readLine()).thenReturn(
                "A",       // 재입력을 요구할것임.
                "5",
                "1 1 2",
                "2 1 3",
                "3 0 0",   // 재입력 요구
                "3 0",
                "4 2 2 3",
                "5 1 4"
        );

        // When
        InputData data = sut.readInput();

        // Then
        verify(inputValidator, times(2)).isValidCount(anyString());
        verify(inputValidator, times(6)).isValidPlayerInfo(eq(5), anyInt(), anyString());
        assertThat(data.getExclusivePlayers(3), hasItem(4));
    }

}
