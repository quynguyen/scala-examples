package ca.whileloop.scala.examples

object CallByValueCallByName extends Application {
  /**
   * Call by value.  y is evaluated before the method is invoked.
   */
  def callByValue( y: Int )
  {
    println( "1st call: " +  y );
    println( "2nd call: " +  y );
  }  
  
  /**
   * Call by name. y isn't evaluated until it's referenced, and it's evaluated each time it's referenced.
   */
  def callByName( y: => Int )
  {
    println( "1st call: " +  y );
    println( "2nd call: " +  y );
  }  
  
  // "_" means default value.  For Int, that's 0. For objects, it's null, and for booleans, it's false.
  var a: Int = _
  
  def incAndGetA(): Int =
  {
    a = a + 1;
    return a;
  }    
  
  a = 0;
  println( "Call by Value: " );
  callByValue( incAndGetA );
  
  println();
  
  a = 0;
  println( "Call by Name: " );
  callByName( incAndGetA );
}
