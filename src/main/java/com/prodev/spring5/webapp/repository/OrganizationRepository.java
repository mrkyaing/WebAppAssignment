package com.prodev.spring5.webapp.repository;

import com.prodev.spring5.webapp.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
}
