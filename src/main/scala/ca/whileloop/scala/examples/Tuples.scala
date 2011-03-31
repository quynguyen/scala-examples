package ca.whileloop.scala.examples

/**
 * Returning more than one result
 */
object Tuples extends Application {

  def divmod(x: Int, y: Int) = {
	  new Tuple2[Int, Int](x / y, x % y)
  }
  
  var result = divmod(7, 2);
  
  println( "The quotient is: " + result._1 + " and the remainder is: " + result._2 );
}

/**
 * Syntactic sugar for tuples
 */
object TuplesSyntaticSugar extends Application
{
  def divmod(x: Int, y: Int): (Int, Int) = {
	  (x / y, x % y)
  }
  
  var result = divmod(7, 2);
  
  println( "The quotient is: " + result._1 + " and the remainder is: " + result._2 );  
}

/**
 * Tuples are also case classes, so they can be used with "match"
 */
object TuplesAsCases extends Application
{
  def divmod(x: Int, y: Int): (Int, Int) = {
	  (x / y, x % y)
  }
  
  divmod(7, 2) match {
    case (n,d) => println( "The quotient is: " + n + " and the remainder is: " + d );  
  } 
}


