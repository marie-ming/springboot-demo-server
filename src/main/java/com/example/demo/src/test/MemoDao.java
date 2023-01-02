package com.example.demo.src.test;


import com.example.demo.src.test.model.GetMemoRes;
import com.example.demo.src.test.model.PatchMemoReq;
import com.example.demo.src.test.model.PostMemoReq;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MemoDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetMemoRes> getMemos(){
        String getMemosQuery = "select * from MEMO";
        return this.jdbcTemplate.query(getMemosQuery,
                (rs,rowNum) -> new GetMemoRes(
                        rs.getInt("id"),
                        rs.getString("memo"))
                );
    }

    public int createMemo(PostMemoReq postMemoReq){
        String createMemoQuery = "insert into MEMO (memo,created) VALUES (?,?)";
        Object[] createMemoParams = new Object[]{postMemoReq.getMemo(), postMemoReq.getCreated()};
        this.jdbcTemplate.update(createMemoQuery, createMemoParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int modifyMemoName(PatchMemoReq patchMemoReq){
        String modifyMemoNameQuery = "update MEMO set memo = ? where id = ? ";
        Object[] modifyMemoNameParams = new Object[]{patchMemoReq.getMemo(), patchMemoReq.getId()};

        return this.jdbcTemplate.update(modifyMemoNameQuery,modifyMemoNameParams);
    }

//    public int deleteUser(int userId){
//        String deleteUserNameQuery = "update USER set status = ? where id = ? ";
//        Object[] deleteUserNameParams = new Object[]{"INACTIVE", userId};
//
//        return this.jdbcTemplate.update(deleteUserNameQuery,deleteUserNameParams);
//    }



}
