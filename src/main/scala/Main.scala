/**
  * Created by bclough on 8/10/17.
  */

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {

  // Create the AKKA actor system
  val system = ActorSystem("BankingSystem")
  val accountActor = system.actorOf(Props[BankAccount], name = "accountActor")
  val timeout = Duration.create(5, "seconds")

  var future = accountActor.ask(Deposit(5000))(timeout)
  var res = Await.result(future, timeout).asInstanceOf[String]

  future = accountActor.ask(Withdraw(5000))(timeout)
  res = Await.result(future, timeout).asInstanceOf[String]

  future = accountActor.ask(BalanceInquiry)(timeout)
  res = Await.result(future, timeout).asInstanceOf[String]

  //future = accountActor ? ListTransactions (timeout)
}

