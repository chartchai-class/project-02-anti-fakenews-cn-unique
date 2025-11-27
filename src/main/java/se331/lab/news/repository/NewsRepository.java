package se331.lab.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.news.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
