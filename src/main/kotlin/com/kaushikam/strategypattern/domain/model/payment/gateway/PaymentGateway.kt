package com.kaushikam.strategypattern.domain.model.payment.gateway

import com.kaushikam.strategypattern.infrastructure.listener.PaymentGatewayListener
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.persistence.*

@Entity
@EntityListeners(PaymentGatewayListener::class)
class PaymentGateway (
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	val id: Long,

	val name: PaymentGatewayName
) {
	@Transient
	lateinit var paymentGatewayProperties: PaymentGatewayProperties

	override fun toString(): String {
		return "PaymentGateway(id=$id, name=$name)"
	}


}

enum class PaymentGatewayName {
	BILLDESK,
	SOUTH_INDIAN_BANK;

	companion object {
		@JvmStatic
		fun parse(name: String?): PaymentGatewayName {
			values().forEach {
				if (it.name.equals(name, true))
					return it
			}

			throw InvalidPaymentGatewayNameException(name?:"null")
		}
	}
}

class InvalidPaymentGatewayNameException(name: String): RuntimeException("There is no such name $name")

@Converter(autoApply = true)
class PaymentGatewayNameConverter: AttributeConverter<PaymentGatewayName, String> {
	override fun convertToDatabaseColumn(attribute: PaymentGatewayName?): String {
		return (attribute ?: throw IllegalArgumentException("Null value is not allowed")).name
	}

	override fun convertToEntityAttribute(dbData: String?): PaymentGatewayName {
		return PaymentGatewayName.parse(
			(dbData ?: throw IllegalArgumentException("There is no such Payment gateway with name $dbData"))
		)
	}
}