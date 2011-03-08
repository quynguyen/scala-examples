package ca.whileloop.scala.examples

/**
 * For-Comprehensions are syntatic sugar for list operations such as
 * map, flatMap, and filter
 */
object ForComprehensions extends Application {
  
  var list = List(1,2,3);
  
  var squareList1 = for( x <- list ) yield x * x
  
  var squareList2 = list.map( x => x * x );
  
  println( squareList1.equals( squareList2 ) )
}


