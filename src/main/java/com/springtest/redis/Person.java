package com.springtest.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
@Builder
@RedisHash("Person")
public class Person {
    private String id;
    @Indexed private String firstname;
    private String lastname;
    private Address address;
}
