package com.kaushikam.strategypattern.domain.model.payment.gateway

import java.lang.RuntimeException

interface PaymentGatewayRepository {
	@Throws(PaymentGatewayNotFoundException::class)
	fun findByPaymentGatewayName(name: PaymentGatewayName): PaymentGateway

	fun findPaymentGatewayProperties(name: PaymentGatewayName): PaymentGatewayProperties
}

class PaymentGatewayNotFoundException(name: PaymentGatewayName): RuntimeException("There is no such payment gateway like ${name.name} exists!")