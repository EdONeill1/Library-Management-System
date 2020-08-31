package main.java.ie.bibliotech.repository;

import main.java.ie.bibliotech.model.Artifact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
}
