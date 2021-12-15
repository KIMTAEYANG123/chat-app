package com.websocket.websocket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member,String> {

    Optional<Member> findById(String id);
    List<Member> findByName(String name);
    boolean existsByName(String name);
    int countByName(String name);
    List<MemberMappingName> findNameMappingByName(String name);
}
