package net.transino.lms.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.transino.lms.web.entity.ExtElecAccep;
import net.transino.lms.web.mapper.ExtElecAccepMapper;
import net.transino.lms.web.service.IExtElecAccepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author lee
 * @since 5.0
 */
@Service("extElecAccepService")
public class ExtElecAccepServiceImpl implements IExtElecAccepService {
    @Autowired
    ExtElecAccepMapper mapper;

    @Override
    public Page<ExtElecAccep> query(String param, String sotr, int offset, int pageSize) {
        PageHelper.offsetPage(offset, pageSize);
        return this.mapper.query(param);
    }
}