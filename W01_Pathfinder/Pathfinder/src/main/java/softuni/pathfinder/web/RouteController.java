package softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.pathfinder.model.viewModel.RouteViewModel;
import softuni.pathfinder.service.RouteService;
import softuni.pathfinder.util.CurrentUser;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final CurrentUser currentUser;

    public RouteController(RouteService routeService, CurrentUser currentUser) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    @GetMapping("/all")
    public String route(Model model){
        List<RouteViewModel> routeViewModels = routeService
                .findAllRoutesView();

        model.addAttribute("routes", routeViewModels);

        return "routes";
    }

    @GetMapping("/detail/{id}")
    public String details(@PathVariable Long id, Model model){

        model.addAttribute("route", routeService.findRouteById(id));


        return "route-details";
    }



}
