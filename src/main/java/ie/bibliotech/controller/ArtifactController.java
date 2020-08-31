package main.java.ie.bibliotech.controller;

import main.java.ie.bibliotech.repository.ArtifactRepository;
import main.java.ie.bibliotech.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import main.java.ie.bibliotech.model.Artifact;


import java.util.List;
import java.util.Optional;

@Controller

public class ArtifactController {
    @Autowired
    ArtifactRepository ArtifactRepository;
    @Autowired
    MemberRepository MemberRepository;
    @Autowired
    main.java.ie.bibliotech.LoanRepository LoanRepository;


    @GetMapping("/")
    public String index() {
        return "startScreen.html";
    }
    @GetMapping("/librarianLogin")
    public String librarianLogin() {
        return "librarianLogin.html";
    }
    @GetMapping("/failure")
    public String failure(){ return "failure.html"; }
    @GetMapping("/libraryIndex")
    public String libraryIndex(){ return "librarianIndex.html"; }
    @GetMapping("/memberIndex")
    public String memberIndex(){ return "memberIndex.html"; }





    @PostMapping("/librarian-Index")
    public String librarianIndex(@RequestParam(name="user") String user,
                                 @RequestParam(name="password") String password,
                                 Model model){
        if(user.equals("Sam") || user.equals("Ed") || user.equals("Mo")) {
            model.addAttribute("user", user);
           return "librarianIndex.html";
        }
        return "failure.html";

    }
    @GetMapping("/browseArtifacts")
    public String browse(Model model) {
        model.addAttribute("artifacts", ArtifactRepository.findAll());
        return "browseArtifacts.html";
    }

    @GetMapping("/browseArtifactsMembers")
    public String browseArtifactsMembers(Model model) {
        model.addAttribute("artifacts", ArtifactRepository.findAll());
        return "browseArtifactsMembers.html";
    }

    @GetMapping("/search-Artifact")
    public String search(@RequestParam(name="search") String search, Model model) {
        System.out.println("SEARCH -> " + search);
        List<Artifact> artifacts = ArtifactRepository.findAll();
        Long id = Long.valueOf(0);
        for(int i = 0; i < artifacts.size(); i++){
            if(artifacts.get(i).getName().equals(search)) {
                id = artifacts.get(i).getId();
            }
        }
        Optional<Artifact> artifact = ArtifactRepository.findById(id);
        artifact.ifPresent(artifact1 -> model.addAttribute("artifact", artifact1));
        return "viewArtifacts.html";
    }

    @GetMapping("/viewArtifacts")
    public String viewA(@RequestParam(name = "id") Long id, Model model) {
        Optional<Artifact> artifact = ArtifactRepository.findById(id);
        artifact.ifPresent(artifact1 -> model.addAttribute("artifact", artifact1));
        return "viewArtifacts.html";
    }
    @GetMapping("/viewArtifactsMembers")
    public String viewM(@RequestParam(name = "id") Long id, Model model) {
        Optional<Artifact> artifact = ArtifactRepository.findById(id);
        artifact.ifPresent(artifact1 -> model.addAttribute("artifact", artifact1));
        return "viewArtifactsMembers.html";
    }

    @GetMapping("/create_artifact")
    public String create_artifact() {
        return "create_artifact.html";
    }

    @PostMapping("/createArtifact")
    public String createArtifact(@RequestParam(name = "author") String author,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "type") String type,
                                 @RequestParam(name = "genre") String genre,
                                 @RequestParam(name = "status") String status,
                                 Model model) {

        Artifact artifact = createAnArtifact(author, name, type, genre, status, false);
        if (!ArtifactRepository.exists(Example.of(artifact))) {
            ArtifactRepository.save(artifact);
        }

        return "redirect:/browseArtifacts";
    }

    @GetMapping("/removeArtifact")
    public String removeArtifact(@RequestParam(name = "id") Long id, Model model) {
        Optional<Artifact> artifact = ArtifactRepository.findById(id);
        if (artifact.isPresent()) {
            ArtifactRepository.deleteById(id);
        }
        return "redirect:/browseArtifacts";
    }



    @GetMapping("/renewArtifact")
    public String renewArtifact(){
        return "renewArtifact.html";
    }



    @GetMapping("/reserveArtifact")
    public String reserve(@RequestParam(name = "id") Long id,
                          Model model){
        Optional<Artifact> artifact = ArtifactRepository.findById(id);
        model.addAttribute("members", MemberRepository.findAll());
        model.addAttribute("artifacts", ArtifactRepository.findAll());
        if(artifact.isPresent()) {
            System.out.println(artifact.get().getName() + " reserved? " + artifact.get().getReserved().toString());
            if (!artifact.get().getReserved()) {
                artifact.get().setReserved(true);
                artifact.get().setStatus("Reserved");
                System.out.println(artifact.get().getName() + " reserved? " + artifact.get().getReserved().toString());
                return "reserveArtifact.html";
            }
            return "failure.html";
        }
        return "failure.html";
    }
    @GetMapping("/reserveSuccessful")
    public String reserveArtifactMember(@RequestParam(name = "id") Long id,  Model model){
        Optional<Artifact> artifact = ArtifactRepository.findById(id);
        if(artifact.isPresent()) {
            Artifact newArtifact = createAnArtifact(artifact.get().getAuthor(),
                    artifact.get().getName(),
                    artifact.get().getType(),
                    artifact.get().getType(),
                    "Reserved", true);
            artifact.ifPresent(artifact1 -> ArtifactRepository.deleteById(id));
            if (!ArtifactRepository.exists(Example.of(newArtifact))) {
                ArtifactRepository.save(newArtifact);
            }
        }
        return "redirect:/browseArtifacts";
    }

    private Artifact createAnArtifact(String author, String name, String type, String genre, String status, Boolean reserved) {
        Artifact artifact = new Artifact();
        artifact.setAuthor(author);
        artifact.setName(name);
        artifact.setType(type);
        artifact.setGenre(genre);
        artifact.setStatus(status);
        artifact.setReserved(reserved);
        return artifact;
    }

}
