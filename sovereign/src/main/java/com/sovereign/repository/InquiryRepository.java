package com.sovereign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sovereign.model.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {}
