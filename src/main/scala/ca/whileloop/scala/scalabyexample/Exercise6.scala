package ca.whileloop.scala.scalabyexample

object Exercise6 extends App {

  trait IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def union(other: IntSet): IntSet
    def intersect(other: IntSet): IntSet
    def isEmpty(): Boolean
    def excl(x: Int): IntSet
  }

  class EmptySet extends IntSet {
    def isEmpty: Boolean = true
    def contains(x: Int): Boolean = false
    def incl(x: Int): IntSet = new NonEmptySet(x, new EmptySet, new EmptySet)
    def union(other: IntSet) = other
    def intersect(other: IntSet) = this
    def excl(x: Int): IntSet = this
  }

  class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    def value = elem;
    def leftSide = left;
    def rightSide = right;
    
    def isEmpty = false
    
    def contains(x: Int): Boolean = {
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true
    }
    
    def incl(x: Int): IntSet = {
      if (x < elem) new NonEmptySet(elem, left incl x, right)
      else if (x > elem) new NonEmptySet(elem, left, right incl x)
      else this
    }
    
    def union(other: IntSet): IntSet = {
      val l = left.union(other)
      val r = right.union(other)
      val u = left.union(right)
      u.incl(elem)
    }
    
    def intersect(other: IntSet): IntSet = {
      val l = left intersect other
      val r = right intersect other
      val u = l union r
      if (other contains elem) u incl elem else u
    }
    
    def excl(x: Int): IntSet = {
      if (x > elem) new NonEmptySet(elem, left, right excl x)
      else if (x < elem) new NonEmptySet(elem, left excl x, right)
      else left union right
    }
  }
}