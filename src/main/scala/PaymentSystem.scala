package com.knoldus

import scala.collection.mutable

// Define PaymentSystem class
class PaymentSystem {
  private val paymentMethods = mutable.Map.empty[String, PaymentMethod]

  def addPaymentMethod(name: String, paymentMethod: PaymentMethod): Boolean = {
    if (paymentMethods.contains(name)) {
      throw new IllegalArgumentException(s"Payment method '$name' already exists")
    }
    paymentMethods += (name -> paymentMethod)
    true
  }

  def processPayment(name: String, amount: Double): Boolean = {
    paymentMethods.get(name) match {
      case Some(paymentMethod) =>
        try {
          paymentMethod.processPayment(amount)
        } catch {
          case e: Exception =>
            throw new RuntimeException(s"Error processing payment using '$name' payment method: ${e.getMessage}", e)
        }
      case None =>
        throw new IllegalArgumentException(s"Payment method '$name' does not exist")
    }
  }

  def listPaymentMethods(): List[String] = {
    paymentMethods.keys.toList
  }
}


// Define PaymentMethod trait
trait PaymentMethod {
  def processPayment(amount: Double): Boolean
}

// Define case class Credit Card
case class CreditCard(cardNumber: String, cvv: Int, expiryDate: String) extends PaymentMethod {
  override def processPayment(amount: Double): Boolean = true
}

// Define case class PayPal
case class PayPal(email: String, password: String) extends PaymentMethod {
  override def processPayment(amount: Double): Boolean = true
}

// Define case class BankTransfer
case class BankTransfer(accountNumber: String, routingNumber: String) extends PaymentMethod {
  override def processPayment(amount: Double): Boolean = true
}

