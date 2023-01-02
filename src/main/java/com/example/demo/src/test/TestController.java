package com.example.demo.src.test;

import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.test.model.*;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final MemoService memoService;

    /**
     * 로그 테스트 API
     * [GET] /test/log
     * @return String
     */
    @ResponseBody
    @GetMapping("/log")
    public String getAll() {
        System.out.println("테스트");
        return "Success Test";
    }

    /**
     * 메모생성 API
     * [POST] /test
     * @return BaseResponse<PostMemoRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/memos")
    public BaseResponse<PostMemoRes> createMemo(@RequestBody PostMemoReq postMemoReq) {
        if(postMemoReq.getMemo() == null){
            return new BaseResponse<>(POST_MEMO_EMPTY_MEMO);
        }
        try{
            PostMemoRes postMemoRes = memoService.createMemo(postMemoReq);
            return new BaseResponse<>(postMemoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 메모 조회 API
     * [GET] /test
     * @return BaseResponse<List<GetMemoRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/memos") // (GET) 127.0.0.1:9000/test/memos
    public BaseResponse<List<GetMemoRes>> getMemos() {
        try{
            List<GetMemoRes> getMemosRes = memoService.getMemos();
            return new BaseResponse<>(getMemosRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 메모수정 API
     * [PATCH] /test/memos/:memoId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/memos/{memoId}")
    public BaseResponse<String> modifyMemoName(@PathVariable("memoId") int memoId, @RequestBody Memo memo){
        try {
            PatchMemoReq patchMemoReq = new PatchMemoReq(memoId,memo.getMemo());
            memoService.modifyMemoName(patchMemoReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
