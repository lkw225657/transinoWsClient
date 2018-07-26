package net.transino.lms.web.mapper;

import com.github.pagehelper.Page;
import net.transino.lms.web.entity.ExtElecAccep;
import org.apache.ibatis.annotations.Param;

/**
 * @author lee
 * @since 5.0
 */
public interface ExtElecAccepMapper {
    Page<ExtElecAccep> query(@Param("tradeType") String param);
}