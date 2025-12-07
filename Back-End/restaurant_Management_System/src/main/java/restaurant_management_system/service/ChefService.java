package restaurant_management_system.service;

import restaurant_management_system.dto.ChefDto;
import restaurant_management_system.model.Chef;

import java.util.List;

public interface ChefService {

        ChefDto saveChef(ChefDto chefDto);

        List<ChefDto> getAllChefs();
}
