package com.lupz.music.service.Impl;

import com.lupz.music.entity.Song;
import com.lupz.music.mapper.SongMapper;
import com.lupz.music.service.SongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌曲表 服务实现类
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

}
