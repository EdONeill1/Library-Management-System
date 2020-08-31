package main.java.ie.bibliotech.controller;



import main.java.ie.bibliotech.model.Artifact;
import main.java.ie.bibliotech.repository.MemberRepository;
import main.java.ie.bibliotech.repository.MemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.java.ie.bibliotech.model.Member;
import main.java.ie.bibliotech.model.Loan;

import java.sql.Date;

import java.util.List;
import java.util.Optional;

@Controller

public class MemberController {


    @Autowired
    MemberRepository MemberRepository;
    @Autowired
    main.java.ie.bibliotech.repository.ArtifactRepository ArtifactRepository;
    @Autowired
    main.java.ie.bibliotech.LoanRepository LoanRepository;



    @GetMapping("/memberLogin")
    public String memberLogin() {
        return "memberLogin.html";
    }

    @PostMapping("/member-Index")
    public String memberIndex(@RequestParam(name="user") String user,
                                 @RequestParam(name="password") String password,
                                 Model model){
        if(user.equals("Sam") || user.equals("Ed") || user.equals("Mo")) {
            model.addAttribute("user", user);
            return "memberIndex.html";
        }
        return "failure.html";
    }

    @GetMapping("/search-Member")
    public String search(@RequestParam(name="search") String search, Model model) {
        System.out.println("SEARCH -> " + search);
        List<Member> members = MemberRepository.findAll();
        Long id = Long.valueOf(0);
        for(int i = 0; i < members.size(); i++){
            if(members.get(i).getName().equals(search)) {
                id = members.get(i).getId();
            }
        }
        Optional<Member> member = MemberRepository.findById(id);
        member.ifPresent(member1 -> model.addAttribute("member", member1));
        return "viewMembers.html";
    }

    @GetMapping("/browseMembers")
    public String browse(Model model) {
        model.addAttribute("members", MemberRepository.findAll());
        return "browseMembers.html";
    }
    @GetMapping("/loans")
    public String loans(Model model) {
        model.addAttribute("loans", LoanRepository.findAll());
        return "loans.html";
    }
    @GetMapping("/historicalLoans")
    public String historicalLoans(@RequestParam(name = "id") Long id, Model model){
        List<Member> members = MemberRepository.findAll();
        List<Loan> loans = LoanRepository.findAll();
        for(int i = 0; i < members.size(); i++){
            for(int j = 0; j < loans.size(); j++){
                if(members.get(i).getName().equals(loans.get(i).getMember())){
                    Optional<Member> member = MemberRepository.findById(Long.valueOf(id));
                    member.ifPresent(member1 -> model.addAttribute("member", member1));
                    model.addAttribute("loans", LoanRepository.findAll());
                    return "historicalLoans.html";
                }
            }
        }
        return "failure.html";
    }

    @GetMapping("/create_member")
    public String create() {
        return "create_member.html";
    }

    @GetMapping("/removeAMember")
    public String remove() {
        return "removeMember.html";
    }


    @GetMapping("/viewMembers")
    public String viewM(@RequestParam(name = "id") Long id, Model model) {
        Optional<Member> member = MemberRepository.findById(id);

        member.ifPresent(member1 -> model.addAttribute("member", member1));
        return "viewMembers.html";
    }

    @PostMapping("/createMember")
    public String createMember(@RequestParam(name = "name") String name,
                               @RequestParam(name = "favouriteGenre") String favouriteGenre,
                               @RequestParam(name = "currentLoan") String currentLoan,
                               Model model) {
        Member member = createAMember(name, favouriteGenre, currentLoan);
        main.java.ie.bibliotech.model.Artifact artifact = createCurrenntArtifact(currentLoan, "On Loan", false);
        Loan loan = createLoan(currentLoan, name);
        if(!LoanRepository.exists(Example.of(loan))){
            LoanRepository.save(loan);
        }
        if (!MemberRepository.exists(Example.of(member))) {
            MemberRepository.save(member);
        }
        if(!ArtifactRepository.exists(Example.of(artifact))){
            ArtifactRepository.save(artifact);
        }

        return "redirect:/browseMembers";
    }

    @GetMapping("/editMember")
    public String editMember(@RequestParam(name="search") String search, Model model) {
        System.out.println("SEARCH -> " + search);
        List<Member> members = MemberRepository.findAll();
        Long id = Long.valueOf(0);
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(search)) {
                id = members.get(i).getId();
                Optional<Member> member = MemberRepository.findById(id);
                if (member.isPresent()) {
                    MemberRepository.deleteById(id);
                }
                return "editMember.html";
            }
        }
        return "failure.html";
    }

    @PostMapping("/edit-Member")
    public String editMember(@RequestParam(name = "name") String name,
                             @RequestParam(name = "favouriteGenre") String favouriteGenre,
                             @RequestParam(name = "currentLoan") String currentLoan,
                             Model model){
        Member editMember = createAMember(name, favouriteGenre, currentLoan);
        if (!MemberRepository.exists(Example.of(editMember))) {
            MemberRepository.save(editMember);
        }
        return "redirect:/browseMembers";
    }

    @GetMapping("/removeMember")
    public String removeMember(@RequestParam(name = "id") Long id, Model model) {
        Optional<Member> member = MemberRepository.findById(id);
        if (member.isPresent()) {
            MemberRepository.deleteById(id);
        }
        return "redirect:/browseMembers";
    }

    private Member createAMember(String name, String favouriteGenre, String currentLoan) {
        Member member = new Member();
        member.setName(name);
        member.setFavouriteGenre(favouriteGenre);
        member.setCurrentLoan(currentLoan);
        return member;
    }
    Loan createLoan(String artifact, String member){
        Loan loan = new Loan();
        loan.setArtifact(artifact);
        loan.setMember(member);
        return loan;
    }
    main.java.ie.bibliotech.model.Artifact createCurrenntArtifact(String name, String status, Boolean reserved){
        main.java.ie.bibliotech.model.Artifact artifact = new Artifact();
        artifact.setName(name);
        artifact.setStatus(status);
        artifact.setReserved(reserved);
        return  artifact;
    }




}
