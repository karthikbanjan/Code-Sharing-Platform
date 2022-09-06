package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.model.CodeSnippet;
import platform.model.CodeSnippetService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ApiController {

    private final CodeSnippetService codeSnippetService;

    public ApiController(@Autowired CodeSnippetService codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<String> acceptNewCode(@RequestBody CodeSnippet newCodeSnippet) {
        codeSnippetService.save(newCodeSnippet);

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(String.format("{\"id\": \"%s\"}", newCodeSnippet.getUuid()));
    }

    @GetMapping("/api/code/{uuid}")
    public ResponseEntity<CodeSnippet> returnCodeById(@PathVariable UUID uuid) {
        Optional<CodeSnippet> codeSnippet = codeSnippetService.findByUuid(uuid);

        if (codeSnippet.isEmpty() || codeSnippetService.ifExpiredKill(codeSnippet.get())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found");
        }

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(codeSnippet.get());
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<CodeSnippet>> returnLatestCode() {
        List<CodeSnippet> codeSnippetList = codeSnippetService.findLatest();

        if (codeSnippetList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found");
        }

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(codeSnippetList);
    }

}