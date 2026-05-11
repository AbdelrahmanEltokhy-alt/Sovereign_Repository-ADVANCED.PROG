package com.sovereign.repository;

import com.sovereign.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ContactRepository — saves and retrieves contact form submissions.
 * Admin sees all messages via findAll() on the dashboard.
 */
@Repository
public interface ContactRepository extends JpaRepository<ContactMessage, Integer> {
}
