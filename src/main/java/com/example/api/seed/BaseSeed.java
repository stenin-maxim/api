package com.example.api.seed;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.api.enums.RoleEnum;
import com.example.api.model.Category;
import com.example.api.model.Role;
import com.example.api.repository.CategoryRepository;
import com.example.api.repository.RoleRepository;

@Component
public class BaseSeed implements CommandLineRunner {
    @Autowired
	CategoryRepository categoryRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
	public void run(String... args) throws Exception {
		loadCategoryData();
        loadRoleData();
	}

    private void loadRoleData() {
        System.out.println(roleRepository);
        if (roleRepository.count() == 0) {
            Role roleUser = new Role(RoleEnum.ROLE_USER);
            Role roleAdmin = new Role(RoleEnum.ROLE_ADMIN);
            Role roleModerator = new Role(RoleEnum.ROLE_MODERATOR);

            roleRepository.save(roleUser);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleModerator);
        }
    }

    private void loadCategoryData() {
        if (categoryRepository.count() == 0) {
            ArrayList<Category> categories = new ArrayList<>();

            categories.add(new Category(0, "Личные вещи"));
            categories.add(new Category(1, "Одежда, обувь, аксессуары"));
            categories.add(new Category(1, "Детская одежда и обувь"));
            categories.add(new Category(1, "Товары для детей и игрушки"));
            categories.add(new Category(1, "Красота и здоровья"));
            categories.add(new Category(1, "Часы и украшения"));

            categories.add(new Category(2, "Женская одежда"));
            categories.add(new Category(2, "Женская обувь"));
            categories.add(new Category(2, "Мужская одежда"));
            categories.add(new Category(2, "Мужская обувь"));
            categories.add(new Category(2, "Сумки, рюкзаки и чемоданы"));
            categories.add(new Category(2, "Аксессуары"));

            categories.add(new Category(3, "Для девочек"));
            categories.add(new Category(3, "Для мальчиков"));

            categories.add(new Category(4, "Детские коляски"));
            categories.add(new Category(4, "Детская мебель"));
            categories.add(new Category(4, "Велосипеды и самокаты"));
            categories.add(new Category(4, "Товары для кормления"));
            categories.add(new Category(4, "Автомобильные кресла"));
            categories.add(new Category(4, "Игрушки"));
            categories.add(new Category(4, "Постельные принадлежности"));
            categories.add(new Category(4, "Товары для купания"));
            categories.add(new Category(4, "Товары для школы"));
            
            categories.add(new Category(4, "Приборы и аксессуары"));
            categories.add(new Category(4, "Парфюмерия"));
            categories.add(new Category(4, "Средства гигиены"));
            categories.add(new Category(4, "Косметика"));
            categories.add(new Category(4, "Средства для волос"));
            categories.add(new Category(4, "Медицинские изделия"));

            categories.add(new Category(4, "Ювелирные изделия"));
            categories.add(new Category(4, "Часы"));
            categories.add(new Category(4, "Бижутерия"));

            categories.add(new Category(7, "Верхняя одежда"));
            categories.add(new Category(7, "Платья"));
            categories.add(new Category(7, "Юбки"));
            categories.add(new Category(7, "Толстовки и свитшоты"));
            categories.add(new Category(7, "Джемперы, свитеры, кардиганы"));
            categories.add(new Category(7, "Пиджаки и костюмы"));
            categories.add(new Category(7, "Брюки"));
            categories.add(new Category(7, "Джинсы"));
            categories.add(new Category(7, "Нижнее бельё"));
            categories.add(new Category(7, "Свадебные платья"));
            categories.add(new Category(7, "Рубашки и блузки"));
            categories.add(new Category(7, "Топы и футболки"));
            categories.add(new Category(7, "Купальники"));
            categories.add(new Category(7, "Другое"));

            categories.add(new Category(8, "Туфли"));
            categories.add(new Category(8, "Сапоги"));
            categories.add(new Category(8, "Босоножки"));
            categories.add(new Category(8, "Ботильоны"));
            categories.add(new Category(8, "Ботинки и полуботинки"));
        
            for (Category category: categories) {
                categoryRepository.save(category);
            }
        }
    }
}