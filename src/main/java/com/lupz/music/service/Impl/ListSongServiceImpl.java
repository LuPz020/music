package com.lupz.music.service.Impl;

import com.lupz.music.entity.ListSong;
import com.lupz.music.mapper.ListSongMapper;
import com.lupz.music.service.ListSongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌单包含歌曲列表 服务实现类
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements ListSongService {

}
