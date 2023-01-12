package com.example.demo.src.test;

import com.example.demo.common.BaseEntity.State;
import com.example.demo.src.test.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMemoAndState(String memo, State state);

    List<Memo> findAllByState(State state);

    Optional<Memo> findByIdAndState(Long memoId, State state);
}
