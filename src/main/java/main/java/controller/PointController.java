package main.java.controller;
import main.java.PointService;
import main.java.repositories.PointRepository;
import main.java.repositories.UserRepository;
import main.java.model.Calculator;
import main.java.model.Point;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

    @RestController
    public class PointController {
        private final PointRepository pointRepository;
        private final UserRepository userRepository;
        private final Calculator calculator;
        private final PointService pointService;
        PointController(PointRepository pointRepository, UserRepository userRepository, PointService pointService) {
            this.pointRepository = pointRepository;
            this.calculator = new Calculator();
            this.userRepository = userRepository;
            this.pointService = pointService;
        }

        @CrossOrigin
        @GetMapping("/points")
        Collection<Point> allPoints(Principal user) {
            System.out.println("all points request from "+user.getName());
            return pointRepository.findAllUserPoints(userRepository.findByUsername(user.getName()));
        }

        @CrossOrigin
        @PostMapping("/points")
        Point newPoint(@RequestBody Point newPoint, Principal user) {
            if (newPoint.getR()<0) return null;
            newPoint.setInArea(calculator.isInArea(newPoint));
            System.out.println(user);
            pointService.refresh(newPoint);
            newPoint.setUser(userRepository.findByUsername(user.getName()));
            return pointRepository.save(newPoint);
        }


        @CrossOrigin
        @GetMapping("/points/{r}")
        Collection<Point> allPointsRecalculation(@PathVariable Double r, Principal user) {
            List<Point> recalculated = new ArrayList<>();
            Collection<Point> points = pointRepository.findAllUserPoints(userRepository.findByUsername(user.getName()));
            System.out.println(user);
            for (Point p : points) {
                Point point = new Point(p.getX(), p.getY(), r, false);
                point.setInArea(calculator.isInArea(point));
                recalculated.add(point);
            }
            pointService.calculateSquare(r);
            return recalculated;
        }
}
