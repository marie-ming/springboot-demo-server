package com.example.demo.common;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "createAt", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updateAt", nullable = false)
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 10)
    protected State state = State.ACTIVE;

    public enum State{
        ACTIVE, INACTIVE;
    }
}
