import akka.actor.Actor

/**
  * Created by bclough on 8/10/17.
  */
class BankAccount extends Actor {

  var balance = 0.0
  var transactions:List[Transaction] = Nil

  class Transaction(amount: Double, date: BigInt)

  override def receive: Receive = {
    case "Deposit" => println("deposited")
    case "Withdrawal" => println("withdrawn")
    case none => println("unknown service request")
  }

}
