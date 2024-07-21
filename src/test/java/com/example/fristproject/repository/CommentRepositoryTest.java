package com.example.fristproject.repository;

import com.example.fristproject.entity.Article;
import com.example.fristproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        {
            Long articleId = 4L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, article, "park", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "choi", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력");
        }

        {
            Long articleId = 1L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = new Article(1L, "가가가가가","11111");
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
        {
            Long articleId = 9L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            List<Comment> expected = new ArrayList<>();
            assertEquals(comments, expected, "9번 게시글의 모든 댓글 조회");
        }
        {
            Long articleId = 999L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            List<Comment> expected = new ArrayList<>();
            assertEquals(comments, expected, "999번 게시글의 모든 댓글 조회");
        }
        {
            Long articleId = -1L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            List<Comment> expected = new ArrayList<>();
            assertEquals(comments, expected, "-1번 게시글의 모든 댓글 조회");
        }
    }

    @Test
    @DisplayName("닉네임으로 댓글 조회")
    void findByNickname() {
        {
            String nickname = "kim";
            List<Comment> comments = commentRepository.findByNickname(nickname);
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), nickname, "아이 엠 샘");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), nickname, "샤브샤브");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 고고고"), nickname, "유튜브 시청");

            List<Comment> expected = Arrays.asList(a, b, c);
            assertEquals(expected.toString(), comments.toString(), "kim의 모든 댓글 조회");
        }
        {
            String nickname = null;
            List<Comment> comments = commentRepository.findByNickname(nickname);
            assertEquals(new ArrayList<>(), comments, "null의 모든 댓글 조회");
        }
        {
            String nickname = "";
            List<Comment> comments = commentRepository.findByNickname(nickname);
            assertEquals(new ArrayList<>(), comments, " \"\" 모든 댓글 조회");
        }
    }
}