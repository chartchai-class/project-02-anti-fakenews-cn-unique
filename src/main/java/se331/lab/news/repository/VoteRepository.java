package se331.lab.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.news.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
