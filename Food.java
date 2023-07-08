import java.util.Comparator;
import java.util.List;

public class Food {
    private String name;
    private List<String> ingredients;

    public Food(String name, List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}

//        List<String> cakeIngredients = new ArrayList<>();
//        cakeIngredients.add("eggs");
//        cakeIngredients.add("milk");
//        cakeIngredients.add("sugar");
//
//        Food cake = new Food("Cake", cakeIngredients);
//
//        List<String> soupIngredients = new ArrayList<>();
//        soupIngredients.add("water");
//        soupIngredients.add("salt");
//        soupIngredients.add("potatoes");
//
//        Food soup = new Food("Soup", soupIngredients);
//
//        ---------------Accessing food names and ingredients----------------
//        System.out.println("Food: " + cake.getName());
//        System.out.println("Ingredients: " + cake.getIngredients());
//
//        System.out.println("Food: " + soup.getName());
//        System.out.println("Ingredients: " + soup.getIngredients());


class PersonNameComparator implements Comparator<Food> {
    // Implement the compare() method from the Comparator interface
    @Override
    public int compare(Food food1, Food food2) {
        // Compare based on the name
        return food1.getName().compareTo(food2.getName());
    }
}
