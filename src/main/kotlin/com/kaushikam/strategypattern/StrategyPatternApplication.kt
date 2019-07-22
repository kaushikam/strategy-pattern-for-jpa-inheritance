package com.kaushikam.strategypattern

import com.kaushikam.strategypattern.domain.model.payment.gateway.PaymentGatewayName
import com.kaushikam.strategypattern.domain.model.payment.gateway.PaymentGatewayRepository
import com.kaushikam.strategypattern.domain.model.payment.order.PaymentOrder
import com.kaushikam.strategypattern.domain.model.payment.order.transaction.message.TransactionMessage
import com.kaushikam.strategypattern.domain.model.payment.order.transaction.message.impl.BillDeskTransactionMessage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootApplication
class StrategyPatternApplication (
	@Autowired
	private val paymentGatewayRepository: PaymentGatewayRepository,

	@Autowired
	private val entityManager: EntityManager
): CommandLineRunner {

	private val logger = LoggerFactory.getLogger(javaClass)

	@Transactional
	override fun run(vararg args: String?) {
		val paymentGateway = paymentGatewayRepository.findByPaymentGatewayName(PaymentGatewayName.BILLDESK)
		logger.info("payment gateway: $paymentGateway")
		logger.info("Properties: ${paymentGateway.paymentGatewayProperties}")

		val paymentOrder = PaymentOrder(amount = 100L, paymentGateway = paymentGateway)
		entityManager.persist(paymentOrder)

		val transactionMessage = TransactionMessage(amount = 100L)
		paymentOrder.transactionMessage = transactionMessage

		val billDeskMessage = BillDeskTransactionMessage(merchantId = "kwaut", paymentOrder = paymentOrder, transactionNumber = "testing")

		entityManager.persist(paymentOrder)
		entityManager.persist(billDeskMessage)
	}
}

fun main(args: Array<String>) {
	runApplication<StrategyPatternApplication>(*args)
}
