package me.pgthinker.codesnippet.service;

import jakarta.servlet.http.HttpServletRequest;
import me.pgthinker.codesnippet.model.domain.CopyLogDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author pgthinker
* @description 针对表【copy_log】的数据库操作Service
* @createDate 2025-03-21 12:49:43
*/
public interface CopyLogDOService extends IService<CopyLogDO> {

    Boolean addCopyLog(HttpServletRequest request, String codeId);
}
