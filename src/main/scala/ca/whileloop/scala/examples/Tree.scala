package ca.whileloop.scala.examples

abstract class Tree
case class Sum(l: Tree, r: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree

object Evaluator
{
  type Environment = String => Int
  
  def eval( t: Tree, env: Environment ): Int = t match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Var(n) => env(n)
    case Const(v) => v
  }
  
  def derive(t: Tree, v: String): Tree = t match {
    case Sum(l, r) => Sum(derive(l, v), derive(r, v))
    case Var(n) if (v == n) => Const(1)
    case _ => Const(0)
  }
}

object Main
{  
  type Environment = String => Int  
  
  def main( args : Array[String] ) {
    
    val exp1: Tree = Sum( Const(1), Const(2) )
    
    println( "Expression: " + exp1 )
    println( "Evaluation: " + Evaluator.eval( exp1, null ) )
    
    val exp2: Tree = Sum( Sum( Var("x"), Var("x") ), Sum( Const(7), Var("y") ) )
    val env2: Environment = {
      case "x" => 5
      case "y" => 7
    }
    
    println()
    
    println( "Expression: " + exp2 )
    println( "Evaluation with x=5, y=7: " + Evaluator.eval( exp2, env2 ))
    
    println()
    
    println( "Derivative: " + Evaluator.derive(exp2, "x"))
  }
}