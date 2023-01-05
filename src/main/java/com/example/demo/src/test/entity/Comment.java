package com.example.demo.src.test.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.src.test.model.MemoDto;
import com.example.demo.src.test.model.PostCommentDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(name = "COMMENT")
public class Comment extends BaseEntity {

    @Id //pk
    @Column(name="id", nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name="memoId")
    private Memo memo;

    public void makeComment(PostCommentDto postCommentDto, Memo memo){
        this.memo = memo;
        this.comment = postCommentDto.getComment();
    }
}
