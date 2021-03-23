package demo3

import scala.collection.mutable.ArrayBuffer

object CollectionHW extends App {

  object Solution {

    // https://leetcode.com/problems/running-sum-of-1d-array/
    //Runtime: 440 ms, faster than 93.96% of Scala online submissions for Running Sum of 1d Array.
    //Memory Usage: 51.5 MB, less than 35.16% of Scala online submissions for Running Sum of 1d Array.
    def runningSum(nums: Array[Int]): Array[Int] = {
      var array: Array[Int] = new Array(nums.length)
      var sum = 0
      for (i <- 0 to nums.length - 1) {
        sum = sum + nums(i)
        array(i) = sum
      }
      array
    }

    def runningSum2(nums: Array[Int]): Array[Int] = {
      var sum = 0
      for (n <- nums) yield {
        sum = sum + n; sum
      }
    }

    def runningSum3(nums: Array[Int]): Array[Int] = {
      return nums.scan(0)(_ + _).drop(1)
    }

    // https://leetcode.com/problems/shuffle-the-array
    //Runtime: 484 ms, faster than 88.31% of Scala online submissions for Shuffle the Array.
    //Memory Usage: 51.6 MB, less than 94.81% of Scala online submissions for Shuffle the Array.
    def shuffle(nums: Array[Int], n: Int): Array[Int] = {
      var array: Array[Int] = new Array(nums.length)

      var index = 0;
      var j = n;
      for (item <- Array.copyOf(nums, n)) {
        array(index) = item;
        array(index + 1) = nums(j);
        j = j + 1
        index = index + 2;
      }
      array
    }

    def shuffle2(nums: Array[Int], n: Int): Array[Int] = {
      var arr = scala.collection.mutable.ArrayBuffer[Int]()

      for (i <- 0 until n) {
        arr += nums(i)
        if ((i + n) < nums.size) arr += nums(i + n)
      }
      arr.toArray
    }

    def shuffle3(nums: Array[Int], n: Int): Array[Int] = {
      (0 to 2 * n - 1).toArray.map {
        idx =>
          idx % 2 match {
            case 0 => nums(idx / 2)
            case 1 => nums(idx / 2 + n)
          }
      }
    }

    def shuffle4(nums: Array[Int], n: Int): Array[Int] = {
      (for (i <- Range(0, 2 * n)) yield nums(i / 2 + n * (i % 2))).toArray
    }

    // https://leetcode.com/problems/richest-customer-wealth
    //Runtime: 476 ms, faster than 94.78% of Scala online submissions for Richest Customer Wealth.
    //Memory Usage: 51.4 MB, less than 61.74% of Scala online submissions for Richest Customer Wealth.
    def maximumWealth(accounts: Array[Array[Int]]): Int = {
      accounts.map(customer => customer.reduce(_ + _)).max(Ordering.Int)
    }

    def maximumWealth2(accounts: Array[Array[Int]]): Int = {
      accounts.map(x => x.sum).max(Ordering.Int)
    }

    def maximumWealth3(accounts: Array[Array[Int]]): Int = {
      val wealthList = for {
        account <- accounts
      } yield {
        account.sum
      }
      wealthList.max(Ordering.Int)
    }


    // https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
    //Runtime: 468 ms, faster than 72.73% of Scala online submissions for Kids With the Greatest Number of Candies.
    //Memory Usage: 51.5 MB, less than 26.14% of Scala online submissions for Kids With the Greatest Number of Candies.
    def kidsWithCandies(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
      candies.map(_ + extraCandies >= candies.max(Ordering.Int))
    }

    def kidsWithCandies2(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
      val max = candies.max(Ordering.Int) - extraCandies
      for (x <- candies) yield x >= max
    }

    def kidsWithCandies3(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
      val maxCandy = candies.max(Ordering.Int)
      candies.foldLeft(Array[Boolean]())((acc, cur) => acc :+ ((cur + extraCandies) >= maxCandy))
    }

    // https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points
    //Runtime: 1136 ms, faster than 57.14% of Scala online submissions for Widest Vertical Area Between Two Points Containing No Points.
    //Memory Usage: 120.7 MB, less than 65.31% of Scala online submissions for Widest Vertical Area Between Two Points Containing No Points.
    def maxWidthOfVerticalArea(points: Array[Array[Int]]): Int = {
      points.map(arr => arr(0))
        .sortWith(_ < _)
        .foldLeft((0, Option.empty[Int])) { (tup, value) =>
          (math.max(tup._1, tup._2.map(x => value - x).getOrElse(0)),
            Option(value)
          )
        }._1
    }


    // https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
    //Runtime: 452 ms, faster than 40.43% of Scala online submissions for Maximum Nesting Depth of the Parentheses.
    //Memory Usage: 50.8 MB, less than 29.79% of Scala online submissions for Maximum Nesting Depth of the Parentheses.
    def maxDepth(s: String): Int = {
      s.scanLeft(0)((a, b) => b match {
        case '(' => a + 1
        case ')' => a - 1
        case _ => a
      }).max
    }

    // https://leetcode.com/problems/split-a-string-in-balanced-strings
    //Runtime: 400 ms, faster than 97.73% of Scala online submissions for Split a String in Balanced Strings.
    //Memory Usage: 50.4 MB, less than 70.45% of Scala online submissions for Split a String in Balanced Strings.
    def balancedStringSplit(s: String): Int = {
      var count: Int = 0
      var countElem: Int = 0
      var previousSymb: Char = s.charAt(0)

      for (symb <- s.toCharArray) {
        if (countElem == 0 && previousSymb != symb) previousSymb = symb
        if (symb == previousSymb) countElem = countElem + 1
        else {
          countElem = countElem - 1
          if (countElem == 0) {
            count = count + 1
          }
        }
      }
      count
    }
  }

  // Implement scanLeft (not using scans ofc)
  def scanLeft[T](zero: T)(list: List[T])(f: (T, T) => T): List[T] = {
    var acc = zero;
    list.map(item => {
      acc = f(acc, item)
      acc
    })
  }

  var r = scanLeft[Int](0)(List(1, 2, 3, 4, 5))(_ + _)
  r.foreach(println)

  // https://twitter.com/allenholub/status/1357115515672555520/photo/1
  // pass the interview
  def count(s: String): Array[(Char, Int)] = {
    var count = 0;
    var previousSymb = s.charAt(0)
    var result = ArrayBuffer[(Char, Int)]()
    for (symb <- s.toCharArray) {
      if (symb == previousSymb) count = count + 1
      else {
        result += (Tuple2(previousSymb, count))
        previousSymb = symb
        count = 1
      }
    }
    result += (Tuple2(previousSymb, count))
    result.toArray
  }

  val s = "aaaabbbcca"
  val res = count(s)
  res.foreach(x => print(s"$x "))
}

