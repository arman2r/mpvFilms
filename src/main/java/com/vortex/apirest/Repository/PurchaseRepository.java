package com.vortex.apirest.Repository;

import com.vortex.apirest.Entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUserEmail(String email);

    Optional<Purchase> findByUserEmailAndConfirmationStatus(String userEmail, int confirmationStatus);
}
