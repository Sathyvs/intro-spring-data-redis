package com.springtest.redis;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class IntroSpringDataRedisController {

    private PersonRepository personRepository;

    private AccountRepository accountRepository;

    private AccountTransactionRepository accountTransactionRepository;

    public IntroSpringDataRedisController(PersonRepository personRepository, AccountRepository accountRepository, AccountTransactionRepository accountTransactionRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @GetMapping("/persons")
    public ResponseEntity<Iterable<Person>> personList() {
        Iterable<Person> allRecords = personRepository.findAll();
        return ResponseEntity.ok(allRecords);
    }

    @GetMapping("/personsByFirstName/{firstName}")
    public ResponseEntity<Iterable<Person>> personsByFirstName(@PathVariable String firstName) {
        List<Person> personList = personRepository.findByFirstname(firstName);
        return ResponseEntity.ok(personList);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam String firstName, @RequestParam String lastName) {
        if(firstName == null) {
            return ResponseEntity.badRequest().build();
        }
        Person person = Person.builder()
                .firstname(firstName)
                .lastname(lastName)
                .address(Address.builder()
                        .street("street")
                        .city("city").build()
                ).build();
        Person savedPerson = personRepository.save(person);
        return ResponseEntity.ok(savedPerson.getId());
    }

    @PostMapping("/saveAccount")
    public ResponseEntity<String> saveAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount.getId());
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(null));
    }

    @GetMapping("/accounts/institution/{id}")
    public ResponseEntity<List<Account>> getAccountByInstitutionId(@PathVariable String id) {
        List<Account> account = accountRepository.findByInstitutionId(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/transactions/save")
    public ResponseEntity<Iterable<Transaction>> saveTransactions(@RequestBody List<Transaction> transaction) {
        Iterable<Transaction> transactions = accountTransactionRepository.saveAll(transaction);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/transactions/{institutionId}/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountIdAndInstitutionId(@PathVariable String institutionId, @PathVariable String accountId) {
        List<Transaction> transactions = accountTransactionRepository.findByInstitutionIdAndAccountId(institutionId, accountId);
        return ResponseEntity.ok(transactions);
    }

    // This is not working - again with JPA - java.lang.IllegalArgumentException: LESS_THAN (1): [IsLessThan, LessThan] is not supported for Redis query derivation!
    @GetMapping("/transactions/{institutionId}/{accountId}/{date}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountIdAndInstitutionIdAndDate(@PathVariable String institutionId, @PathVariable String accountId, @PathVariable int date) {
        List<Transaction> transactions = accountTransactionRepository.findByInstitutionIdAndAccountIdAndDateLessThan(institutionId, accountId, date);
        return ResponseEntity.ok(transactions);
    }

    // This is working for a nested Account, but returns the full list of accounts. JPA will not work here,
    // I think we need to write queries to get only the transactions matching the date index ?
    // adding jpa starts complaining on the build file
    @GetMapping("/transactions/date/{date}")
    public ResponseEntity<List<Account>> getTransactionsByDate(@PathVariable int date) {
        List<Account> transactions = accountRepository.findByTransactionsDate(date);
        return ResponseEntity.ok(transactions);
    }
}
