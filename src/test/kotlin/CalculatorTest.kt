import org.example.Calculator
import org.junit.jupiter.api.BeforeEach

class CalculatorTest {
    private lateinit var calculator: Calculator

    @BeforeEach
    fun setUp(){
        calculator = Calculator()
    }


}