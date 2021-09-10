package com.lupz.music.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lupz.music.entity.Admin;
import com.lupz.music.mapper.AdminMapper;
import com.lupz.music.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
