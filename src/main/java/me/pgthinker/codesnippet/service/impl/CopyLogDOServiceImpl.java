package me.pgthinker.codesnippet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.codesnippet.model.domain.CopyLogDO;
import me.pgthinker.codesnippet.service.CopyLogDOService;
import me.pgthinker.codesnippet.mapper.CopyLogDOMapper;
import org.apache.tomcat.util.net.IPv6Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author pgthinker
* @description 针对表【copy_log】的数据库操作Service实现
* @createDate 2025-03-21 12:49:43
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class CopyLogDOServiceImpl extends ServiceImpl<CopyLogDOMapper, CopyLogDO>
    implements CopyLogDOService{

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addCopyLog(HttpServletRequest request, String codeId) {
        CopyLogDO copyLogDO = new CopyLogDO();
        copyLogDO.setCodeId(codeId);
        copyLogDO.setUserIp(request.getRemoteAddr());
        copyLogDO.setUserAgent(request.getHeader("User-Agent"));
        return this.save(copyLogDO);
    }
}




