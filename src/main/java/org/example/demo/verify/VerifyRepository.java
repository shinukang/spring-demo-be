package org.example.demo.verify;

import org.example.demo.verify.model.Verify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerifyRepository extends JpaRepository<Verify, Long> {
    Optional<Verify> findByUuid(String uuid);
}
