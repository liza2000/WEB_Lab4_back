package main.java.repositories;

import main.java.model.Point;
import main.java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("SELECT u FROM Point u WHERE u.user = ?1")
    Collection<Point> findAllUserPoints(User user);
}
