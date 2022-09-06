package platform.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.repository.CodeSnippetRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CodeSnippetService {

    private final CodeSnippetRepository codeSnippetRepository;

    public CodeSnippetService(@Autowired CodeSnippetRepository codeSnippetRepository) {
        this.codeSnippetRepository = codeSnippetRepository;
    }

    public void save(CodeSnippet toSave) {
        toSave.setUuid(UUID.randomUUID());
        toSave.setTitle("Code");
        toSave.setDate(Instant.now());
        toSave.setViews(0L);
        if (toSave.getTimeLimit() > 0) {
            toSave.setTimeRestricted('y');
        } else {
            toSave.setTimeRestricted('n');
        }
        if (toSave.getViewLimit() > 0) {
            toSave.setViewRestricted('y');
        } else {
            toSave.setViewRestricted('n');
        }
        System.out.println(toSave);
        codeSnippetRepository.save(toSave);
    }

    public Optional<CodeSnippet> findByUuid(UUID uuid) {
        return codeSnippetRepository.findByUuid(uuid);
    }

    public List<CodeSnippet> findLatest() {
        return codeSnippetRepository
                .findByViewRestrictedAndTimeRestrictedOrderByDateDesc('n', 'n')
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    public boolean ifExpiredKill(CodeSnippet codeSnippet) {
        codeSnippet.setViews(codeSnippet.getViews() + 1);
        codeSnippetRepository.save(codeSnippet);

        if (codeSnippet.getTimeRestricted().equals('y')) {
            if (Instant.now().getEpochSecond() - codeSnippet.getDate().getEpochSecond() > codeSnippet.getTimeLimit()) {
                codeSnippetRepository.delete(codeSnippet);
                return true;
            } else {
                codeSnippet.setTimeLimit(
                                codeSnippet.getTimeLimit() -
                                        (Instant.now().getEpochSecond() - codeSnippet.getDate().getEpochSecond()));
            }
        }

        if (codeSnippet.getViewRestricted().equals('y')) {
            if (codeSnippet.getViews() > codeSnippet.getViewLimit()) {
                codeSnippetRepository.delete(codeSnippet);
                return true;
            } else {
                codeSnippet.setViewLimit(codeSnippet.getViewLimit() - codeSnippet.getViews());
            }
        }

        return false;
    }

}
