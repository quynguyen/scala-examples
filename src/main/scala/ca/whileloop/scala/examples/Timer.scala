package ca.whileloop.scala.examples

object Timer {
  def call( callback : () => Unit ) {
	  callback()
  }
  
  def timeFlies() {
    println("time flies like an arrow...")
  }
  
  def main( args : Array[String] ) {    
    // calling a named function
    call( timeFlies );
    
    // calling an anonymous function
    call( () => 
      println( "Called an anonymous function") )    
  }
}