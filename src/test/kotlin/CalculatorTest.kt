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

/*
* CalculatorTest performs actions that test Calculator's capabilities of basic mathematical operations
* */
class CalculatorTest {
    /*
    * Instance of Calculator used by this test suite
    *
    *  */
    private lateinit var calculator: Calculator

    companion object{
        /*
        * Contains Divide test cases
        * */
        @JvmStatic
        fun edgeErrorCasesDivide(): Stream<Pair<Double, Double>> = Stream.of(
            Pair(Double.POSITIVE_INFINITY, 2.0),
            Pair(Double.NEGATIVE_INFINITY, 2.0)
        )
        /*
        * Contains addition test cases
        * */
        @JvmStatic
        fun edgeErrorCasesSum() : Stream<Pair<Double, Double>> = Stream.of(
            Pair(1e308, 1e308),
            Pair(-1e308, -1e308)
        )

        /*
        * Contains Multiply test cases
        * */
        @JvmStatic
        fun edgeErrorCasesMultiplySubtract() : Stream<Pair<Double, Double>> = Stream.of(
            Pair(Double.POSITIVE_INFINITY, 1.0),
            Pair(Double.NEGATIVE_INFINITY, 1.0)
        )
    }

    /*
    * Initialize an instance of Calculator
    * */
    @BeforeEach
    fun setUp(){
        calculator = Calculator()
    }

    /*
    * Performs 5 test cases:
    * - 1: Positive subtract
    * - 2: Negative subtract
    * - 3: Long subtract
    * - 4: First Small subtract
    * - 5: Second Small subtract
    *  */
    @ParameterizedTest
    @CsvSource(
        "10.0, 2.0, 8.0", "-10.0, -2.0, -8.0", "1e308, 1e307, 9.0e307",
        "-1e-308, 1e-308, -2e-308", "-1e-307, 1e-308, -1.1e-307"
    )
    fun testSubtraction(a : Double, b : Double, expected: Double){
        val res = calculator.subtract(a, b)
        assertEquals(expected, res, 1e300)
    }

    /*
    * Performs 5 test cases:
    * - 1: Positive addition
    * - 2: Negative addition
    * - 3: Long addition
    * - 4: First Small addition
    * - 5: Second Small addition
    *  */
    @ParameterizedTest
    @CsvSource(
        "10.0, 2.0, 12.0", "-10.0, -2.0, -12.0", "1e308, 1e307, 1.1e308",
        "1e-308, 1e-308, 2e-308", "1e-308, 1e-307, 1.1e-307"
    )
    fun testAddition(a : Double, b: Double, expected : Double){
        val res = calculator.add(a, b)
        assertEquals(expected, res, 1e300)
    }

    /*
    * Performs 5 test cases:
    * - 1: Positive product
    * - 2: Negative product
    * - 3: Long product
    * - 4: First Small product
    * - 5: Second Small product
    *  */
    @ParameterizedTest
    @CsvSource(
        "10.0, 2.0, 20.0", "-10.0, -2.0, 20.0", "9e153, 1e154, 9e307",
        "1e-308, 1e-308, 1e-616", "-1e-154, 1e-154, -1e-308"
        )
    fun testMultiply(a : Double, b : Double, expected: Double){
        val res = calculator.multiply(a, b)
        assertEquals(expected, res)
    }

    /*
    * Performs 5 test cases:
    * - 1: Positive quotie
    * - 2: Negative quotie
    * - 3: Long quotie
    * - 4: First Small quotie
    * - 5: Second Small quotie
    *  */
    @ParameterizedTest
    @CsvSource(
        "10.0, 2.0, 5.0", "-10.0, -2.0, 5.0", "-1e308, 1e154, -1e154",
        "1e-308, 1e-308, 1.0", "-1e-308, 1e-308, -1.0"
    )
    fun testDivision(a : Double, b : Double, expected: Double){
        val res = calculator.divide(a, b)
        assertEquals(expected, res, 1e290)
    }

    /*
    * Evaluates DivideByZeroException
    * */
    @Test
    fun testDivideByZeroError(){
        val exception = assertThrows<IllegalArgumentException> {
            calculator.divide(10.0, 0.0)
        }
        assertEquals(Calculator.exceptionMessagesDictionary["DivideByZero"], exception.message)
    }

    /*
    * Evaluates Infinite Addition result
    * */
    @ParameterizedTest
    @MethodSource("edgeErrorCasesSum")
    fun testSumEdgeError(data : Pair<Double, Double>){
        val exception = assertThrows<IllegalArgumentException> {
            val (a, b) = data
            calculator.add(a, b)
        }
        assertEquals(Calculator.exceptionMessagesDictionary["SumInfinite"], exception.message)
    }
    /*
    * Evaluates Infinite subtract result
    * */
    @ParameterizedTest
    @MethodSource("edgeErrorCasesMultiplySubtract")
    fun testSubtractEdgeError(data : Pair<Double, Double>){
        val exception = assertThrows<IllegalArgumentException> {
            val (a, b) = data
            calculator.subtract(a, b)
        }

        assertEquals(Calculator.exceptionMessagesDictionary["SubtractInfinite"], exception.message)
    }

    /*
    * Evaluates Infinite product
    * */
    @ParameterizedTest
    @MethodSource("edgeErrorCasesMultiplySubtract")
    fun testMultiplyEdgeError(data : Pair<Double, Double>){
        val exception = assertThrows<IllegalArgumentException> {
            val (a, b) = data
            calculator.multiply(a, b)
        }
        assertEquals(Calculator.exceptionMessagesDictionary["MultiplyInfinite"], exception.message)
    }

    /*
    * Evaluates Infinite quotie result
    * */
    @ParameterizedTest
    @MethodSource("edgeErrorCasesDivide")
    fun testDivideEdgeError(data : Pair<Double, Double>){
        val exception = assertThrows<IllegalArgumentException> {
            val (a, b) = data
            calculator.divide(a, b)
        }

        assertEquals(Calculator.exceptionMessagesDictionary["DivideInfinite"], exception.message)
    }

    /*
    * Evaluates Infinite NaN addition
    * */
    @Test
    fun testSumNaNError(){
        val exception = assertThrows<IllegalArgumentException> {
            calculator.add(Double.NaN, 1.0)
        }
        assertEquals(Calculator.exceptionMessagesDictionary["NaNArgument"], exception.message)
    }

    /*
    * Evaluates Infinite NaN subtract
    * */
    @Test
    fun testSubtractNaNError(){
        val exception = assertThrows<IllegalArgumentException> {
            calculator.subtract(Double.NaN, 1.0)
        }

        assertEquals(Calculator.exceptionMessagesDictionary["NaNArgument"], exception.message)
    }

    /*
    * Evaluates Infinite NaN product
    * */
    @Test
    fun testMultiplyNaNError(){
        val exception = assertThrows<IllegalArgumentException> {
            calculator.multiply(Double.NaN, 1.0)
        }

        assertEquals(Calculator.exceptionMessagesDictionary["NaNArgument"], exception.message)
    }

    /*
    * Evaluates Infinite NaN quotie
    * */
    @Test
    fun testDivideNaNError(){
        val exception = assertThrows<IllegalArgumentException> {
            calculator.divide(Double.NaN, 1.0)
        }

        assertEquals(Calculator.exceptionMessagesDictionary["NaNArgument"], exception.message)
    }

}