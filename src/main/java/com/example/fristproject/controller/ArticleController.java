package com.example.fristproject.controller;

import com.example.fristproject.dto.ArticleForm;
import com.example.fristproject.dto.CommentDto;
import com.example.fristproject.entity.Article;
import com.example.fristproject.repository.ArticleRepository;
import com.example.fristproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j

public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(Model model) {
        return "articles/new";
    }

    List<Integer> list = new ArrayList<>();

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
        //System.out.println(form.toString());
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        log.info("id = " + id);
        List<CommentDto> commentDtos = commentService.comments(id);
        log.info(commentDtos.toString());
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        return "articles/show";

    }

    @GetMapping("/articles")
    public String index(Model model) {
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        log.info("id = " + id);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        Article articleEntity = form.toEntity();
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null)
            articleRepository.save(articleEntity);
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다.");
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제완료");
        }
        return "redirect:/articles";
    }
}
