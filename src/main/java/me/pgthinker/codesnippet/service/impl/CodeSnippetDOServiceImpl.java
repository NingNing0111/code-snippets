package me.pgthinker.codesnippet.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.codesnippet.mapper.CopyLogDOMapper;
import me.pgthinker.codesnippet.model.domain.CodeSnippetDO;
import me.pgthinker.codesnippet.model.domain.CopyLogDO;
import me.pgthinker.codesnippet.model.vo.CodeSnippetVO;
import me.pgthinker.codesnippet.service.CodeSnippetDOService;
import me.pgthinker.codesnippet.mapper.CodeSnippetDOMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
* @author pgthinker
* @description 针对表【code_snippet】的数据库操作Service实现
* @createDate 2025-03-21 12:49:43
*/
@Service
@RequiredArgsConstructor
@Slf4j
public class CodeSnippetDOServiceImpl extends ServiceImpl<CodeSnippetDOMapper, CodeSnippetDO>
    implements CodeSnippetDOService{

    private final CopyLogDOMapper copyLogDOMapper;

    @Override
    public List<CodeSnippetVO> list(CodeSnippetVO codeSnippetVO) {
        String category = codeSnippetVO.getCategory();
        String content = codeSnippetVO.getContent();
        String description = codeSnippetVO.getDescription();
        String title = codeSnippetVO.getTitle();
        LambdaUpdateWrapper<CodeSnippetDO> qw = new LambdaUpdateWrapper<>();
        if(category != null) {
            qw.eq(CodeSnippetDO::getCategory, category);
        }
        if(content != null) {
            qw.or().like(CodeSnippetDO::getContent, content);
        }
        if(description != null) {
            qw.or().like(CodeSnippetDO::getDescription, description);
        }
        if(title != null) {
            qw.or().like(CodeSnippetDO::getTitle, title);
        }
        List<CodeSnippetDO> list = this.list(qw);

        List<CodeSnippetVO> transfer = transfer(list);
        ArrayList<CodeSnippetVO> sortedRes = new ArrayList<>(transfer);

        sortedRes.sort((o1, o2) -> Long.compare(o2.getCopyCnt(), o1.getCopyCnt()));

        return sortedRes;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String add(CodeSnippetVO codeSnippetVO) {
        CodeSnippetDO codeSnippetDO = new CodeSnippetDO();
        BeanUtils.copyProperties(codeSnippetVO, codeSnippetDO);
        this.save(codeSnippetDO);
        return codeSnippetDO.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delete(String id) {
        CodeSnippetDO byId = this.getById(id);
        if(byId != null) {
            return this.removeById(id);
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean update(CodeSnippetVO codeSnippetVO) {
        CodeSnippetDO codeSnippetDO = new CodeSnippetDO();
        BeanUtils.copyProperties(codeSnippetVO, codeSnippetDO);
        return this.updateById(codeSnippetDO);
    }

    private List<CodeSnippetVO> transfer(List<CodeSnippetDO> codeSnippetDOList) {
        return codeSnippetDOList.stream().map(this::transfer).toList();
    }

    private CodeSnippetVO transfer(CodeSnippetDO codeSnippetDO) {
        CodeSnippetVO codeSnippetVO = new CodeSnippetVO();
        BeanUtils.copyProperties(codeSnippetDO, codeSnippetVO);
        LambdaUpdateWrapper<CopyLogDO> qw = new LambdaUpdateWrapper<>();
        qw.eq(CopyLogDO::getCodeId, codeSnippetDO.getId());
        Long cnt = copyLogDOMapper.selectCount(qw);
        codeSnippetVO.setCopyCnt(cnt);
        return codeSnippetVO;
    }
}




