package com.example.fristproject.controller;

import com.example.fristproject.dto.MemberForm;
import com.example.fristproject.entity.Member;
import com.example.fristproject.repository.MemberRepository;
import jakarta.persistence.Access;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signUpPage() {
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        log.info(memberForm.toString());
        Member member = memberForm.toEntity();
        log.info(member.toString());
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member memeberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memeberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        ArrayList<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberRepository.findAll());
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);
        log.info(member.toString());
        model.addAttribute("member", member);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm memberForm) {
        Member memberEntity = memberForm.toEntity();
        log.info(memberForm.toString());
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if (target != null)
            memberRepository.save(memberEntity);
        return "redirect:/members/" + target.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Member target = memberRepository.findById(id).orElse(null);
        if (target != null) {
            memberRepository.delete(target);
            redirectAttributes.addFlashAttribute("msg", "삭제완료");
        }
        return "redirect:/members";
    }
}
