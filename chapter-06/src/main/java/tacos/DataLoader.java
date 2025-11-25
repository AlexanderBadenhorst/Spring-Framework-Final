package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tacos.data.IngredientRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final IngredientRepository ingredientRepo;

    public DataLoader(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));

        ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        ingredientRepo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));

        ingredientRepo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientRepo.save(new Ingredient("JACK", "Monterey Jack", Ingredient.Type.CHEESE));

        ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientRepo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));

        ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        ingredientRepo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
    }
}
