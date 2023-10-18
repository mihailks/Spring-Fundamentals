package softuni.pathfinder.service;

import softuni.pathfinder.model.serviceModel.RouteServiceModel;
import softuni.pathfinder.model.viewModel.RouteDetailsViewModel;
import softuni.pathfinder.model.viewModel.RouteViewModel;
import softuni.pathfinder.repository.RouteRepository;

import java.util.List;

public interface RouteService {

    List<RouteViewModel> findAllRoutesView();

    void addNewRoute(RouteServiceModel routeServiceModel);

    RouteDetailsViewModel findRouteById(Long id);
}
