package com.kaushikam.strategypattern.infrastructure.listener

import com.kaushikam.strategypattern.domain.model.payment.gateway.PaymentGateway
import com.kaushikam.strategypattern.domain.model.payment.gateway.PaymentGatewayRepository
import com.kaushikam.strategypattern.shared.SpringApplicationContext
import javax.persistence.PostLoad

class PaymentGatewayListener {

	@PostLoad
	fun loadPaymentGatewayProperties(paymentGateway: PaymentGateway) {
		val repository = SpringApplicationContext.getObjectOfClass(PaymentGatewayRepository::class.java)
		paymentGateway.paymentGatewayProperties = repository.findPaymentGatewayProperties(paymentGateway.name)
	}
}