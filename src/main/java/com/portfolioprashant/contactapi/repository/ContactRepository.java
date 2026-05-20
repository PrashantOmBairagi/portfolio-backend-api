package com.portfolioprashant.contactapi.repository;

import com.portfolioprashant.contactapi.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {
}
