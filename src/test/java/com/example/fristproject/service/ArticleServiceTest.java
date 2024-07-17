package com.example.fristproject.service;

import com.example.fristproject.dto.ArticleForm;
import com.example.fristproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        List<Article> articles = articleService.index();

        Article a = new Article(1L, "가가가가가", "11111");
        Article b = new Article(2L, "나나나나나", "22222");
        Article c = new Article(3L, "다다다다다", "33333");

        List<Article> expected = new ArrayList<>(Arrays.asList(a,b,c));

        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        Long id = 1L;
        Article expected = new Article(id, "가가가가가", "11111");
        Article article = articleService.show(id);
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_실패_존재하지_않는_id_입력() {
        Long id = -1L;
        Article expected = null;
        Article article = articleService.show(id);
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto입력() {
        String title = "라라라라라";
        String content = "44444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        Article article = articleService.create(dto);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_실패_id가_포함된_dto_입력() {
        Long id = 4L;
        String title = "라라라라라";
        String content = "44444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        Article article = articleService.create(dto);
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        Article expected = new Article(1L,"가나다라","test");
        ArticleForm dto = new ArticleForm(1L, "가나다라", "test");
        Article article = articleService.update(1L, dto);
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        Article expected = new Article(1L,"가나다라","11111");
        ArticleForm dto = new ArticleForm(1L, "가나다라", null);
        Article article = articleService.update(1L, dto);
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void update_실패_존재하지_않는_id와_dto_입력() {
        Article expected = null;
        ArticleForm dto = new ArticleForm(-1L, "가나다라", "abcd");
        Article article = articleService.update(-11L, dto);
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        Long id = 1L;
        Article expected = articleService.show(id);
        Article article = articleService.delete(id);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력() {
        Long id = -1L;
        Article expected = null;
        Article article = articleService.delete(id);
        assertEquals(expected, article);
    }

}