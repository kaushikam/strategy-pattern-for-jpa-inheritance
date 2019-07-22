package com.kaushikam.strategypattern.domain.model.payment.order.transaction.message

import com.kaushikam.strategypattern.domain.model.payment.order.PaymentOrder
import javax.persistence.*

@Entity
class TransactionMessage (
    @Id
    val id: Long = 0L,

    val amount: Long,

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    var paymentOrder: PaymentOrder? = null
)