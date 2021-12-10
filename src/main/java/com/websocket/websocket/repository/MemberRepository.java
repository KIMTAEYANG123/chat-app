package com.websocket.websocket.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member,String> {

    List<Member> findByName(String name);
    boolean existsByName(String name);
    int countByName(String name);
    List<MemberMappingName> findNameMappingByName(String name);
}
