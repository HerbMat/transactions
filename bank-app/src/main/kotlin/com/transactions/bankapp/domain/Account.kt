package com.transactions.bankapp.domain

import io.quarkus.mongodb.panache.kotlin.PanacheMongoEntityBase
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import java.math.BigDecimal

data class Account @BsonCreator constructor (
    @BsonId var id: ObjectId,
    @BsonProperty("userId") val userId: String,
    @BsonProperty("balance") val balance: BigDecimal
): PanacheMongoEntityBase()