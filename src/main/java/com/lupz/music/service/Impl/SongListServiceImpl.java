package com.lupz.music.service.Impl;

import com.lupz.music.entity.SongList;
import com.lupz.music.mapper.SongListMapper;
import com.lupz.music.service.SongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌单 服务实现类
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {

}
