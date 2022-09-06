package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import platform.model.CodeSnippet;
import platform.model.CodeSnippetService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
public class ViewController {

    private final CodeSnippetService codeSnippetService;

    public ViewController(@Autowired CodeSnippetService codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/code/new")
    public String acceptNewCode() {
        return "NewSnippet";
    }

    @GetMapping("/code/{uuid}")
    public String returnCode(Model model, @PathVariable UUID uuid) {
        Optional<CodeSnippet> codeSnippet = codeSnippetService.findByUuid(uuid);

        if (codeSnippet.isEmpty() || codeSnippetService.ifExpiredKill(codeSnippet.get())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found");
        }

        model.addAttribute("title", codeSnippet.get().getTitle());
        model.addAttribute("date", codeSnippet.get().getStringDate());
        model.addAttribute("code", codeSnippet.get().getCode());

        if (codeSnippet.get().getTimeRestricted().equals('y')) {
            model.addAttribute("time_restriction", codeSnippet.get().getTimeLimit());
        }
        if (codeSnippet.get().getViewRestricted().equals('y')) {
            model.addAttribute("views_restriction", codeSnippet.get().getViewLimit());
        }

        return "Snippet";
    }

    @GetMapping("/code/latest")
    public String returnLatestCodes(Model model) {
        List<CodeSnippet> codeSnippetList = codeSnippetService.findLatest();

        if (codeSnippetList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found");
        }

        model.addAttribute("codeSnippets", codeSnippetList);

        return "SnippetFeed";
    }

}