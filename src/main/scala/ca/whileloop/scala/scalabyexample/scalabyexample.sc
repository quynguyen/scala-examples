object scalabyexample {

  def sum(f: Int => Double)(a: Int, b: Int): Double = {
    def iter(a: Int, acc: Double): Double = {
      if (a > b)
        acc
      else
        iter(a + 1, acc + f(a))
    }
    iter(a, 0)
  }                                               //> sum: (f: Int => Double)(a: Int, b: Int)Double

  sum(x => x * x)(1, 2)                           //> res0: Double = 5.0

  def product(f: Int => Double)(a: Int, b: Int): Double = {
    def iter(a: Int, acc: Double): Double = {
      if (a > b)
        acc
      else
        iter(a + 1, acc * f(a))
    }
    iter(a, 1)
  }                                               //> product: (f: Int => Double)(a: Int, b: Int)Double
  
  product(x => x)(1, 4)                           //> res1: Double = 24.0
  
  def factorial(n: Int): Double = {
  	product(x => x)(1, n)
  }                                               //> factorial: (n: Int)Double
  
  factorial(3)                                    //> res2: Double = 6.0
  
  abstract class Operator
  {
  	def identity: Double
  	def apply(a: Double, b: Double): Double
  }
  case object Add extends Operator
  {
  	def identity: Double = 0
  	def apply(a: Double, b: Double): Double = a + b
  }
  case object Multiply extends Operator
  {
  	def identity: Double = 1
  	def apply(a: Double, b: Double): Double = a * b
  }
  
  object OperateOnRange {
  	def apply(operator: Operator)(a: Int, b: Int): Double = {
  		def iter(a: Int, acc: Double): Double = {
  			if (a > b)
  				acc
  			else
  				iter(a + 1, operator(acc, a))
  		}
  		iter(a, operator.identity)
  	}
  }
  
  OperateOnRange(Add)(1,2)                        //> res3: Double = 3.0
  
  
  def fixedPoint(f: Double => Double)(initialGuess: Double): Double = {
  	def iter(guess: Double): Double = {
  		def isCloseEnough(next: Double): Boolean = {
  			Math.abs((next - guess)/guess) < 0.0001
  		}
  		val next = f(guess);
  		println(next)
  		if (isCloseEnough(next))
  			next
  		else
  			iter(next)
  	}
  	
  	iter(initialGuess)
  }                                               //> fixedPoint: (f: Double => Double)(initialGuess: Double)Double
  
  def sqrt(x: Double) = fixedPoint(y => (y + x/y) / 2)(1.0)
                                                  //> sqrt: (x: Double)Double
  sqrt(4)                                         //> 2.5
                                                  //| 2.05
                                                  //| 2.000609756097561
                                                  //| 2.0000000929222947
                                                  //| 2.000000000000002
                                                  //| res4: Double = 2.000000000000002
  
  
  
	def averageDamp(f: Double => Double)(x: Double) = (x + f(x))/2
                                                  //> averageDamp: (f: Double => Double)(x: Double)Double
	
	def sqrt2(x: Double) = fixedPoint(averageDamp(y => x / y))(1.0)
                                                  //> sqrt2: (x: Double)Double
	
	
	def cbrt(x: Double) = fixedPoint(averageDamp(y => x / (y * y)))(1.0)
                                                  //> cbrt: (x: Double)Double
	
	cbrt(16)                                  //> 8.5
                                                  //| 4.360726643598616
                                                  //| 2.6010630779468844
                                                  //| 2.4829963306881875
                                                  //| 2.539089185721205
                                                  //| 2.5104368521931093
                                                  //| 2.524597644149205
                                                  //| 2.5174777560877635
                                                  //| 2.5210276034702774
                                                  //| 2.519250184036327
                                                  //| 2.52013826629502
                                                  //| 2.5196940687432705
                                                  //| 2.519916128358389
                                                  //| res5: Double = 2.519916128358389
	
	trait IntSet {
		def incl(x: Int): IntSet
		def contains(x: Int): Boolean
	}
	
	class EmptySet extends IntSet {
def contains(x: Int): Boolean = false
def incl(x: Int): IntSet = new NonEmptySet(x, new EmptySet, new EmptySet)
}

class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
def contains(x: Int): Boolean =
if (x < elem) left contains x
else if (x > elem) right contains x
else true
def incl(x: Int): IntSet =
if (x < elem) new NonEmptySet(elem, left incl x, right)
else if (x > elem) new NonEmptySet(elem, left, right incl x)
else this
}  
}