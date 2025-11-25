package tacos.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.TacoOrder;
import tacos.Taco;
import tacos.User;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepo;
    private final UserRepository userRepo;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            TacoRepository tacoRepo,
            UserRepository userRepo) {

        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        for (Type type : Ingredient.Type.values()) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type)
            );
        }
    }

    @ModelAttribute(name = "order")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal) {
        if (principal == null) return null;
        return userRepo.findByUsername(principal.getName());
    }

    @GetMapping
    public String showDesignForm(Model model) {
        if (!model.containsAttribute("taco")) {
            model.addAttribute("taco", new Taco());
        }
        return "design";
    }

    @PostMapping
    @Transactional
    public String processTaco(
            @Valid @ModelAttribute("taco") Taco taco,
            Errors errors,
            @ModelAttribute("order") TacoOrder order,
            @ModelAttribute("user") User user) {

        if (errors.hasErrors()) {
            log.warn("Taco validation errors: {}", errors);
            return "design";
        }

        // Attach user once per session-order
        if (order.getUser() == null && user != null) {
            order.setUser(user);
        }

        // IMPORTANT:
        // Taco belongs to NO ORDER until /orders (checkout) stage
        taco.setTacoOrder(null);

        // Save the taco immediately
        Taco saved = tacoRepo.save(taco);

        // Attach to session-scoped order
        order.addTaco(saved);

        log.info("🌮 Saved taco '{}' with {} ingredients",
                saved.getName(),
                saved.getIngredients().size());

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
