package com.example.demo.src.test;


import com.example.demo.common.BaseException;
import com.example.demo.src.test.model.GetMemoRes;
import com.example.demo.src.test.model.PatchMemoReq;
import com.example.demo.src.test.model.PostMemoReq;
import com.example.demo.src.test.model.PostMemoRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoDao memoDao;



    //POST
    public PostMemoRes createMemo(PostMemoReq postMemoReq) throws BaseException {
        //중복
//        if(checkEmail(postMemoReq.getMemo()) ==1){
//            throw new BaseException(POST_USERS_EXISTS_EMAIL);
//        }
        try{
            int memoId = memoDao.createMemo(postMemoReq); // POINT
            return new PostMemoRes(memoId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyMemoName(PatchMemoReq patchMemoReq) throws BaseException {
        try{
            int result = memoDao.modifyMemoName(patchMemoReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_MEMO);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public void deleteMemo(int memoId) throws BaseException {
//        try{
//            int result = memoDao.deleteMemo(memoId);
//            if(result == 0){
//                throw new BaseException(DELETE_FAIL_MEMO);
//            }
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }

    public List<GetMemoRes> getMemos() throws BaseException{
        try{
            List<GetMemoRes> getMemoRes = memoDao.getMemos();
            return getMemoRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }};
