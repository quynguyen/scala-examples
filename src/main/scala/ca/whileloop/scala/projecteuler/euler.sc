object euler {
  println("Welcome to the Scala worksheet")
  
  val x = Stream.cons( 1, Stream.cons( 2, Stream.Empty ))

	x match {
		case first :: second :: xs => println( first )
	}
}