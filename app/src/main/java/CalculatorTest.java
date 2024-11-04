import static org.junit.Assert.assertEquals;

import com.example.calculadora.Calculator;

import org.junit.Test;

public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    public void testSuma(){
        int total = (int) calculator.calcular(5, 3, "+");
        assertEquals ("5+3 operations not working correctly", 8, total);
    }

    @Test
    public void testResta(){
        int total = (int) calculator.calcular(20, 12, "-");
        assertEquals("20-12 operations not working correctly", 8, total);
    }

    @Test
    public void testMult(){
        int total = (int) calculator.calcular(4,2, "*");
        assertEquals("4*2 operations not working correctly", 8, total);
    }

    @Test
    public void testDiv(){
        int total = (int) calculator.calcular(16,2, "/");
        assertEquals("16/2 operations not working correctly", 8, total);
    }

}
