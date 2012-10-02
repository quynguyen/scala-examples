package ca.whileloop.scala.examples

object Lists extends Application {
  
  def isort(xs: List[Int]): List[Int] = {
    
    def insert( value: Int, sorted: List[Int] ): List[Int] = {
      if ( sorted.isEmpty ) {
    	value :: Nil
      }
      else if ( value <= sorted.head ) 
        value :: sorted 
      else 
        insert( value, sorted.tail )
    }
    
    if (xs.isEmpty) { 
      Nil 
    }
    else {
      insert( xs.head, isort( xs.tail ) )      
    } 
  }
  
  println( isort( 5 :: 2 :: 3 :: 1 :: 4 :: 0 :: Nil ) )
}

/**
 * Uses cases with lists
 */
object ListsWithCases extends Application {
  
  def isort( xs: List[Int] ): List[Int] = {
    def insert( value: Int, sorted: List[Int] ): List[Int] = {
      sorted match {
        case Nil => List( value ) 
        case sortedHead :: sortedTail => {
          if ( value <= sortedHead ) 
            value :: sorted 
          else 
            sortedHead:: insert( value, sortedTail )
        }
      }
    }
    
    xs match {
      case Nil => List()
      case head :: tail => insert( head, isort(tail) );
    }    
  }
  
  println( isort( 5 :: 2 :: 3 :: 1 :: 4 :: 0 :: Nil ) )  
}


object Folding extends Application
{
  val list = List(1,2,3,4,5)
  
  /**
   * Adds a list of integers from left to right
   * 
   * The parameter "0" represents an initial value from
   * which the left-most list element is subtracted
   */
  val leftFoldSum = list.foldLeft(0) { (folded,next) => 
    println( folded + " + " + next ) 
    folded + next 
  }
  
  println( "---------------\n" + leftFoldSum + "\n" );
 
  /**
   * Subtracts a list of integers from left to right
   * 
   * The parameter "0" represents an initial operand
   * from which the right-most list element is subracted
   */
  val rightFoldSum = list.foldRight(0) { (next,folded) => 
    println( folded + " - " + next ); 
    folded - next 
  }
  println( "---------------\n" + rightFoldSum );
}

object FoldingSyntaticSugar extends Application
{
  val list = List(1,2,3,4,5)  
  
  /**
   * (0 /: list) == list.foldLeft( 0 )
   */
  val leftFoldSum = (0 /: list) { (folded,next) => 
    println( folded + " + " + next ) 
    folded + next 
  }
  
  println( "---------------\n" + leftFoldSum + "\n" );
 
  /**
   * (list :\ 0) == list.foldRight( 0 )
   */
  val rightFoldSum = (list :\ 0) { (next,folded) => 
    println( folded + " - " + next ); 
    folded - next 
  }
  println( "---------------\n" + rightFoldSum );  
}

object MapFunctionsUsingFold extends Application
{
  /**
   * An implementation of map, using foldRight
   */
  def mapFun[A,B]( xs: List[A], f: A => B ): List[B] = {
    ( xs :\ List[B]() ){ (next,folded) => f( next ) :: folded }
  }
  
  def lengthFun[A]( xs: List[A] ): Int = {
    ( 0 /: xs ) { (folded,next) =>
      folded + 1
    }
  }
  
  val list = List(1,2,3)
  val sum = ( 0 /: list){_ + _}
  
  println( "List: " + list )
  println( "Sum:  " + sum )
  println( "Length: " + lengthFun( list ) )
  println( "Square each element: " + mapFun[Int,Int]( list, x => x*x  ))
}