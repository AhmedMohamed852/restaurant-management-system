package restaurant_management_system.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import restaurant_management_system.enums.CategoryName;
import restaurant_management_system.enums.RolesEnum;
import restaurant_management_system.model.Category;
import restaurant_management_system.model.Role;
import restaurant_management_system.repo.CategoryRepo;
import restaurant_management_system.repo.RoleRepo;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class DataSeeder implements ApplicationRunner {

    private final RoleRepo roles_Repo;
    private final CategoryRepo categoryRepo;
   // private final Interests_Repo interests_Repo;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        setRoles();
        setCategory();
       // setInterests();
    }

    private void setRoles()
    {
        List<String> roles = List.of("ADMIN","USER");

        roles.forEach(roleName ->
        {
            if(!roles_Repo.existsByCode(RolesEnum.valueOf(roleName)))
            {
                Role Role = new Role();
                Role.setCode(RolesEnum.valueOf(roleName));
                roles_Repo.save(Role);
                log.info("✅ Role saved",roleName);
            }else {
                log.info("⏩Role already exists");
            }
        });
    }




    private void setCategory()
    {
        List<Category> categories = List.of(
                new Category("fa fa-shopping-cart", CategoryName.ALL, "Popular"),
                new Category("fa fa-hamburger", CategoryName.FOODS, "Special"),
                new Category("fa fa-pizza-slice", CategoryName.FAST_FOOD, "Special"),
                new Category("fa fa-utensils", CategoryName.OTHER_FOODS, "Lovely"),
                new Category("fa fa-ice-cream", CategoryName.COLD_DRINKS, "Lovely"),
                new Category("fa fa-coffee", CategoryName.HOT_DRINKS, "Lovely"),
                new Category("fa fa-birthday-cake", CategoryName.SWEETS, "Lovely")
        );

        categories.forEach(category ->
        {
            if(!categoryRepo.existsByName(category.getName()))
            {
                categoryRepo.save(category);
                log.info("✅ Category saved: {}",category.getName());
            }else {
                log.info("⏩ Category already exists: {}" , category.getName());
            }
        });
    }

   /* private void setInterests() {
        List<InterestCategory> interests = List.of(InterestCategory.values());

        interests.forEach(interest -> {
            if (!interests_Repo.existsByCategory(interest)) {

                Interests newInterest = new Interests();
                newInterest.setCategory(interest);

                interests_Repo.save(newInterest);
                log.info("✅ Interest saved: {}", interest);

            } else {
                log.info("⏩ Interest already exists: {}", interest);
            }
        });
    }*/
}
