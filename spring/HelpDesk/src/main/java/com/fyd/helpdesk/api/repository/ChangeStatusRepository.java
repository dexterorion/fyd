package com.fyd.helpdesk.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.helpdesk.api.entity.ChangeStatus;

public interface ChangeStatusRepository extends MongoRepository<ChangeStatus, String>{
	
	Iterable<ChangeStatus> findByTicketIdOrderByDataChangeStatusDesc(String ticketId);

}
