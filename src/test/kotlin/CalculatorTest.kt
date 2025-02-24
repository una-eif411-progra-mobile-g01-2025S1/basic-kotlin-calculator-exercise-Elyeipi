import org.example.Calculator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import java.util.stream.Stream
import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorTest {
    private lateinit var calculator: Calculator

    companion object{
        @JvmStatic
        fun edgeErrorCasesDivide(): Stream<Pair<Double, Double>> = Stream.of(
            Pair(Double.POSITIVE_INFINITY, 2.0),
            Pair(Double.NEGATIVE_INFINITY, 2.0)
        )
        @JvmStatic
        fun edgeErrorCasesSum() : Stream<Pair<Double, Double>> = Stream.of(
            Pair(Double.POSITIVE_INFINITY, 1.0),
            Pair(Double.NEGATIVE_INFINITY, -1.0)
        )
        @JvmStatic
        fun edgeErrorCasesMultiplySubtract() : Stream<Pair<Double, Double>> = Stream.of(
            Pair(Double.POSITIVE_INFINITY, 1.0),
            Pair(Double.NEGATIVE_INFINITY, 1.0)
        )
    }

    @BeforeEach
    fun setUp(){
        calculator = Calculator()
    }

    @ParameterizedTest
    @CsvSource("10.0, 2.0, 8.0", "-10.0, -2.0, -8.0")
    fun testSubtraction(a : Double, b : Double, expected: Double){
        val res = calculator.subtract(a, b);
        assertEquals<Double>(expected, res)
    }

    @ParameterizedTest
    @CsvSource("10.0, 2.0, 12.0", "-10.0, -2.0, -12.0")
    fun testAddition(a : Double, b: Double, expected : Double){
        val res = calculator.add(a, b);
        assertEquals<Double>(expected, res)
    }

    @ParameterizedTest
    @CsvSource("10.0, 2.0, 20.0", "-10.0, -2.0, 20.0")
    fun testMultiply(a : Double, b : Double, expected: Double){
        val res = calculator.multiply(a, b);
        assertEquals<Double>(expected, res)
    }

    @ParameterizedTest
    @CsvSource("10.0, 2.0, 5.0", "-10.0, -2.0, 5.0")
    fun testPositiveDivision(a : Double, b : Double, expected: Double){
        val res = calculator.divide(a, b);
        assertEquals<Double>(expected, res)
    }

    @Test
    fun testDivideByZeroError(){
        assertThrows<IllegalArgumentException> {
            calculator.divide(10.0, 0.0)
        }
    }

    @ParameterizedTest
    @MethodSource("edgeErrorCasesSum")
    fun testSumEdgeError(data : Pair<Double, Double>){
        assertThrows<IllegalArgumentException> {
            val (a, b) = data
            calculator.add(a, b)
        }
    }
    @ParameterizedTest
    @MethodSource("edgeErrorCasesMultiplySubtract")
    fun testSubtractEdgeError(data : Pair<Double, Double>){
        assertThrows<IllegalArgumentException> {
            val (a, b) = data
            calculator.subtract(a, b)
        }
    }

    @ParameterizedTest
    @MethodSource("edgeErrorCasesMultiplySubtract")
    fun testMultiplyEdgeError(data : Pair<Double, Double>){
        assertThrows<IllegalArgumentException> {
            val (a, b) = data
            calculator.multiply(a, b)
        }
    }

    @ParameterizedTest
    @MethodSource("edgeErrorCasesDivide")
    fun testDivideEdgeError(data : Pair<Double, Double>){
        assertThrows<IllegalArgumentException> {
            val (a, b) = data
            calculator.divide(a, b)
        }
    }

}