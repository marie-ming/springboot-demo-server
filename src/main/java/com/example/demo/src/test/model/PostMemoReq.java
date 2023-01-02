package com.example.demo.src.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class PostMemoReq {
    private String memo;
    private Timestamp created;
}
