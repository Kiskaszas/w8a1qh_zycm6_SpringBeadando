package org.example.kaszmaginnovate.controller;

import org.example.kaszmaginnovate.config.AuthConfiguration;
import org.example.kaszmaginnovate.model.Belepes;
import org.example.kaszmaginnovate.model.Meccs;
import org.example.kaszmaginnovate.model.Message;
import org.example.kaszmaginnovate.model.Nezo;
import org.example.kaszmaginnovate.service.BelepesService;
import org.example.kaszmaginnovate.service.MeccsService;
import org.example.kaszmaginnovate.service.MessageService;
import org.example.kaszmaginnovate.service.NezoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
public class HomeController {

    private final MeccsService meccsService;
    private final NezoService nezoService;
    private final AuthConfiguration auth;
    private final BelepesService belepesService;
    private final MessageService messageService;

    public HomeController(
            MeccsService meccsService,
            NezoService nezoService,
            AuthConfiguration auth,
            BelepesService belepesService,
            MessageService messageService) {
        this.meccsService = meccsService;
        this.nezoService = nezoService;
        this.auth = auth;
        this.belepesService = belepesService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("companyInfo", "KaszMag-Innovate");
        return "index";
    }

    @GetMapping("/meccsek")
    public String meccsek(
            final Model model,
            final AuthConfiguration aut,
            @RequestParam(defaultValue = "0") int page) {
        Page<Meccs> meccsPage = meccsService.getAllMeccs(PageRequest.of(page, 20));
        model.addAttribute("meccsek", meccsPage.getContent());
        model.addAttribute("meccs", new Meccs());
        model.addAttribute("totalPages", meccsPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "meccsek";
    }

    @GetMapping("/nezok")
    public String getNezok(
            Model model,
            final AuthConfiguration aut,
            @RequestParam(defaultValue = "0") int page) {
        Page<Nezo> nezokPage = nezoService.getAllNezo(PageRequest.of(page, 20));
        var nezo = new Nezo();
        model.addAttribute("nezok", nezokPage.getContent());
        model.addAttribute("nezo", nezo);
        model.addAttribute("ferfi", nezo.isFerfi());
        model.addAttribute("berletes", nezo.isBerletes());
        model.addAttribute("totalPages", nezokPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "nezok";
    }

    @GetMapping("/belepes")
    public String getBelepes(
            Model model,
            final AuthConfiguration auth,
            @RequestParam(defaultValue = "0") int page) {
        if (auth.isAdmin()) {
            Page<Belepes> belepesPage = belepesService.findAllBelepes(PageRequest.of(page, 20));
            model.addAttribute("belepesek", belepesPage.getContent());
            model.addAttribute("belepes", new Belepes());
            model.addAttribute("totalPages", belepesPage.getTotalPages());
            model.addAttribute("pageNumber", page);
            return "belepes";
        } else {
            return "error";
        }
    }

    @GetMapping("/uzenetek")
    private String uzenetek(
            Model model,
            final AuthConfiguration auth,
            @RequestParam(defaultValue = "0") int page) {
        if (auth.isAdmin()) {
            Page<Message> uzenetekPage = messageService.getAllMessage(PageRequest.of(page, 20));
            model.addAttribute("uzenetek", uzenetekPage.getContent());
            model.addAttribute("uzenet", new Message());
            model.addAttribute("totalPages", uzenetekPage.getTotalPages());
            model.addAttribute("pageNumber", page);
            return "uzenetek";
        } else {
            return "error";
        }
    }

   @GetMapping("/contact")
   public String contact() {
        return "contact";
   }

    @PostMapping("/contact/send-message")
    public String postContact(
            @RequestParam String sender,
            @RequestParam String content,
            Principal principal) {

        Message message = new Message();

        // Ha a felhasználó be van jelentkezve, a principal tartalmazza a felhasználónevet
        if (principal != null) {
            message.setSender(principal.getName());
            message.setRole("USER"); // Feltételezve, hogy minden bejelentkezett felhasználó "USER"
        } else {
            message.setSender(sender);
            message.setRole("LÁTOGATÓ");
        }

        message.setContent(content);
        message.setSentAt(LocalDateTime.now());

        messageService.save(message);
        return "redirect:/";
    }
}