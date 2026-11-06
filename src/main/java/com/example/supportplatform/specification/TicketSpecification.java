package com.example.supportplatform.specification;

import com.example.supportplatform.entity.Ticket;
import com.example.supportplatform.entity.TicketStatus;
import org.springframework.data.jpa.domain.Specification;

public class TicketSpecification {

    public static Specification<Ticket> hasStatus(TicketStatus status){
        return (root , query , criteriaBuilder) -> criteriaBuilder.equal(root.get("status") , status);
    }

    public static Specification<Ticket> hasEmail(String email){
        return (root , query , criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("email"))
                                                                        , "%" + email.toLowerCase() + "%"
                                                                        );
    }

    public static Specification<Ticket> hasSubject(String subject){
        return (root , query , criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("subject"))
                                                                        , "%" + subject.toLowerCase() + "%"
                                                                        );
    }
}
