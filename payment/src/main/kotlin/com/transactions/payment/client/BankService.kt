package com.transactions.payment.client

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import java.math.BigDecimal
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

@Path("/account")
@RegisterRestClient(configKey = "bank-api")
interface BankService {

    @PUT
    @Path("/{userId}/withdraw")
    fun withdrawMoney(@PathParam("userId") userId: String, @QueryParam("amount") amount: BigDecimal)

    @PUT
    @Path("/{userId}/deposit")
    fun depositMoney(@PathParam("userId") userId: String, @QueryParam("amount") amount: BigDecimal)
}