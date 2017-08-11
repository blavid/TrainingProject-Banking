/**
  * Created by bclough on 8/10/17.
  */

import akka.actor._
import akka.pattern.ask
import scala.concurrent.duration.Duration

object Main extends App {

  // Create the AKKA actor system
  val system = ActorSystem("BankingSystem")
  val accountActor = system.actorOf(Props[BankAccount], name = "accountActor")
  val timeout = Duration.create(1, "seconds")

  // Create some transactions
  accountActor.ask(Deposit(5000))(timeout)
  accountActor.ask(Withdraw(3000))(timeout)
  accountActor.ask(Withdraw(300))(timeout)
  accountActor.ask(BalanceInquiry)(timeout)
  accountActor.ask(ListTransactions) (timeout)
}

