package com.kaushikam.strategypattern.domain.model.payment.gateway.impl

import com.kaushikam.strategypattern.domain.model.payment.gateway.PaymentGatewayProperties
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class BillDeskProperties (
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	val id: Long,

	val commonString: String,

	val createdOn: LocalDateTime
): PaymentGatewayProperties {
	override fun toString(): String {
		return "BillDeskProperties(id=$id, commonString='$commonString', createdOn=$createdOn)"
	}
}