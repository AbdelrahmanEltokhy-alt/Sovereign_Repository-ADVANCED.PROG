

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.AdminService;
import org.springframework.boot.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin-service")
public class AdminController {
        
	  
	@Autowired
    private AdminService  service;
	
	
	@PostMapping("/login")
	public String login(@RequestParam String email,
	                    @RequestParam String password) {

	    return service.checkUserRole(email, password);
	}

	@PostMapping("/admin-showroom")
	public CarRepository addCar(@RequestParam CarRepository car) {
	    return service.addCar(car);
	}
	
	@GetMapping("/car/status/{id}")
	public String getStatus(@PathVariable long id) {
	    return service.getCarStatus(id);
	}
	
	
}
