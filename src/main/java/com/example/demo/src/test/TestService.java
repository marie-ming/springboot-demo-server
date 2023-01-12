package com.example.demo.src.test;

import com.example.demo.common.BaseException;
import com.example.demo.src.test.entity.Comment;
import com.example.demo.src.test.entity.Memo;
import com.example.demo.src.test.model.GetMemoDto;
import com.example.demo.src.test.model.MemoDto;
import com.example.demo.src.test.model.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.common.BaseEntity.State.ACTIVE;
import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@Transactional
@Service
public class TestService {

    private final TestDao testDao;
    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;

    public void createMemo(MemoDto memoDto) throws BaseException {
        Memo memo = new Memo();
        memo.makeMemo(memoDto);
        //중복
        if(checkMemo(memoDto.getMemo()) == 1){
            throw new BaseException(POST_TEST_EXISTS_MEMO);
        }
        try{
            //testDao.createMemo(memo); // POINT
            memoRepository.save(memo);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public int checkMemo(String memo) throws BaseException{
        try{
            //List<Memo> memoList = testDao.checkMemo(memo);
            List<Memo> memoList =  memoRepository.findByMemoAndState(memo, ACTIVE);
            return memoList.size();
        } catch (Exception exception){
            //exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public List<GetMemoDto> getMemos() throws BaseException{
        try{
            //List<Memo> memoList = testDao.getMemos();
            List<Memo> memoList = memoRepository.findAllByState(ACTIVE);

            List<GetMemoDto> getMemoDtoList = new ArrayList<>();
            for(Memo memo : memoList){
                GetMemoDto dto = new GetMemoDto(memo);
                getMemoDtoList.add(dto);
            }
            return getMemoDtoList;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyMemo(Long memoId, MemoDto memoDto) throws BaseException{
        try{
            //Memo memo = testDao.findMemo(memoId);
            Memo memo = memoRepository.findByIdAndState(memoId, ACTIVE).get();

            if(memo == null){
                throw new BaseException(MODIFY_FAIL_MEMO);
            }
            memo.updateMemo(memoDto);

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void createComment(PostCommentDto postCommentDto) throws BaseException {
        try{
            //Memo memo = testDao.findMemo(postCommentDto.getMemoId());
            Memo memo = memoRepository.findByIdAndState(postCommentDto.getMemoId(), ACTIVE).get();

            Comment comment = new Comment();
            comment.makeComment(postCommentDto, memo);

            //testDao.createComment(comment);
            commentRepository.save(comment);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
