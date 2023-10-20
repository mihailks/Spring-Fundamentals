package softuni.battleships.service;

import softuni.battleships.model.bindingModel.AddShipDTO;
import softuni.battleships.model.bindingModel.BattleDTO;
import softuni.battleships.model.viewModel.ShipViewModel;

import java.util.List;

public interface ShipService {
    void addShip(AddShipDTO addShipDTO);

    List<ShipViewModel> findAllShipsByUserId(Long id);

    List<ShipViewModel> findAllShipsByUserIdIsNot(Long id);

    List<ShipViewModel> findAllShips();

    void battle(BattleDTO battleDTO);
}
