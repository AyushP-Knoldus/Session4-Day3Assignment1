package com.knoldus

object Driver {
  def main(args: Array[String]): Unit = {
    val paymentSystem = new PaymentSystem

    // Adding payment methods
    val creditCard = CreditCard("1234567890123456", 123, "12/23")
    val payPal = PayPal("johndoe@gmail.com", "password")

    paymentSystem.addPaymentMethod("Credit Card", creditCard)
    paymentSystem.addPaymentMethod("PayPal", payPal)

    // Process payment
    try {
      val paymentSuccessful = paymentSystem.processPayment("Credit Card", 100.0)
      println(s"Payment successful: $paymentSuccessful")
    } catch {
      case e: Exception =>
        println(s"Error processing payment: ${e.getMessage}")
    }

    // List payment methods
    val paymentMethodsList = paymentSystem.listPaymentMethods()
    println(s"Available payment methods: $paymentMethodsList")

  }
}