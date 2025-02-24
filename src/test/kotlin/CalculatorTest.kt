import org.example.Calculator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorTest {
    private lateinit var calculator: Calculator

    @BeforeEach
    fun setUp(){
        calculator = Calculator()
    }

    @Test
    fun testPositiveAddition(){
        val res = calculator.add(10.0, 2.0);
        assertEquals<Double>(res, 12.0)
    }

    @Test
    fun testNegativeAddition(){
        val res = calculator.add(-10.0, -2.0);
        assertEquals<Double>(res, -12.0)
    }

    @Test
    fun testPositiveSubtraction(){
        val res = calculator.subtract(10.0, 2.0);
        assertEquals<Double>(res, 8.0)
    }

    @Test
    fun testNegativeSubtraction(){
        val res = calculator.subtract(-10.0, -2.0);
        assertEquals<Double>(res, -8.0)
    }

    @Test
    fun testPositiveMultiply(){
        val res = calculator.multiply(10.0, 2.0);
        assertEquals<Double>(res, 20.0)
    }

    @Test
    fun testNegativeMultiply(){
        val res = calculator.multiply(-10.0, -2.0);
        assertEquals<Double>(res, 20.0)
    }

    @Test
    fun testPositiveDivision(){
        val res = calculator.divide(10.0, 2.0);
        assertEquals<Double>(res, 5.0)
    }

    @Test
    fun testNegativeDivision(){
        val res = calculator.divide(-10.0, -2.0);
        assertEquals<Double>(res, 5.0)
    }

    @Test
    fun testDivideByZeroError(){
        assertThrows<IllegalArgumentException> {
            calculator.divide(10.0, 0.0)
        }
    }

}