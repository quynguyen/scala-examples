package ca.whileloop.scala.projecteuler


object Euler001 extends App {

    def sumMultiples(threshold: Int, number: Int): Int = {
        def iter(curr: Int, sum: Int): Int = {
            if (curr >= threshold) sum
            else iter(curr + number, sum + curr)  
        }
        iter(0,0)
    }
 
    val s = sumMultiples(1000, 3) + sumMultiples(1000,5)
    print( s )
}
