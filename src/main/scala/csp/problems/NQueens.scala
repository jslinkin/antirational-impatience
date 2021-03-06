package csp.problems

import csp.{Domain, Problem, Solution}

class NQueens(queens: Int) extends Problem[Int] {
  def standardDomain: Domain[Int] = (0 until queens).toStream

  def variableCount: Int = queens

  def initialDomains: List[Domain[Int]] = List.fill(queens)(standardDomain)

  def isSolution(solution: Solution[Int]): Boolean = solution.length == queens && isCompatible(solution)

  def isCompatible(queenIndexes: Solution[Int]): Boolean = queenIndexes match {
    case Nil => true
    case h :: t => Set(4)
      t.zipWithIndex.forall(t => h != t._1 && math.abs(h - t._1) - 1 != t._2) &&
        isCompatible(t)
  }

  def removeConflicts(domains: List[Domain[Int]], el: Int): List[Domain[Int]] =
    domains.zipWithIndex.map{case (d, i) => d.filterNot(x => x == el || math.abs(x - el) == i)}
}
