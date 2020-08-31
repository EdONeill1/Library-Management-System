package ie.bibliotech.controller;
import main.java.ie.bibliotech.repository.MemberRepository;
import main.java.ie.bibliotech.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.java.ie.bibliotech.model.Member;
import java.sql.Date;
import main.java.ie.bibliotech.repository.ArtifactRepository;
import main.java.ie.bibliotech.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import main.java.ie.bibliotech.model.Artifact;

import java.util.List;
import java.util.Optional;

@Controller


public class MainController {

    @Autowired
    MemberRepository MemberRepository;


}