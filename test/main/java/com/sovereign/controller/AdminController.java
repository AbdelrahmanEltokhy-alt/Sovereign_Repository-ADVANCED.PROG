import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.AdminService;
import org.springframework.boot.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin-service")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("/login")
    String ViewLoginForm()
    {
    	return "login" ;
    }

    @PostMapping("/admin-showroom")
   String AdminShowRoom()
   {
    	return "admin-showroom" ;
   }
    @GetMapping("/car/status/{id}")
    String CarStatus()
{
	 return"car-status";
}
   }
