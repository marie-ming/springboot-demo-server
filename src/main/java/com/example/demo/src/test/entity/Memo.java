package com.example.demo.src.test.entity;

import com.example.demo.common.BaseEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(name = "MEMO")
public class Memo extends BaseEntity {

    @Id //pk
    @Column(name="id", nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memo;
}
