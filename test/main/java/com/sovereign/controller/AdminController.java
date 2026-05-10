import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.AdminService;
import org.springframework.boot.CarRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-service")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("/login")
    public boolean login(@RequestParam String email,
                         @RequestParam String password) {

        return service.checkUserLogin(email, password);
    }

    @PostMapping("/admin-showroom")
    public CarRepository addCar(@RequestBody CarRepository car) {
        return service.addCar(car);
    }

    @GetMapping("/car/status/{id}")
    public String getStatus(@PathVariable CarRepository id) {
        return service.getCarStatus(id);
    }
}
