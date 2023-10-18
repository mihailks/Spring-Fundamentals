package softuni.pathfinder.web;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.pathfinder.model.bindingModel.RouteAddBindingModel;
import softuni.pathfinder.model.serviceModel.RouteServiceModel;
import softuni.pathfinder.service.RouteService;
import softuni.pathfinder.util.CurrentUser;

import java.io.IOException;

@Controller
@RequestMapping("/routes")
public class AddRouteController {
    private final RouteService routeService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public AddRouteController(RouteService routeService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.routeService = routeService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String addRoute() {
        if (currentUser.getId() == null){
            return "redirect:users/login";
        }
        return "add-route";
    }

    @PostMapping("/add")
    public String addRoute(@Valid RouteAddBindingModel routeAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("routeAddBindingModel", routeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);
            return "redirect:add";
        }
        RouteServiceModel routeServiceModel = modelMapper.map(routeAddBindingModel, RouteServiceModel.class);
        routeServiceModel.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        routeService.addNewRoute(routeServiceModel);

        return "redirect:all";
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }
}
