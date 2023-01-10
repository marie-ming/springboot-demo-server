package com.example.demo.src.test.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.src.test.model.MemoDto;
import com.example.demo.src.test.model.PostCommentDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "memo", fetch = FetchType.LAZY)
    List<Comment> commentList = new ArrayList<>();

    public void makeMemo(MemoDto memoDto){
        this.memo = memoDto.getMemo();
    }

    public void updateMemo(MemoDto memoDto){
        this.memo = memoDto.getMemo();
    }
}
