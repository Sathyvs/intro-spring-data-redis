package com.springtest.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
@Builder
@RedisHash("Transaction")
public class Transaction {
    private String id;
    private int amount;
    private String currency;
    @Indexed private int date;
    private String description;
    @Indexed private String accountId;
    @Indexed private String institutionId;
    private TransactionType type;
}
