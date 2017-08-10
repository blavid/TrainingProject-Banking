import java.util.{Calendar, Date}
import java.text.SimpleDateFormat

import akka.actor.Actor

/**
  * Created by bclough on 8/10/17.
  */

// Message case classes
case class Deposit (amount: Double)
case class Withdraw (amount: Double)
case class BalanceInquiry ()
case class ListTransactions()

class BankAccount extends Actor {

  var balance = 0.0
  var transactions = List[Transaction]()

  class Transaction(var amount: Double, var date: Date)

  def deposit (amount: Double) = {
    balance += amount
    val transaction = new Transaction(amount, Calendar.getInstance().getTime())
    transactions = transaction::transactions
  }

  def withdraw (amount: Double) = {
    balance -= amount
    val transaction = new Transaction(-amount, Calendar.getInstance().getTime())
    transactions = transaction::transactions
  }

  def listTransactions:String = {
    var returnString = "      Date           Type       Amount        Balance\n"
    returnString +=    "----------------    ------    ----------    -----------\n"
    val dateFormat = new SimpleDateFormat("hh:mm MM-dd-yyyy")
    var runningBalance = 0.0

    for (transaction <- transactions){
      val date = dateFormat.format(transaction.date)
      val tType = if(transaction.amount > 0) "D" else "W"
      runningBalance += transaction.amount
      returnString += s"$date      $tType         " + transaction.amount + s"       $runningBalance\n"

    }

    returnString
  }

  override def receive: Receive = {

    case Deposit(amount) => {
      deposit(amount)
      println(s"deposited $amount")
      sender ! 1
    }

    case Withdraw(amount) => {
      withdraw(amount)
      println(s"withdrawn $amount")
      sender ! 2
    }

    case BalanceInquiry =>{
      println(s"Balance: $balance")
      sender ! 3
    }

    case ListTransactions =>{
     println(listTransactions)
    }

    case _ => println("unknown service request")

  }

}
