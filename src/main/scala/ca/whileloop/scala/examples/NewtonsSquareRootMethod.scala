package ca.whileloop.scala.examples

import java.lang.Math._

object NewtonsSquareRootMethods {
  def main(args : Array[String]) : Unit = {
    val num: Double = 25;
    print( "The square " + sqrt(num) )
  }
  
  
  /**
   * Demonstrates nested functions
   */
  def sqrt(x: Double) = {
    def sqrtIter(guess: Double, x: Double): Double = {
      if (isGoodEnough(guess, x)) guess
      	else sqrtIter(improve(guess, x), x)
    }
    def improve(guess: Double, x: Double) = {
      (guess + x / guess) / 2
    }
    def isGoodEnough(guess: Double, x: Double) = {
      abs(square(guess) - x) < 0.001
    }
    def abs(i: Double) = { 
      if ( i < 0 ) i * -1 else i
    }
    def square(i: Double) = {
      i * i
    }
    
    sqrtIter(1.0, x)
  }  
}
