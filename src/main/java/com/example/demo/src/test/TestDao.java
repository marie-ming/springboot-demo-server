package com.example.demo.src.test;

import com.example.demo.src.test.model.GetMemoDto;
import com.example.demo.src.test.model.MemoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TestDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public MemoDto createMemo(MemoDto memoDto){
        String createMemoQuery = "insert into MEMO (memo) VALUES (?)";
        Object[] createMemoParams = new Object[]{memoDto.getMemo()};
        this.jdbcTemplate.update(createMemoQuery, createMemoParams);

        return memoDto;
    }


    public int checkMemo(String memo){
        String checkMemoQuery = "select exists(select memo from MEMO where memo = ? and state = 'ACTIVE')";
        String checkMemoParams = memo;
        return this.jdbcTemplate.queryForObject(checkMemoQuery,
                int.class,
                checkMemoParams);

    }


    public List<GetMemoDto> getMemos() {
        String getUsersQuery = "select * from MEMO where state = 'ACTIVE'";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetMemoDto(
                        rs.getInt("id"),
                        rs.getString("memo"))
        );
    }


    public int modifyMemo(int memoId, MemoDto memoDto){
        String modifyMemoQuery = "update MEMO set memo = ? where id = ? ";
        Object[] modifyMemoParams = new Object[]{memoDto.getMemo(), memoId};

        return this.jdbcTemplate.update(modifyMemoQuery,modifyMemoParams);
    }

}
