package com.lupz.music.service.Impl;

import com.lupz.music.entity.Comment;
import com.lupz.music.mapper.CommentMapper;
import com.lupz.music.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
