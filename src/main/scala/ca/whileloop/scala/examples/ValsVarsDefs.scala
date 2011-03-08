package ca.whileloop.scala.examples

object ValsVarsDefs extends Application {
  
  // vars are variables
  var x = 5;
  
  // vals are final variables
  val y = 1 + x;
  
  // defs are only evalulated when referenced.
  def z = 1 + x;
  
  println( "x = 5" )
  println( "y = 6? " + (y == 6) )
  println( "z = 6? " + (z == 6) )
  
  println();
  
  // change x.
  x = 6;
  println( "x = 6\n" )  
  
  // y should stay the same
  println( "y == 6? " + (y == 6) )
  
  // but z should be different
  println( "z == 7? " + (z == 7) )
}


