package net.talaatharb.examplebackend.repositories;

import net.talaatharb.examplebackend.entities.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByIdAndUserId(Long id, UUID userId);

    Page<Todo> findAllByUserId(UUID userId, Pageable pageable);
}
