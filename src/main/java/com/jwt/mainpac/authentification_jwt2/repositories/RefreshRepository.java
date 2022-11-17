package com.jwt.mainpac.authentification_jwt2.repositories;

import com.jwt.mainpac.authentification_jwt2.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshToken, Integer> {
    RefreshToken findRefreshTokenById(int id);
}
