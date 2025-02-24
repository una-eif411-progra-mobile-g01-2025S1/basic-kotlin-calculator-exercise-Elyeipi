package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val calculator = Calculator()
    val sum = calculator.add(5.0, 3.0)
    val diff = calculator.add(10.0, 4.0)
    val product = calculator.add(2.0, 3.0)
    val quotient = calculator.add(10.0, 2.0)

    println("_____Main use of Calculator_____")
    println("Sum of 5.0 + 3.0 = $sum")
    println("Sum of 10.0 + 4.0 = $diff")
    println("Sum of 2.0 + 3.0 = $product")
    println("Sum of 10.0 + 2.0 = $quotient")
}