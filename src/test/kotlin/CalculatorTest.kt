import org.example.Calculator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.IllegalArgumentException
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
            Pair(1e308, 1e308),
            Pair(-1e308, -1e308)
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
    @CsvSource(
        "10.0, 2.0, 8.0", "-10.0, -2.0, -8.0", "1e308, 1e307, 9.0e307",
        "-1e-308, 1e-308, -2e-308", "-1e-307, 1e-308, -1.1e-307"
    )
    fun testSubtraction(a : Double, b : Double, expected: Double){
        val res = calculator.subtract(a, b)
        assertEquals(expected, res, 1e300)
    }

    @ParameterizedTest
    @CsvSource(
        "10.0, 2.0, 12.0", "-10.0, -2.0, -12.0", "1e308, 1e307, 1.1e308",
        "1e-308, 1e-308, 2e-308", "1e-308, 1e-307, 1.1e-307"
    )
    fun testAddition(a : Double, b: Double, expected : Double){
        val res = calculator.add(a, b)
        assertEquals(expected, res, 1e300)
    }

    @ParameterizedTest
    @CsvSource(
        "10.0, 2.0, 20.0", "-10.0, -2.0, 20.0", "9e153, 1e154, 9e307",
        "1e-308, 1e-308, 1e-616", "-1e-154, 1e-154, -1e-308"
        )
    fun testMultiply(a : Double, b : Double, expected: Double){
        val res = calculator.multiply(a, b)
        assertEquals(expected, res)
    }

    @ParameterizedTest
    @CsvSource(
        "10.0, 2.0, 5.0", "-10.0, -2.0, 5.0", "-1e308, 1e154, -1e154",
        "1e-308, 1e-308, 1.0", "-1e-308, 1e-308, -1.0"
    )
    fun testDivision(a : Double, b : Double, expected: Double){
        val res = calculator.divide(a, b)
        assertEquals(expected, res, 1e290)
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

    @Test
    fun testSumNaNError(){
        assertThrows<IllegalArgumentException> {
            calculator.add(Double.NaN, 1.0)
        }
    }

    @Test
    fun testSubtractNaNError(){
        assertThrows<IllegalArgumentException> {
            calculator.subtract(Double.NaN, 1.0)
        }
    }

    @Test
    fun testMultiplyNaNError(){
        assertThrows<IllegalArgumentException> {
            calculator.multiply(Double.NaN, 1.0)
        }
    }

    @Test
    fun testDivideNaNError(){
        assertThrows<IllegalArgumentException> {
            calculator.divide(Double.NaN, 1.0)
        }
    }

}