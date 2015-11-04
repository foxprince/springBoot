package cn.anthony.boot.repository;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import cn.anthony.boot.doman.BaseEntity;

public interface BaseEntityRepository extends JpaRepository<BaseEntity, Long> {

    String findTitleById(Long id);

    @Async
    Future<BaseEntity> findById(Long id);

    @Query("SELECT t FROM BaseEntity t where t.title = :title AND t.description = :description")
    public List<BaseEntity> findByTitleAndDescription(@Param("title") String title, @Param("description") String description);

    @Query(value = "SELECT * FROM base_entity t where t.title = :title AND t.description = :description", nativeQuery = true)
    public List<BaseEntity> findByTitleAndDescriptionNative(@Param("title") String title, @Param("description") String description);
}
