package com.kaushikam.strategypattern

import com.kaushikam.strategypattern.domain.model.payment.gateway.PaymentGatewayName
import com.kaushikam.strategypattern.domain.model.payment.gateway.PaymentGatewayRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StrategyPatternApplication (
	@Autowired
	private val paymentGatewayRepository: PaymentGatewayRepository
): CommandLineRunner {

	private val logger = LoggerFactory.getLogger(javaClass)

	override fun run(vararg args: String?) {
		val paymentGateway = paymentGatewayRepository.findByPaymentGatewayName(PaymentGatewayName.BILLDESK)
		logger.info("payment gateway: $paymentGateway")
		logger.info("Properties: ${paymentGateway.paymentGatewayProperties}")
	}
}

fun main(args: Array<String>) {
	runApplication<StrategyPatternApplication>(*args)
}
