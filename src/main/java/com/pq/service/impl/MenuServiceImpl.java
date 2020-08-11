package com.pq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pq.entity.Menu;
import com.pq.mapper.MenuMapper;
import com.pq.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
