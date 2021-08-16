package com.transactions.bankapp.resource

import com.transactions.bankapp.domain.Account
import com.transactions.bankapp.dto.AccountDataDto
import com.transactions.bankapp.service.AccountService
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AccountResource(private val accountService: AccountService) {

    @Path("/{userId}")
    @GET
    fun getAccount(@PathParam("userId") userId: String): Account {
        return accountService.getAccount(userId)
    }

    @Path("/{userId}")
    @PUT
    fun updateAccount(@PathParam("userId") userId: String, @RequestBody accountData: AccountDataDto) {
        accountService.updateAccount(userId, accountData)
    }

    @POST
    fun createAccount(@RequestBody accountData: AccountDataDto) {
        accountService.saveAccount(accountData)
    }
}