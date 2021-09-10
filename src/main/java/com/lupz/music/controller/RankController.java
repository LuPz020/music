package com.lupz.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lupz.music.common.result.Result;
import com.lupz.music.entity.Rank;
import com.lupz.music.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 评价 前端控制器
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class RankController {
    final private RankService rankService;

    //新增评价
    @PostMapping("add")
    public Result RankAdd(@RequestBody Rank rank){
        if(rankService.save(rank)){
            return Result.succ(null);
        }
        return  Result.fail("评价失败");
    }

    //计算平均分
    @GetMapping("avgScore/{songListId}")
    public Result RankAvgScore(@PathVariable("songListId") Integer songListId) {
        QueryWrapper<Rank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id", songListId);
        queryWrapper.select("round(avg(score),1) AS avg_score");
        Map<String, Object> map = rankService.getMap(queryWrapper);
        return Result.succ(map);
    }
}
