package com.springtest.redis;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
@Builder
@RedisHash("Account")
public class Account {
    private String id;
    @Indexed private String institutionId;
    private String accountName;
    private AccountType type;
    private List<Transaction> transactions;
}
