package com.transactions.bankapp.config.listener

import com.transactions.bankapp.domain.Account
import com.transactions.bankapp.repository.AccountRepository
import io.quarkus.runtime.StartupEvent
import org.bson.types.ObjectId
import java.math.BigDecimal
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes

@ApplicationScoped
class LoadEntitiesBean(private val accountRepository: AccountRepository) {
    fun onStart(@Observes ev: StartupEvent) {
        val account1 = Account(ObjectId.get(),"1", BigDecimal("10000"))
        val account2 = Account(ObjectId.get(),"2", BigDecimal("5000"))
        val account3 = Account(ObjectId.get(),"3", BigDecimal("2000"))
        accountRepository.persist(account1)
        accountRepository.persist(account2)
        accountRepository.persist(account3)
    }
}