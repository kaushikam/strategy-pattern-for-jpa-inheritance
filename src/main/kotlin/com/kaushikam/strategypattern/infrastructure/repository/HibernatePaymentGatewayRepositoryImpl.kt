package com.kaushikam.strategypattern.infrastructure.repository

import com.kaushikam.strategypattern.domain.model.payment.gateway.*
import com.kaushikam.strategypattern.domain.model.payment.gateway.impl.BillDeskProperties
import com.kaushikam.strategypattern.domain.model.payment.gateway.impl.SouthIndianBankProperties
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class HibernatePaymentGatewayRepositoryImpl (
	@PersistenceContext
	private val entityManager: EntityManager
): PaymentGatewayRepository {

	override fun findByPaymentGatewayName(name: PaymentGatewayName): PaymentGateway {
		val query = entityManager.createQuery(
			"SELECT p FROM PaymentGateway p WHERE p.name = :name",
			PaymentGateway::class.java
		)
		query.setParameter("name", name)
		return query.singleResult ?: throw PaymentGatewayNotFoundException(name)
	}

	override fun findPaymentGatewayProperties(name: PaymentGatewayName): PaymentGatewayProperties {
		val query = when(name) {
			PaymentGatewayName.BILLDESK -> {
				entityManager.createQuery(
					"SELECT b FROM BillDeskProperties b ORDER BY b.createdOn DESC",
					BillDeskProperties::class.java
				)
			}
			PaymentGatewayName.SOUTH_INDIAN_BANK -> {
				entityManager.createQuery(
					"SELECT s FROM SouthIndianBankProperties s ORDER BY s.createdOn DESC",
					SouthIndianBankProperties::class.java
				)
			}
		}
		return query.setMaxResults(1).resultList[0] ?: throw PaymentGatewayPropertiesNotFoundException(name)
	}

}