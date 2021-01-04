package com.prodev.spring5.webapp.repository;

import com.prodev.spring5.webapp.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {
}
