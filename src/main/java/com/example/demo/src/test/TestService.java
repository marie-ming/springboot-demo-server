package com.example.demo.src.test;

import com.example.demo.common.BaseException;
import com.example.demo.src.test.model.GetMemoDto;
import com.example.demo.src.test.model.MemoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class TestService {

    private final TestDao testDao;

    public void createMemo(MemoDto memoDto) throws BaseException {
        //중복
        if(checkMemo(memoDto.getMemo()) == 1){
            throw new BaseException(POST_TEST_EXISTS_MEMO);
        }
        try{
            testDao.createMemo(memoDto); // POINT
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkMemo(String memo) throws BaseException{
        try{
            return testDao.checkMemo(memo);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetMemoDto> getMemos() throws BaseException{
        try{
            return testDao.getMemos();
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyMemo(int memoId, MemoDto memoDto) throws BaseException{
        try{
            int result = testDao.modifyMemo(memoId, memoDto);
            if(result != 1) {
                throw new BaseException(MODIFY_FAIL_MEMO);
            }
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
