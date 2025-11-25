package tacos.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.User;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.data.UserRepository;
import tacos.data.IngredientRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepo;
    private final IngredientRepository ingredientRepo;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(
            UserRepository userRepo,
            IngredientRepository ingredientRepo,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.ingredientRepo = ingredientRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // --- Ingredients (same as book) ---
        ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        ingredientRepo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        ingredientRepo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        ingredientRepo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
        ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        ingredientRepo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
        ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        // --- Default user ---
        if (userRepo.findByUsername("alex") == null) {
            userRepo.save(new User(
                    "alex",
                    passwordEncoder.encode("password"),
                    "Alexander Badenhorst",
                    "123 Street",
                    "Johannesburg",
                    "GP",
                    "1000",
                    "0123456789"
            ));
        }
    }
}
