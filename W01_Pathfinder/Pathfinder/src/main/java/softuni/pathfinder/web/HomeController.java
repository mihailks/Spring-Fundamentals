package softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.pathfinder.service.PictureService;

@Controller
public class HomeController {
    private final PictureService pictureService;

    public HomeController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("pictures",
                pictureService.findAllUrls());
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
