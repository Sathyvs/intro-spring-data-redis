package com.springtest.redis;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends CrudRepository<Transaction, String> {
    List<Transaction> findByInstitutionIdAndAccountId(String institutionId, String accountId);

    List<Transaction> findByInstitutionIdAndAccountIdAndDateLessThan(String institutionId, String accountId, int date);

}
