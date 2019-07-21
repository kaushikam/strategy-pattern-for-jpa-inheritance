package com.kaushikam.strategypattern.domain.model.payment.gateway

import java.lang.RuntimeException

interface PaymentGatewayProperties

class PaymentGatewayPropertiesNotFoundException(name: PaymentGatewayName): RuntimeException("There is no properties exist for payment gateway with name ${name.name}!")