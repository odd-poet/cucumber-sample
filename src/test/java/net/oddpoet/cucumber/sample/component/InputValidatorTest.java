package net.oddpoet.cucumber.sample.component;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 28.
 * Time: 오후 11:07
 * To change this template use File | Settings | File Templates.
 */
public class InputValidatorTest {

    InputValidator sut = new InputValidator();

    @Test
    public void 총_선수_수는_숫자이어야_한다() {
        assertFalse(sut.isValidCount("A"));
        assertFalse(sut.isValidCount("+"));
        assertFalse(sut.isValidCount("~"));
        assertTrue(sut.isValidCount("3"));
    }

    @Test
    public void 총_선수_수는_2보다_커야한다() {
        assertFalse(sut.isValidCount("1"));
        assertFalse(sut.isValidCount("0"));
        assertFalse(sut.isValidCount("-20"));
    }

    @Test
    public void 선수정보_선수번호는_1보다_크거나_같고_총선수_수보다_작거나_같다() {
        // Given
        int numberOfPlayers = 3;

        // Then
        assertTrue(sut.isValidPlayerInfo(numberOfPlayers, 1, "1 2 2 3"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayers, 1, "A 1 B"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayers, 0, "0 1 1"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayers, 1, "1 1 0"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayers, 4, "4 1 1"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayers, 1, "1 1 -1"));
    }

    @Test
    public void 자기자신을_싫어할_수_없다() {
        int numberOfPlayer = 3;

        // Then
        assertTrue(sut.isValidPlayerInfo(numberOfPlayer, 1, "1 1 2"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayer, 1, "1 2 1 2"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayer, 1, "1 3 1 2 3"));
    }

    @Test
    public void 싫어하는_선수가_중복으로_나와서는_안된다() {
        int numberOfPlayer = 3;

        // Then
        assertFalse(sut.isValidPlayerInfo(numberOfPlayer, 1, "1 2 2 2"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayer, 1, "1 3 2 3 2"));
    }

    @Test
    public void 싫어하는선수수와_싫어하는_선수ID_목록의_수가_일치해야한다() {
        int numberOfPlayer = 5;

        // Then
        assertFalse(sut.isValidPlayerInfo(numberOfPlayer, 1, "1 0 1"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayer, 1, "1 3 2 3"));
        assertFalse(sut.isValidPlayerInfo(numberOfPlayer, 1, "1 1 2 3"));
    }

}
