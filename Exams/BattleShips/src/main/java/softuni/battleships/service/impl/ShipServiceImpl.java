package softuni.battleships.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.battleships.model.bindingModel.AddShipDTO;
import softuni.battleships.model.bindingModel.BattleDTO;
import softuni.battleships.model.entity.CategoryEntity;
import softuni.battleships.model.entity.ShipEntity;
import softuni.battleships.model.entity.UserEntity;
import softuni.battleships.model.viewModel.ShipViewModel;
import softuni.battleships.repository.ShipRepository;
import softuni.battleships.service.CategoryService;
import softuni.battleships.service.ShipService;
import softuni.battleships.service.UserService;
import softuni.battleships.util.CurrentUser;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;
    private final UserService userService;

    public ShipServiceImpl(ShipRepository shipRepository, ModelMapper modelMapper, CurrentUser currentUser, CategoryService categoryService, UserService userService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void addShip(AddShipDTO addShipDTO) {
        ShipEntity ship = modelMapper.map(addShipDTO, ShipEntity.class);
        CategoryEntity category = categoryService.findByName(addShipDTO.getCategory());
        UserEntity user = userService.findById(currentUser.getId());
        ship.setCategory(category)
                .setCreated(addShipDTO.getDate())
                .setUser(user);

        shipRepository.save(ship);
    }

    @Override
    public List<ShipViewModel> findAllShipsByUserId(Long id) {
        return shipRepository.findAllByUser_Id(id)
                .stream()
                .map(shipEntity -> modelMapper.map(shipEntity, ShipViewModel.class))
                .toList();

    }

    @Override
    public List<ShipViewModel> findAllShipsByUserIdIsNot(Long id) {
        return shipRepository.findAllByUser_IdIsNot(id)
                .stream()
                .map(shipEntity -> modelMapper.map(shipEntity, ShipViewModel.class))
                .toList();
    }

    @Override
    public List<ShipViewModel> findAllShips() {
        return shipRepository.findAllByoOrderByNameHealthPower()
                .stream()
                .map(shipEntity -> modelMapper.map(shipEntity, ShipViewModel.class))
                .toList();
    }

    @Override
    public void battle(BattleDTO battleDTO) {
        ShipEntity userShip = this.shipRepository.findById(battleDTO.getAttackerId()).orElse(null);
        ShipEntity enemyShip = this.shipRepository.findById(battleDTO.getDefenderId()).orElse(null);
        long healthEnemyShip = enemyShip.getHealth() - userShip.getPower();
        if (healthEnemyShip <= 0) {
            this.shipRepository.delete(enemyShip);
        } else {
            enemyShip.setHealth(healthEnemyShip);
            this.shipRepository.save(enemyShip);
        }
    }
}
