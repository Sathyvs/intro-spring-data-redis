package com.springtest.redis;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
  List<Account> findByInstitutionId(String id);
  List<Account> findByTransactionsDate(int date);
}
