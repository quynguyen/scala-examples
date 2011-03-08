package ca.whileloop.scala.examples

object TailRecursiveFactorial extends Application {
  
  def factorial(n: Int): Int = {
  
    def fact(n: Int, result: Int): Int = {
      val next = n - 1
      if (n == 0) result else fact( next, n * result );
    }
    
    return fact(n, 1 );
  }
  
  println( factorial( 5 ) );
}
