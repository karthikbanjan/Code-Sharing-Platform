package platform.repository;

import org.springframework.data.repository.CrudRepository;
import platform.model.CodeSnippet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CodeSnippetRepository extends CrudRepository<CodeSnippet, Long> {

    Optional<CodeSnippet> findByUuid(UUID uuid);

    List<CodeSnippet> findByViewRestrictedAndTimeRestrictedOrderByDateDesc(Character viewRestricted, Character timeRestricted);

}