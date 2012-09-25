package ca.whileloop.scala.scalabyexample

object Exercise521 extends App {

  /**
   * Exercise 5.2.1
   */
  def sum(f: Int => Double)(a: Int, b: Int): Double = {
    def iter(a: Int, acc: Double): Double = {
      if (a > b)
        acc
      else
        iter(a + 1, acc + f(a))
    }
    iter(a, 0)
  }

  /**
   * Exercise 5.2.2
   */
  def product(f: Int => Double)(a: Int, b: Int): Double = {
    def iter(a: Int, acc: Double): Double = {
      if (a > b)
        acc
      else
        iter(a + 1, acc + f(a))
    }
    iter(a, 0)
  }

  /**
   * Exercise 5.2.3
   */
  def factorial(n: Int): Double = {
    product(x => x)(1, n)
  }

  /**
   * Exercise 5.2.4
   */
  abstract class Operator {
    def identity: Double
    def apply(a: Double, b: Double): Double
  }
  case object Add extends Operator {
    def identity: Double = 0
    def apply(a: Double, b: Double): Double = a + b
  }
  case object Multiply extends Operator {
    def identity: Double = 1
    def apply(a: Double, b: Double): Double = a * b
  }

  object OperateOnRange {
    def apply(operator: Operator)(a: Int, b: Int): Double = {
      def iter(a: Int, acc: Double): Double = {
        if (a > b)
          acc
        else
          iter(a + 1, operator(acc, a))
      }
      iter(a, operator.identity)
    }
  }
  OperateOnRange(Add)(1, 2)

  /**
   * Exercise 5.2.5
   */
  def fixedPoint(f: Double => Double)(initialGuess: Double): Double = {
    def iter(guess: Double): Double = {
      def isCloseEnough(next: Double): Boolean = {
        scala.math.abs((next - guess) / guess) < 0.0001
      }
      val next = f(guess);
      println(next)
      if (isCloseEnough(next))
        next
      else
        iter(next)
    }

    iter(initialGuess)
  } 

  def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2)(1.0)
  sqrt(4) 

  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

  def sqrt2(x: Double) = fixedPoint(averageDamp(y => x / y))(1.0)
}