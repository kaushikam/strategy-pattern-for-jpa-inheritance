package com.kaushikam.strategypattern.domain.model.payment.order.transaction.message.impl

import com.kaushikam.strategypattern.domain.model.payment.order.PaymentOrder
import com.kaushikam.strategypattern.domain.model.payment.order.transaction.message.TransactionMessage
import javax.persistence.*

@Entity
class BillDeskTransactionMessage (
    @Id
    val id: Long = 0,

    val merchantId: String,

    val transactionNumber: String,

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    val paymentOrder: PaymentOrder
)