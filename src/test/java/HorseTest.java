import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Mock
    private static Horse mockHorse;

    @Test
    public void nullHorseTest(){
        Throwable excep = assertThrows(IllegalArgumentException.class,
                () -> {
                    mockHorse = new Horse(null,3);
                }
        );
        assertEquals("Name cannot be null.",excep.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ","\t"})
    public void spaceNameHorseTest(String string){
        Throwable excep = assertThrows(IllegalArgumentException.class,
                () -> {
                    mockHorse = new Horse(string,3.0);
                }
                );
        assertEquals("Name cannot be blank.",excep.getMessage());
    }
    @Test
    public void twoNegativeParamTest(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    mockHorse = new Horse("Name",-2.0);
                });
        assertEquals("Speed cannot be negative.",exception.getMessage());
    }
    @Test
    public void threeNegativeParamTest(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    mockHorse = new Horse("Name",2.0,-10.0);
                });
        assertEquals("Distance cannot be negative.",exception.getMessage());
    }
    @Test
    public void getNameTest(){
        mockHorse = new Horse("Blaze",3.0);
        assertEquals("Blaze", mockHorse.getName());
    }
    @Test
    public void getSpeedTest(){
        mockHorse = new Horse("Blaze",3.0);
        assertEquals(3.0, mockHorse.getSpeed());
    }
    @Test
    public void getDistanceTest(){
        mockHorse = new Horse("Blaze",3.0,10.0);
        assertEquals(10.0, mockHorse.getDistance());
    }
    @Test
    public void moveTest(){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            new Horse("Конь",20,100).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }
    }

}
