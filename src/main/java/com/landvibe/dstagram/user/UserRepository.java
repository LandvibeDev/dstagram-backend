package com.landvibe.dstagram.user;

import com.landvibe.dstagram.user.model.DstagramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DstagramUser, Integer> {

    Optional<DstagramUser> findByEmail(String email);
}
