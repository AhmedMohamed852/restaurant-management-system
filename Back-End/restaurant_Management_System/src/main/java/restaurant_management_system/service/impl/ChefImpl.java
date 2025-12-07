package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant_management_system.dto.ChefDto;
import restaurant_management_system.mapper.ChefMapper;
import restaurant_management_system.model.Chef;
import restaurant_management_system.repo.ChefRepo;
import restaurant_management_system.service.ChefService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChefImpl implements ChefService {


    private ChefRepo chefRepo;
    private ChefMapper chefMapper;

    @Autowired
    public ChefImpl(ChefRepo chefRepo, ChefMapper chefMapper) {
        this.chefRepo = chefRepo;
        this.chefMapper = chefMapper;
    }



//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO ____________________saveChef_________________________________
//TODO _____________________________________________________________
    @Override
    public ChefDto saveChef(ChefDto chefDto)
    {
        if(Objects.nonNull(chefDto.getId()))
        {
            throw new RuntimeException("id.Must.Be.Null");
        }

        Optional<Chef> optionalChefDto = chefRepo.findByName(chefDto.getName());
        if(optionalChefDto.isPresent())
        {
            throw new RuntimeException("Chef.Already.Exists");
        }

        return chefMapper.toDto(chefRepo.save(chefMapper.toEntity(chefDto)));
    }



//TODO ____________________getAllChefs_________________________________
//TODO _____________________________________________________________
    @Override
    public List<ChefDto> getAllChefs()
    {
        List<Chef> chefs = chefRepo.findAll();
        if(chefs.isEmpty())
        {
            throw new RuntimeException("No.Chefs.Found");
        }

        return chefMapper.toDtoList(chefs);
    }
}
