package com.bclis.persistence.repository;

import com.bclis.persistence.entity.RoleEntity;
import com.bclis.persistence.entity.enums.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(EnumRole name);
}
