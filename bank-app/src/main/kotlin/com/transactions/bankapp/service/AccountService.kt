package com.transactions.bankapp.service

import com.transactions.bankapp.domain.Account
import com.transactions.bankapp.dto.AccountDataDto
import com.transactions.bankapp.repository.AccountRepository
import org.bson.types.ObjectId
import java.math.BigDecimal
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

@ApplicationScoped
class AccountService(private val accountRepository: AccountRepository) {
    fun getAccount(userId: String): Account {
        return accountRepository.findByUserId(userId) ?: throw WebApplicationException("Not Found", Response.Status.NOT_FOUND)
    }

    fun saveAccount(accountData: AccountDataDto) {
        accountRepository.persist(Account(ObjectId.get(), accountData.userId, accountData.balance))
    }

    fun updateAccount(userId: String, accountData: AccountDataDto) {
        val existingAccount = accountRepository.findByUserId(userId)

        existingAccount?.let { accountRepository.persistOrUpdate(it.copy(userId = accountData.userId, balance = accountData.balance)) }
            ?: throw WebApplicationException("Not Found", Response.Status.NOT_FOUND)
    }

    fun withdrawMoney(userId: String, amount: BigDecimal) {
        accountRepository.findByUserId(userId)?.let {
            if (it.balance < amount) {
                throw WebApplicationException("Amount Exceed balance", Response.Status.BAD_REQUEST)
            }
            accountRepository.persistOrUpdate(it.copy(balance = it.balance.minus(amount)))
        } ?: throw WebApplicationException("Not Found", Response.Status.NOT_FOUND)
    }

    fun depositMoney(userId: String, amount: BigDecimal) {
        accountRepository.findByUserId(userId)?.let {
            accountRepository.persistOrUpdate(it.copy(balance = it.balance.plus(amount)))
        } ?: throw WebApplicationException("Not Found", Response.Status.NOT_FOUND)
    }
}