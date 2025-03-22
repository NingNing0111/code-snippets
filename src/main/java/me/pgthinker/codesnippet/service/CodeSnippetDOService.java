package me.pgthinker.codesnippet.service;

import me.pgthinker.codesnippet.common.BaseResponse;
import me.pgthinker.codesnippet.model.domain.CodeSnippetDO;
import com.baomidou.mybatisplus.extension.service.IService;
import me.pgthinker.codesnippet.model.vo.CodeSnippetVO;

import java.util.List;

/**
* @author pgthinker
* @description 针对表【code_snippet】的数据库操作Service
* @createDate 2025-03-21 12:49:43
*/
public interface CodeSnippetDOService extends IService<CodeSnippetDO> {


    List<CodeSnippetVO> list(CodeSnippetVO codeSnippetVO);

    String add(CodeSnippetVO codeSnippetVO);

    Boolean delete(String id);

    Boolean update(CodeSnippetVO codeSnippetVO);
}
