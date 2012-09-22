package ca.whileloop.scala.examples

/**
 * A set of reusable math operations 
 */
trait MathTrait
{
  def identity( x: Int ) = { x }
  
  def square( x: Int ) = { x * x }
  
  def powerOfTwo( x: Int ): Int = {
    if ( x == 0 ) 1 else 2 * powerOfTwo( x - 1 );
  }
  
  def addition( x: Int, y: Int ) = { x + y }
  
  def multiply( x: Int, y: Int ) = { x * y }
}

/**
 * Define three functions that do an operation over a sequence
 * of numbers, from a to b, inclusive.
 */
object Funcs extends App with MathTrait
{
  def sumInts( a: Int, b: Int ): Int = {
    if ( a > b ) 0 else identity(a) + sumInts( a + 1, b );
  }
  
  def sumSquares( a: Int, b: Int ): Int = {
    if ( a > b ) 0 else square(a) + sumSquares( a + 1, b );
  }
  
  def sumPowerOfTwo( a: Int, b: Int): Int = {
    if ( a > b ) 0 else powerOfTwo(a) + sumPowerOfTwo( a + 1, b );
  }
   
  println( "Sum from 1 to 5: " + sumInts(1, 4) );
  println( "Sum from of squares 1 to 5: " + sumSquares(1, 5) );
  println( "Sum from of power-of-twos 1 to 5: " + sumPowerOfTwo(1, 5) );    
}

/*
 * Demonstrates First-Class functions, i.e. passing functions as a parameters.
 */
object FactorOutSum extends App with MathTrait
{
  /**
   * A reusable sum method, that takes a function "f", as a parameter,
   * and applies that function to the current value
   */
  def sum( f: (Int) => Int, a: Int, b: Int ): Int = {
    if ( a > b ) 0 else f( a ) + sum( f, a + 1, b );
  }  
  
  /**
   * Pass in the function
   */
  def sumInts( a: Int, b: Int ) = sum( identity, a, b );
  def sumSquares( a: Int, b: Int ) = sum( square, a, b );
  def sumPowerOfTwo( a: Int, b: Int ) = sum( powerOfTwo, a, b );
  
  println( "Sum from 1 to 5: " + sumInts(1, 4) );
  println( "Sum from of squares 1 to 5: " + sumSquares(1, 5) );
  println( "Sum from of power-of-twos 1 to 5: " + sumPowerOfTwo(1, 5) );      
}

/**
 * Demonstrate usage of anonymous functions
 */
object AnonymousFuncs extends App with MathTrait
{
  def sum( f: (Int) => Int, a: Int, b: Int ): Int = {
    if ( a > b ) 0 else f( a ) + sum( f, a + 1, b );
  }  
  
  // pass in an anonymous function that does the same as the method MathTrait#identity
  def sumInts( a: Int, b: Int ) = sum( (x: Int) => x, a, b );  
  
  // simplied anonymous method defintion.  (x: Int) => x, becomes, x => x * x
  def sumSquares( a: Int, b: Int ) = sum( x => x * x, a, b );
  def sumPowerOfTwo( a: Int, b: Int ) = sum( powerOfTwo, a, b );
  
  println( "Sum from 1 to 5: " + sumInts(1, 5) );
  println( "Sum from of squares 1 to 5: " + sumSquares(1, 5) );
  println( "Sum from of power-of-twos 1 to 5: " + sumPowerOfTwo(1, 5) );     
}

/**
 * Demonstrates currying, i.e, returning a function
 */
object CurryingFunc extends App with MathTrait
{
  /**
   * Return a function that does the summing
   */
  def sum( f: Int => Int ): (Int, Int) => Int = {
	def sumF( a: Int, b: Int ): Int  = {
      if ( a > b ) 0 else f( a ) + sumF( a + 1, b );
    }
	
	return sumF
  }
  
  /**
   * Can now ommit the parameter defintion of (a: Int, b: Int) from the functions
   */
  def sumInts = sum( x => x )
  def sumSquares = sum( x => x * x)
  def sumPowerOfTwo = sum( powerOfTwo )
  
  println( "Sum from 1 to 5: " + sumInts(1, 5) );
  println( "Sum from of squares 1 to 5: " + sumSquares(1, 5) );
  println( "Sum from of power-of-twos 1 to 5: " + sumPowerOfTwo(1, 5) );   
}

/**
 * Demonstrates the special syntax for a currying function
 */
object SimplifiedCurryingFunc extends App with MathTrait
{
  /**
   * Turned sum into a currying function.  Note the
   * secondary set parameters ( a: Int, b: Int ) -- these are the parameters for the "curried function"
   */
  def sum( f: Int => Int )( a: Int, b: Int): Int = {
    if ( a > b ) 0 else f( a ) + sum(f)( a + 1, b );
  }
  
  /**
   * Can now ommit the parameter defintion of (a: Int, b: Int) from the functions
   * 
   * Note the "_" following the assignment.  This means we're partially applying the function "sum"
   */
  def sumInts = sum( x => x ) _
  def sumSquares = sum( x => x * x) _
  def sumPowerOfTwo = sum( powerOfTwo ) _
  
  println( "Sum from 1 to 5: " + sumInts(1, 5) );
  println( "Sum from of squares 1 to 5: " + sumSquares(1, 5) );
  println( "Sum from of power-of-twos 1 to 5: " + sumPowerOfTwo(1, 5) );    
}

object TailRescursiveSum extends Application
{
  /**
   * A short currying function, that uses a nested function "iter" to hold the
   * "running result"
   */
  def sum( f: Int => Int )( a: Int, b: Int ): Int = {
	def iter( current: Int, runningResult: Int ): Int = {
	  if ( current > b ) runningResult else iter( current + 1, runningResult + f( current ))
	}
	iter( a, 0 )
  }
  
  def sumInts = sum( x => x ) _
  println( "Sum from 1 to 5: " + sumInts(1, 5) );  
}

object Product extends App
{
  /**
   * Exactly the same as sum, but instead of "*", instead of "+" 
   */
  def product( f: Int => Int )( a: Int, b: Int ): Int = {
	def iter( current: Int, runningResult: Int ): Int = {
	  if ( current > b ) runningResult else iter( current + 1, runningResult * f( current ))
	}
	iter( a, 1 )
  }
  
  def productInts = product( x => x ) _;
 
  def factorial( n: Int ) = { productInts(1, n) }
  
  println( "The factorial of 3 is: " + factorial( 3 ) );
}

object Operate extends App with MathTrait
{
  def operate( operator: (Int, Int) => Int, identityValue: Int )( f: Int => Int )( a: Int, b: Int ): Int = {
	def iter( current: Int, runningResult: Int ): Int = {
	  if ( current > b ) runningResult else iter( current + 1, operator( runningResult, f( current ) ) )
	}
	iter( a, identityValue )
  }  
  
  def product = operate( multiply, 1 ) _
  def productInts = product( x => x )
  def factorial( n: Int ) = productInts( 1, n )
  
  def sum = operate( addition, 0 ) _;
  def sumInts = sum( x => x )
  
  val value = 3
  println( "The factorial of " + value + " is " + factorial( value ) );
  println( "Sum from 1 to 5: " + sumInts(1, 5) );   
}

