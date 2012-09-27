package ca.whileloop.scala.scalabyexample

object Exercise7 extends App {

  abstract class IntTree {
    def contains(t: IntTree, v: Int): Boolean = t match {
      case EmptyTree => false
      case Node(e, _, _) if (v == e) => true
      case Node(e, l, _) if (v < e)  => contains(l, v)
      case Node(e, _, r)			 => contains(r, v)
    }

    def insert(t: IntTree, v: Int): IntTree = t match {
      case EmptyTree => Node(v, EmptyTree, EmptyTree)
      case Node(e, l, r) => {
        if (v > e) Node(e, l, insert(r, v))
        else if (v < e) Node(e, insert(l, v), r)
        else t
      }
    }
  }

  case object EmptyTree extends IntTree

  case class Node(elem: Int, left: IntTree, right: IntTree) extends IntTree
}