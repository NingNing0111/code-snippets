package me.pgthinker.codesnippet.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.pgthinker.codesnippet.common.BaseResponse;
import me.pgthinker.codesnippet.common.ResultUtils;
import me.pgthinker.codesnippet.service.CopyLogDOService;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: me.pgthinker.codesnippet.controller
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/21 13:19
 * @Description:
 */
@RestController
@RequestMapping("/log/copy")
@RequiredArgsConstructor
public class CopyLogController {

    private final CopyLogDOService copyLogDOService;

    @PostMapping("/add/{codeId}")
    public BaseResponse<Boolean> add(HttpServletRequest httpServletRequest, @PathVariable String codeId) {
        return ResultUtils.success(copyLogDOService.addCopyLog(httpServletRequest,codeId));
    }
}
