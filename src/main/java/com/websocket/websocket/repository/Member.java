package com.websocket.websocket.repository;

import com.websocket.websocket.DTO.MemberType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name="T_MEMBER")    // 클래스명이 테이블명이 일치할 경우는 name을 지정 안 해도됨 아닌 경우 name에 테이블 명
public class Member {

    @Id //pk값
    private String id;

    @Enumerated(EnumType.STRING)    //jpa에서 enum을 사용하기 위해서
    private MemberType type;

    private String password;
    private String name;
    private boolean state;
    private Date joinDate;
    private Date delDate;
    private Date updateDate;
}
