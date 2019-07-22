package com.kaushikam.strategypattern.domain.model.payment.order

import com.kaushikam.strategypattern.domain.model.payment.gateway.PaymentGateway
import com.kaushikam.strategypattern.domain.model.payment.order.transaction.message.TransactionMessage
import javax.persistence.*

@Entity
class PaymentOrder (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,

    val amount: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    val paymentGateway: PaymentGateway
) {
    @OneToOne(mappedBy = "paymentOrder", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var transactionMessage: TransactionMessage? = null
        set(value) {
            if (value == null) {
                if (field != null) {
                    field?.paymentOrder = null
                }
            } else {
                value.paymentOrder = this
            }
            field = value
        }
}