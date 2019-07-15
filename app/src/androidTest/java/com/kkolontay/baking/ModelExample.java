package com.kkolontay.baking;

import com.kkolontay.baking.model.BakeModel;
import com.kkolontay.baking.model.Ingredient;
import com.kkolontay.baking.model.Step;

import java.util.ArrayList;
import java.util.Arrays;

public class ModelExample {
    static public BakeModel getBakingForTest() {
        BakeModel model = new BakeModel();
        model.setId(1);
        model.setName("Nutella Pie");
        model.setServings(8);
        model.setImage("");
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(Arrays.asList(new Ingredient(2, "CUP", "Graham Cracker crumbs"),
                new Ingredient(6, "TBLSP", "unsalted butter, melted"),
                new Ingredient((float) 0.5, "CUP", "granulated sugar"),
                new Ingredient((float) 1.5, "TSP", "salt"),
                new Ingredient(5, "TBLSP", "vanilla"),
                new Ingredient(1, "K", "Nutella or other chocolate-hazelnut spread"),
                new Ingredient(500, "G", "Mascapone Cheese(room temperature)"),
                new Ingredient(1, "CUP", "heavy cream(cold)"),
                new Ingredient(4, "OZ", "cream cheese(softened")));
        model.setIngredients(ingredients);
        ArrayList<Step> steps = new ArrayList<>(Arrays.asList(
                new Step(0, "Recipe Introduction", "Recipe Introduction",
                        "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
                        ""),
                new Step(1, "Starting prep", "1. Preheat the oven to 350\u00b0F. Butter a 9\" deep dish pie pan.",
                        "",
                        ""),
                new Step(2, "Prep the cookie crust.", "2. Whisk the graham cracker crumbs, 50 grams (1/4 cup) of sugar, and 1/2 teaspoon of salt together in a medium bowl. Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients and stir together until evenly mixed.",
                        "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4",
                        ""),
                new Step(3, "Press the crust into baking form.", "3. Press the cookie crumb mixture into the prepared pie pan and bake for 12 minutes. Let crust cool to room temperature.",
                        "",
                        ""),
                new Step(4, "Start filling prep", "4. Beat together the nutella, mascarpone, 1 teaspoon of salt, and 1 tablespoon of vanilla on medium speed in a stand mixer or high speed with a hand mixer until fluffy.",
                        "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd97a_1-mix-marscapone-nutella-creampie/1-mix-marscapone-nutella-creampie.mp4",
                        ""),
                new Step(5, "Finish filling prep", "5. Beat the cream cheese and 50 grams (1/4 cup) of sugar on medium speed in a stand mixer or high speed with a hand mixer for 3 minutes. Decrease the speed to medium-low and gradually add in the cold cream. Add in 2 teaspoons of vanilla and beat until stiff peaks form.",
                        "",
                        "\"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffda20_7-add-cream-mix-creampie/7-add-cream-mix-creampie.mp4"),
                new Step(6, "Finishing Steps", "6. Pour the filling into the prepared crust and smooth the top. Spread the whipped cream over the filling. Refrigerate the pie for at least 2 hours. Then it's ready to serve!",
                        "\"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffda45_9-add-mixed-nutella-to-crust-creampie/9-add-mixed-nutella-to-crust-creampie.mp4",
                        "")
        ));
        model.setSteps(steps);

        return model;
    }
}

