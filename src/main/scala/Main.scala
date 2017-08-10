/**
  * Created by bclough on 8/10/17.
  */

import akka.actor._


object Main extends App {

  // Create the AKKA actor system
  val system = ActorSystem("BankingSystem")
  val accountActor = system.actorOf(Props[BankAccount], name = "accountActor")

  accountActor ! "Deposit"
  accountActor ! "Withdrawal"
}

