package com.growit.api.repo;

import com.growit.api.domain.Account;
import com.growit.api.domain.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepo  extends CrudRepository<Transaction, Long> {

    List<Transaction> findAllByAccountOrderByCreatedAsc(Account account);
}
