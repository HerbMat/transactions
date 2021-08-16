package com.transactions.bankapp.resource

import com.transactions.bankapp.domain.Account
import com.transactions.bankapp.dto.AccountDataDto
import com.transactions.bankapp.service.AccountService
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import java.math.BigDecimal
import java.time.temporal.TemporalAmount
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PaymentResource(private val accountService: AccountService) {

    @PUT
    @Path("/{userId}/withdraw")
    fun withdraw(@PathParam("userId") userId: String, @QueryParam("amount") amount: BigDecimal) {
        accountService.withdrawMoney(userId, amount)
    }

    @PUT
    @Path("/{userId}/deposit")
    fun deposit(@PathParam("userId") userId: String, @QueryParam("amount") amount: BigDecimal) {
        accountService.depositMoney(userId, amount)
    }
}