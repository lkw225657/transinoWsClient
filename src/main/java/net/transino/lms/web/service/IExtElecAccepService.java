package net.transino.lms.web.service;

import com.github.pagehelper.Page;
import net.transino.lms.web.entity.ExtElecAccep;

/**
 * @author lee
 * @since 5.0
 */
public interface IExtElecAccepService {
    Page<ExtElecAccep> query(String param, String sotr, int offset, int pageSize);
}