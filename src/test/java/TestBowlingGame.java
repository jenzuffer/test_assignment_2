import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.Callable;

public class TestBowlingGame {
    private Game game = new Game();

    @BeforeEach
    public void createNewGameObject() {
        game = new Game();
    }

    @Test
    public void testconstructor() {
        //Arrange
        //Act
        //Assert
        Assertions.assertNotNull(game);
    }

    private void rollMany(int nth, int pins) {
        for (int i = 0; i < nth; i++) {
            game.rolls(pins);
        }
    }

    @Test
    public void testgutter_game() {
        rollMany(20, 0);
        Assertions.assertEquals(0, game.score());
    }

    @Test
    public void testAllOnes() {
        rollMany(20, 1);
        Assertions.assertEquals(20, game.score());
    }

    @Test
    public void testonespare() {
        rollSpare();
        game.rolls(3);
        rollMany(17, 0);
        Assertions.assertEquals(16, game.score());
    }

    @Test
    public void testoneStrike() {
        rollStrike();
        game.rolls(3);
        game.rolls(4);
        rollMany(16, 0);
        Assertions.assertEquals(24, game.score());
    }

    @Test
    public void testperfectGame() {
        rollMany(12, 10);
        Assertions.assertEquals(300, game.score());
    }

    private void rollStrike() {
        game.rolls(10);
    }

    private void rollSpare() {
        game.rolls(5);
        game.rolls(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, Integer.MIN_VALUE})
    public void testIntegerMaxMin(int input) {
        try {
            System.out.println("input: " + input);
            for (int iterator = 0; iterator < 50; iterator++) {
                game.rolls(input);
                game.score();
            }
            System.out.println("testIntegerMaxMin: " + game.score());
            Assertions.assertTrue(game.score() >= 0);
            assert true;
        } catch (Exception e) {
            assert false;
        }

    }

    @Test
    public void testHandleInvalidRolls() {
        try {
            for (int iterator = -5000; iterator < 5000; iterator++) {
                game.rolls(iterator);
                game.score();
            }
            System.out.println("testHandleInvalidRolls: " + game.score());
            Assertions.assertTrue(game.score() > 0);
            assert true;
        } catch (ArrayIndexOutOfBoundsException array_exception) {
            assert false;
        }
    }

    @Test
    public void testSimpleThreadSafetyOfProgram() {
        int max_score = 300;
        for (int iterator = -50; iterator < 50; iterator++) {
            int finalIterator = iterator;
            try {
                Callable<Integer> threadScore = game.rollsThreaded(iterator);
                {
                    Integer calledScore = threadScore.call();
                    System.out.println("calledScore: " + calledScore);
                    Assertions.assertTrue(calledScore <= max_score);
                    Assertions.assertTrue(calledScore > 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
