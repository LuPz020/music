package com.lupz.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lupz.music.common.result.Result;
import com.lupz.music.entity.Comment;
import com.lupz.music.entity.Consumer;
import com.lupz.music.service.CommentService;
import com.lupz.music.service.ConsumerService;
import com.lupz.music.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    final private CommentService commentService;
    final  private ConsumerService consumerService;

    @PostMapping("add")
    public Result addComment(@RequestBody Comment comment) {
        if (commentService.save(comment)) {
            return Result.succ(null);
        }
        return Result.fail("评论失败");
    }

    @PutMapping("update")
    public Result updateComment(@RequestBody Comment comment) {
        if (commentService.saveOrUpdate(comment)) {
            return Result.succ(null);
        }
        return Result.fail("修改评论失败");
    }

    @DeleteMapping("delete")
    public Result deleteComment(@RequestParam("commentId") Integer commentId
            ,@RequestParam("songListId")Integer songLIstId) {
        if (commentService.remove(new LambdaQueryWrapper<Comment>().eq(Comment::getId,commentId)
                .eq(Comment::getSongListId,songLIstId))) {
            return Result.succ(null);
        }
        return Result.fail("删除评论失败");
    }

    @GetMapping("allComment")
    public Result getAllComment() {
        return Result.succ(commentService.list());
    }

    //查询某个歌曲下的所有评论
    @GetMapping("aSongComment/{id}")
    public Result getSongComment(@PathVariable("id") Integer id) {
        return Result.succ(commentService.list(new QueryWrapper<Comment>()
                .eq("song_id", id)));
    }

    //查询某个歌单下的所有评论
    @GetMapping("aSongListComment/{id}")
    public Result getSongListComment(@PathVariable("id") Integer id) {
        return Result.succ(commentService.list(new QueryWrapper<Comment>()
                .eq("song_list_id", id)));
    }

    //点赞
    @PutMapping("likeComment")
    public Result LikeComment(@RequestBody Map<String,Integer> up) {
        Integer id = up.get("id");
        Integer upN = up.get("up");
        Comment comment = commentService.getById(id);
        comment.setUp(upN);
        if(commentService.saveOrUpdate(comment)){
            return Result.succ(null);
        }
        return Result.fail("点赞成功");
    }

    //获取某个歌单下的评论和用户信息
    @GetMapping("UserAndComment/{id}")
    public Result UserAndComment(@PathVariable("id")Integer id){
        List<Comment> list = commentService.list(new LambdaQueryWrapper<Comment>()
                .eq(Comment::getSongListId,id));
        List<CommentVo> consumerVos = new ArrayList<>();
        CommentVo commentVo ;
        for (Comment comment : list) {
            commentVo  = new CommentVo();
            Consumer byId = consumerService.getById(comment.getUserId());
            commentVo.setId(comment.getId());
            commentVo.setUserName(byId.getUserName());
            commentVo.setContent(comment.getContent());
            consumerVos.add(commentVo);
        }
        return Result.succ(consumerVos);
    }
}
