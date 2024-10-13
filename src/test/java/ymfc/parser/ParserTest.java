package ymfc.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ymfc.commands.AddRecipeCommand;
import ymfc.exception.InvalidArgumentException;
import ymfc.recipe.Recipe;
import ymfc.recipelist.RecipeList;
import ymfc.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static ymfc.parser.Parser.parseCommand;

class ParserTest {

    static Stream<Arguments> parseCommand_addRecipeCommandSuccess_data() {
        return Stream.of(
                arguments( // Universal test #1
                        new Recipe("Ramen Eggs",
                                new ArrayList<>(Arrays.asList("eggs", "soya sauce", "water")),
                                new ArrayList<>(Arrays.asList("boil eggs for 6.5 min", "cool eggs in ice bath"))
                        ), "add n/Ramen Eggs" +
                                " i/eggs i/soya sauce i/water" +
                                " s1/boil eggs for 6.5 min s2/cool eggs in ice bath"
                ),

                arguments( // Universal test #2
                        new Recipe("Grilled Cheese Sandwhich",
                                new ArrayList<>(Arrays.asList("bread", "cheese slice", "butter")),
                                new ArrayList<>(Arrays.asList("heat pan with butter",
                                        "grill bread on pan, and add cheese on top",
                                        "remove from grill after 3 minutes"))
                        ), "add n/Grilled Cheese Sandwhich" +
                                " i/bread i/cheese slice i/butter" +
                                " s1/heat pan with butter" +
                                " s2/grill bread on pan, and add cheese on top" +
                                " s3/remove from grill after 3 minutes"
                ),

                arguments( // Ingredients with numbers
                        new Recipe("Spicy Wings",
                                new ArrayList<>(Arrays.asList("chicken wings 5pcs", "5 tbsp hot sauce", "butter")),
                                new ArrayList<>(Arrays.asList("mix ingredients"))
                        ),
                        "add n/Spicy Wings i/chicken wings 5pcs i/5 tbsp hot sauce i/butter s1/mix ingredients"
                ),

                arguments( // Steps with punctuation
                        new Recipe("Baked Fish",
                                new ArrayList<>(Arrays.asList("fish fillet", "lemon")),
                                new ArrayList<>(Arrays.asList("season fish, then bake.", "serve hot."))
                        ),
                        "add n/Baked Fish i/fish fillet i/lemon s1/season fish, then bake. s2/serve hot."
                ),

                arguments( // Ingredients with punctuation
                        new Recipe("Mixed Veggies",
                                new ArrayList<>(Arrays.asList("carrots, chopped", "broccoli", "salt")),
                                new ArrayList<>(Arrays.asList("cook veggies"))
                        ),
                        "add n/Mixed Veggies i/carrots, chopped i/broccoli i/salt s1/cook veggies"
                ),

                arguments( // Special characters in the name
                        new Recipe("Fish & Chips",
                                new ArrayList<>(Arrays.asList("fish", "potatoes")),
                                new ArrayList<>(Arrays.asList("fry fish and chips"))
                        ),
                        "add n/Fish & Chips i/fish i/potatoes s1/fry fish and chips"
                )
        );
    }

    @ParameterizedTest
    @DisplayName("parseCommand_addRecipeCommand_success")
    @MethodSource("parseCommand_addRecipeCommandSuccess_data")
    void parseCommand_addRecipeCommand_success(Recipe recipe, String command) {
        RecipeList rList = new RecipeList();
        Ui ui = new Ui(System.in);
        try {
            AddRecipeCommand addCommand = (AddRecipeCommand) parseCommand(command);
            assert addCommand != null;
            addCommand.execute(rList, ui);
            assertTrue(recipe.equals(rList.getRecipe(rList.getCounter() - 1)));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_addRecipeCommand_invalidArgumentsExceptionThrown")
    @ValueSource(strings = {
        "add n/Plain Water",                                                            // Missing ingreds and steps
        "add n/Salad i/lettuce i/tomato i/cucumber",                                    // Missing steps
        "add n/Walking s1/start walking s2/keep walking",                               // Missing ingreds
        "add i/eggs i/milk s1/whisk together",                                          // Missing name
        "add n/Toast bread butter s1/spread butter on bread",                           // Missing i/
        "add n/Smoothie i/banana i/yogurt blend all ingredients",                       // Missing s/
        // "add n/Pizza i/dough i/cheese s1/make dough s1/bake pizza",                     // Repeated step numbers
        // "add n/Sandwich i/bread i/ham i/cheese s1/spread butter s3/grill sandwich",     // Steps not in order
        "add n/Soup i/ i/onion s1/cook onions s2/",                                     // Empty ingredient or step
        "add n/  Porridge  i/ oats  i/milk s1 / cook oats",                             // Whitespace before slash
        "add nPorridge i oats i milk s1 cook oats",                                     // No slashes
        "add n//Omelette i/eggs// s1/fry/",                                             // Too many slashes
        "add n/RamenEggs i/eggsi/soya sauce i/water s1/boil eggss2/eggs in ice bath"    // Missing spaces
    })
    void parseCommand_addRecipeCommand_invalidArgumentsExceptionThrown(String command) {
        assertThrows(InvalidArgumentException.class, () -> parseCommand(command));
    }


}
