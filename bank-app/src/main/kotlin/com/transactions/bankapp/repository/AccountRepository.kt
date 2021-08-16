package com.transactions.bankapp.repository

import com.transactions.bankapp.domain.Account
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class AccountRepository: PanacheMongoRepository<Account> {
    fun findByUserId(userId: String): Account? {
        return find("userId", userId).firstResult()
    }
}