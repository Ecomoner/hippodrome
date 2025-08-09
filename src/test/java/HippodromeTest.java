import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {


    @Mock
    public static Hippodrome mockHippodrome;

    @Test
    public void nullHippodromeTest(){
        Throwable excep = assertThrows(IllegalArgumentException.class,
                () -> {
                    mockHippodrome = new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.",excep.getMessage());
    }

    @Test
    public void nullHippodromeListTest(){
        List<Horse> horseList = new ArrayList<>();
        Throwable excep = assertThrows(IllegalArgumentException.class,
                () -> {
                    mockHippodrome = new Hippodrome(horseList);
                }
        );
        assertEquals("Horses cannot be empty.",excep.getMessage());
    }

    @Test
    public void getHorsesTest(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horseList.add(new Horse("" + i ,i,i));
        }
        mockHippodrome = new Hippodrome(horseList);
        assertEquals(horseList,mockHippodrome.getHorses());
    }

    @Test
    public void moveTest(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }
        mockHippodrome = new Hippodrome(horseList);
        mockHippodrome.move();
        for (Horse horse:horseList){
            verify(horse).move();
        }
    }
    @Test
    public void getWinnerTest(){
        Horse horse1 = new Horse("Конь1", 3, 1);
        Horse horse2 = new Horse("Конь2", 2, 3);
        Horse horse3 = new Horse("Конь3", 5, 9);
        Horse horse4 = new Horse("Конь4", 1, 7);
        mockHippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));
        assertSame(horse3,mockHippodrome.getWinner());
    }
}
