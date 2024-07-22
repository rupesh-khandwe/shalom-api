package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface DonationRepository extends JpaRepository<Donation, Long> {


}
