package com.bclis.repository;

import com.bclis.model.entity.RoleEntity;
import com.bclis.model.enums.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(EnumRole name);
}
