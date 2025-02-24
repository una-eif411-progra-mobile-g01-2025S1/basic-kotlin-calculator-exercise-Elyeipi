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
        assertEquals<Double>(12.0, res)
    }

    @Test
    fun testNegativeAddition(){
        val res = calculator.add(-10.0, -2.0);
        assertEquals<Double>(-12.0, res)
    }

    @Test
    fun testPositiveSubtraction(){
        val res = calculator.subtract(10.0, 2.0);
        assertEquals<Double>(8.0, res)
    }

    @Test
    fun testNegativeSubtraction(){
        val res = calculator.subtract(-10.0, -2.0);
        assertEquals<Double>(-8.0, res)
    }

    @Test
    fun testPositiveMultiply(){
        val res = calculator.multiply(10.0, 2.0);
        assertEquals<Double>(20.0, res)
    }

    @Test
    fun testNegativeMultiply(){
        val res = calculator.multiply(-10.0, -2.0);
        assertEquals<Double>(20.0, res)
    }

    @Test
    fun testPositiveDivision(){
        val res = calculator.divide(10.0, 2.0);
        assertEquals<Double>(5.0, res)
    }

    @Test
    fun testNegativeDivision(){
        val res = calculator.divide(-10.0, -2.0);
        assertEquals<Double>(5.0, res)
    }

    @Test
    fun testDivideByZeroError(){
        assertThrows<IllegalArgumentException> {
            calculator.divide(10.0, 0.0)
        }
    }

    @Test
    fun testSumPositiveEdgeError(){
        assertThrows<IllegalArgumentException> {
            calculator.add(Double.POSITIVE_INFINITY, 1.0)
        }
    }

    @Test
    fun testSumNegativeEdgeError(){
        assertThrows<IllegalArgumentException> {
            calculator.add(Double.NEGATIVE_INFINITY, -1.0)
        }
    }

    @Test
    fun testSubtractPositiveEdgeError(){
        assertThrows<IllegalArgumentException> {
            calculator.subtract(Double.POSITIVE_INFINITY, 1.0)
        }
    }

    @Test
    fun testSubtractNegativeEdgeError(){
        assertThrows<IllegalArgumentException> {
            calculator.subtract(Double.NEGATIVE_INFINITY, 1.0)
        }
    }

    @Test
    fun testMultiplyPositiveEdgeError(){
        assertThrows<IllegalArgumentException> {
            calculator.multiply(Double.POSITIVE_INFINITY, 1.0)
        }
    }

    @Test
    fun testMultiplyNegativeEdgeError(){
        assertThrows<IllegalArgumentException> {
            calculator.multiply(Double.NEGATIVE_INFINITY, 1.0)
        }
    }

    @Test
    fun testDividePositiveEdgeError(){
        assertThrows<IllegalArgumentException> {
            calculator.divide(Double.POSITIVE_INFINITY, 2.0)
        }
    }

    @Test
    fun testDivideNegativeEdgeError(){
        assertThrows<IllegalArgumentException> {
            calculator.divide(Double.NEGATIVE_INFINITY, 2.0)
        }
    }

}