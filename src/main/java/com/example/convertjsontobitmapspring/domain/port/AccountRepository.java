package com.example.convertjsontobitmapspring.domain.port;

import com.example.convertjsontobitmapspring.domain.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
