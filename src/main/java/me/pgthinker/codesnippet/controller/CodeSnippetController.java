package me.pgthinker.codesnippet.controller;

import lombok.RequiredArgsConstructor;
import me.pgthinker.codesnippet.common.BaseResponse;
import me.pgthinker.codesnippet.common.ErrorCode;
import me.pgthinker.codesnippet.common.ResultUtils;
import me.pgthinker.codesnippet.model.vo.CodeSnippetVO;
import me.pgthinker.codesnippet.service.CodeSnippetDOService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Project: me.pgthinker.codesnippet.controller
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/21 12:52
 * @Description:
 */
@RestController
@RequestMapping("/codeSnippet")
@RequiredArgsConstructor
public class CodeSnippetController {

    private final CodeSnippetDOService codeSnippetDOService;

    @Value("${app.password}")
    private String password;


    @GetMapping("/list")
    public BaseResponse<List<CodeSnippetVO>> list(CodeSnippetVO vo) {
        return ResultUtils.success(codeSnippetDOService.list(vo));
    }

    @PostMapping("/addCodeSnippet")
    public BaseResponse<String> add(@RequestBody CodeSnippetVO vo , @RequestHeader(value = "Authorization") String authorization) {
        if(!Objects.equals(authorization, password)) {
            return ResultUtils.error(ErrorCode.NO_AUTH_ERROR);
        }
        return ResultUtils.success(codeSnippetDOService.add(vo));
    }

    @PostMapping("/deleteCodeSnippet")
    public BaseResponse<Boolean> delete(@RequestBody CodeSnippetVO vo,@RequestHeader(value = "Authorization") String authorization) {
        if(!Objects.equals(authorization, password)) {
            return ResultUtils.error(ErrorCode.NO_AUTH_ERROR);
        }
        return ResultUtils.success(codeSnippetDOService.delete(vo.getId()));
    }

//    @PostMapping("/updateCodeSnippet")
//    public BaseResponse<Boolean> update(@RequestBody CodeSnippetVO vo) {
//        return ResultUtils.success(codeSnippetDOService.update(vo));
//    }
}
