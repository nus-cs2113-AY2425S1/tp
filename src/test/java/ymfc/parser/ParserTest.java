package ymfc.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ymfc.commands.AddIngredientCommand;
import ymfc.commands.AddCommand;
import ymfc.commands.ByeCommand;
import ymfc.commands.DeleteCommand;
import ymfc.commands.DeleteIngredientCommand;
import ymfc.commands.EditCommand;
import ymfc.commands.FindCommand;
import ymfc.commands.FindIngredientCommand;
import ymfc.commands.HelpCommand;
import ymfc.commands.ListCommand;
import ymfc.commands.ListIngredientCommand;
import ymfc.commands.RandomCommand;
import ymfc.commands.RecommendCommand;
import ymfc.commands.SortCommand;
import ymfc.exception.InvalidArgumentException;
import ymfc.exception.EmptyListException;
import ymfc.exception.InvalidCommandException;
import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static ymfc.parser.Parser.parseCommand;

class ParserTest {

    RecipeList recipes = new RecipeList();
    IngredientList ingredients = new IngredientList();
    ArrayList<Ingredient> pastaIngredients = new ArrayList<>();
    @BeforeEach
    void setUp() {
        recipes = new RecipeList();
        ingredients = new IngredientList();
        ingredients.addIngredient(new Ingredient("Pasta"));
        ingredients.addIngredient(new Ingredient("Tomato"));

        pastaIngredients = new ArrayList<>();
        pastaIngredients.add(new Ingredient("Pasta"));
        ArrayList<String> pastaSteps = new ArrayList<>();
        pastaSteps.add("Boil pasta in water.");

        Recipe pastaRecipe = new Recipe("Pasta", pastaIngredients, pastaSteps, 2);
        recipes.addRecipe(pastaRecipe);
    }

    static Stream<Arguments> parseCommand_addRecipeCommandSuccess_data() {
        return Stream.of(
                arguments( // Universal test #1
                        new Recipe("Ramen Eggs",
                                new ArrayList<>(Arrays.asList(
                                        new Ingredient("eggs"),
                                        new Ingredient("soya sauce"),
                                        new Ingredient("water")
                                )),
                                new ArrayList<>(Arrays.asList("boil eggs for 6.5 min", "cool eggs in ice bath"))
                        ), "add n/Ramen Eggs" +
                                " i/eggs i/soya sauce i/water" +
                                " s1/boil eggs for 6.5 min s2/cool eggs in ice bath"
                ),

                arguments( // Universal test #2
                        new Recipe("Grilled Cheese Sandwhich",
                                new ArrayList<>(Arrays.asList(
                                        new Ingredient("bread"),
                                        new Ingredient("cheese slice"),
                                        new Ingredient("butter")
                                )),
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
                                new ArrayList<>(Arrays.asList(
                                        new Ingredient("chicken wings 5pcs"),
                                        new Ingredient("5 tbsp hot sauce"),
                                        new Ingredient("butter")
                                )),
                                new ArrayList<>(Arrays.asList("mix ingredients"))
                        ),
                        "add n/Spicy Wings i/chicken wings 5pcs i/5 tbsp hot sauce i/butter s1/mix ingredients"
                ),

                arguments( // Steps with punctuation
                        new Recipe("Baked Fish",
                                new ArrayList<>(Arrays.asList(
                                        new Ingredient("fish fillet"),
                                        new Ingredient("lemon")
                                )),
                                new ArrayList<>(Arrays.asList("season fish, then bake.", "serve hot."))
                        ),
                        "add n/Baked Fish i/fish fillet i/lemon s1/season fish, then bake. s2/serve hot."
                ),

                arguments( // Ingredients with punctuation
                        new Recipe("Mixed Veggies",
                                new ArrayList<>(Arrays.asList(
                                        new Ingredient("carrots, chopped"),
                                        new Ingredient("broccoli"),
                                        new Ingredient("salt")
                                )),
                                new ArrayList<>(Arrays.asList("cook veggies"))
                        ),
                        "add n/Mixed Veggies i/carrots, chopped i/broccoli i/salt s1/cook veggies"
                ),

                arguments( // Special characters in the name
                        new Recipe("Fish & Chips",
                                new ArrayList<>(Arrays.asList(
                                        new Ingredient("fish"),
                                        new Ingredient("potatoes")
                                )),
                                new ArrayList<>(Arrays.asList("fry fish and chips"))
                        ),
                        "add n/Fish & Chips i/fish i/potatoes s1/fry fish and chips"
                ),

                arguments( // Recipe with cuisine and time
                        new Recipe("Spicy Wings",
                                new ArrayList<>(Arrays.asList(
                                        new Ingredient("chicken wings 5pcs"),
                                        new Ingredient("5 tbsp hot sauce"),
                                        new Ingredient("butter")
                                )),
                                new ArrayList<>(Arrays.asList("mix ingredients")),
                                "Asian", 15
                        ),
                        "add n/Spicy Wings i/chicken wings 5pcs i/5 tbsp hot sauce i/butter" +
                                " s1/mix ingredients c/Asian t/15"
                ),

                arguments( // Recipe with cuisine
                        new Recipe("Niku udon",
                                new ArrayList<>(Arrays.asList(
                                        new Ingredient("udon noodle"),
                                        new Ingredient("bonito flakes")
                                )),
                                new ArrayList<>(Arrays.asList("add bonito flakes to boiling water",
                                        "boil for 12 minutes", "add cooked noodles and serve")),
                                "Japanese"
                        ),
                        "add n/Niku udon i/udon noodle i/bonito flakes s1/add bonito flakes to boiling water" +
                                " s2/boil for 12 minutes s3/add cooked noodles and serve c/Japanese"
                ),

                arguments( // Recipe with time
                        new Recipe("Carbonara",
                                new ArrayList<>(Arrays.asList(
                                        new Ingredient("spaghetti"),
                                        new Ingredient("eggs"),
                                        new Ingredient("parmesan cheese"),
                                        new Ingredient("pancetta")
                                )),
                                new ArrayList<>(Arrays.asList("boil spaghetti",
                                        "whisk eggs and cheese together",
                                        "fry pancetta until crispy",
                                        "mix spaghetti with pancetta and egg mixture")),
                                null, // No cuisine provided
                                20 // Example time in minutes
                        ),
                        "add n/Carbonara i/spaghetti i/eggs i/parmesan cheese i/pancetta " +
                                "s1/boil spaghetti s2/whisk eggs and cheese together " +
                                "s3/fry pancetta until crispy s4/mix spaghetti with pancetta and egg mixture t/20"
                )
        );
    }

    @ParameterizedTest
    @DisplayName("parseCommand_addRecipeCommand_success")
    @MethodSource("parseCommand_addRecipeCommandSuccess_data")
    void parseCommand_addRecipeCommand_success(Recipe recipe, String command) {
        RecipeList rList = new RecipeList();
        IngredientList iList = new IngredientList();
        Ui ui = new Ui(System.in);
        try {
            AddCommand addCommand = (AddCommand) parseCommand(command, rList, iList);
            assert addCommand != null;
            addCommand.execute(rList, iList, ui, new Storage());
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
        "add n/Pizza i/dough i/cheese s1/make dough s1/bake pizza",                     // Repeated step numbers
        "add n/Pizza i/dough i/cheese s1/make dough s/bake pizza",                     // Missing step numbers
        "add n/Sandwich i/bread i/ham i/cheese s1/spread butter s3/grill sandwich",     // Steps not in order
        "add n/Soup i/ i/onion s1/cook onions s2/",                                     // Empty ingredient or step
        "add n/  Porridge  i/ oats  i/milk s1 / cook oats",                             // Whitespace before slash
        "add nPorridge i oats i milk s1 cook oats",                                     // No slashes
        "add n//Omelette i/eggs// s1/fry/",                                             // Too many slashes
        "add n/RamenEggs i/eggsi/soya sauce i/water s1/boil eggss2/eggs in ice bath",    // Missing spaces

        // Test cases for invalid cuisine and time
        "add n/Spicy Wings i/chicken wings 5pcs c/Asian",                               // Missing steps and time
        "add n/Grilled Vegetable Salad i/zucchini i/bell pepper c/Mediterranean t/10",  // Missing steps
        "add n/Spicy Wings c/Asian t/15",                                               // Missing ingredients and steps
        "add n/Salad i/lettuce i/tomato c/Healthy",                                     // Missing steps
        "add n/Pasta i/pasta i/sauce s1/cook pasta c/Italian t/abc",                    // Invalid time format
        "add n/Smoothie i/banana i/yogurt blend all ingredients c/Fruit t/5",           // Missing steps
        "add n/Breakfast c/English",                                                    // Missing ingredients and steps
        "add n/Omelette i/eggs c/Breakfast s1/fry t/-10",                               // Invalid time (negative)
        "add n/Pancakes i/flour i/milk s1/mix c/Breakfast t/",                          // Missing time
        "add n/Sandwich i/bread i/ham s1/spread butter c/Quick t/0"                     // Invalid time (zero)

    })
    void parseCommand_addRecipeCommand_invalidArgumentsExceptionThrown(String command) {
        assertThrows(InvalidArgumentException.class, () -> parseCommand(command, new RecipeList(),
                new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_findCommand_invalidArgumentsExceptionThrown")
    @ValueSource(strings = {
        "find nNIsS/query",     // Invalid options (more than 3 characters)
        "find nX/query",        // Invalid options (contains an invalid character)
        "find nNi/",            // Invalid query (empty query after `/`)
        "find nNi/foo/bar",     // Invalid query (contains `/` in query part)
        "find nNi/Pasta",       // Valid query but invalid options (duplicate options)
        "find nIi/Pasta",       // Valid query but invalid options (duplicate options)
        "find sSn/Pasta",       // Valid query but invalid options (duplicate options)
        "find /foo",            // Invalid query (no options, but query starts with `/`)
        "find ni/"             // Missing query (no query provided after `/`)
    })
    void parseCommand_findCommand_invalidArgumentsExceptionThrown(String command) {
        assertThrows(InvalidArgumentException.class, () -> parseCommand(command, recipes, ingredients));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_findCommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "find i/query"     // Valid command but empty list
    })
    void parseCommand_findCommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_findCommand_success")
    @ValueSource(strings = {
        "find nsi/Pasta",       // Find with full options
        "find Pasta",           // Find with default option
        "find n/Pasta",        // Find in name
        "find i/Pasta",        // Find in ingredient
        "find s/Pasta",        // Find in step
        "find ni/Pasta",        // Find in name and ingredient
        "find si/Pasta",        // Find in step and ingredient
        "find ns/Pasta",        // Find in name and step
    })
    void parseCommand_findCommand_success(String command) {
        try {
            assertInstanceOf(FindCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_deleteCommand_success")
    @ValueSource(strings = {
        "delete n/Pasta"
    })
    void parseCommand_deleteCommand_success(String command) {
        try {
            assertInstanceOf(DeleteCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_deleteCommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "delete n/query"     // Valid command but empty list
    })
    void parseCommand_deleteCommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_deleteCommand_invalidArgumentExceptionThrown")
    @ValueSource(strings = {
        "delete n/"     // No recipe name
    })
    void parseCommand_deleteCommand_invalidArgumentExceptionThrown(String command) {
        assertThrows(InvalidArgumentException.class, () -> parseCommand(command, recipes, ingredients));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_deleteIngredientCommand_success")
    @ValueSource(strings = {
        "deleteI n/Pasta"
    })
    void parseCommand_deleteIngredientCommand_success(String command) {
        try {
            assertInstanceOf(DeleteIngredientCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_deleteIngredientCommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "deleteI n/query"     // Valid command but empty list
    })
    void parseCommand_deleteIngredientCommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_deleteIngredientCommand_invalidArgumentExceptionThrown")
    @ValueSource(strings = {
        "deleteI n/"     // No ingredient name
    })
    void parseCommand_deleteIngredientCommand_invalidArgumentExceptionThrown(String command) {
        assertThrows(InvalidArgumentException.class, () -> parseCommand(command, recipes, ingredients));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_listCommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "list"     // Valid command but empty list
    })
    void parseCommand_listCommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_listCommand_success")
    @ValueSource(strings = {
        "list"
    })
    void parseCommand_listCommand_success(String command) {
        try {
            assertInstanceOf(ListCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_listICommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "listI"     // Valid command but empty list
    })
    void parseCommand_listICommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_listICommand_success")
    @ValueSource(strings = {
        "listI"
    })
    void parseCommand_listICommand_success(String command) {
        try {
            assertInstanceOf(ListIngredientCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    @DisplayName("parseCommand_helpCommand_success")
    void parseCommand_helpCommand_success() {
        try {
            assertInstanceOf(HelpCommand.class, parseCommand("help", recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    @DisplayName("parseCommand_byeCommand_success")
    void parseCommand_byeCommand_success() {
        try {
            assertInstanceOf(ByeCommand.class, parseCommand("bye", recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_sortCommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "sort s/name",     // Valid command but empty list
        "sort s/time"      // Valid command but empty list
    })
    void parseCommand_sortCommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_sortCommand_invalidArgumentExceptionThrown")
    @ValueSource(strings = {
        "sort s/social credit",     // Invalid sorting argument
        "sort s/"                   // No sorting option
    })
    void parseCommand_sortCommand_invalidArgumentExceptionThrown(String command) {
        assertThrows(InvalidArgumentException.class, () -> parseCommand(command, recipes, ingredients));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_sortCommand_success")
    @ValueSource(strings = {
        "sort s/name",     // Valid command but empty list
        "sort s/time"      // Valid command but empty list
    })
    void parseCommand_sortCommand_success(String command) {
        try {
            assertInstanceOf(SortCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_addIngredientCommand_success")
    @ValueSource(strings = {
        "new n/Potato"
    })
    void parseCommand_addIngredientCommand_success(String command) {
        try {
            assertInstanceOf(AddIngredientCommand.class, parseCommand(command, new RecipeList(), new IngredientList()));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_addIngredientCommand_invalidArgumentExceptionThrown")
    @ValueSource(strings = {
        "new n/"        //No ingredient name
    })
    void parseCommand_addIngredientCommand_invalidArgumentExceptionThrown(String command) {
        assertThrows(InvalidArgumentException.class,
                () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_editCommand_success")
    @ValueSource(strings = {
        "edit e/Pasta i/Potato i/Tomato i/Pasta s1/Boil pasta in water s2/Ice bath the pasta",
        "edit e/Pasta i/Potato i/Tomato",
        "edit e/Pasta s1/Boil pasta in water s2/Ice bath the pasta",
        "edit e/Pasta c/Italian",
        "edit e/Pasta c/",
        "edit e/Pasta t/5",
        "edit e/Pasta t/"
    })
    void parseCommand_editCommand_success(String command) {
        try {
            assertInstanceOf(EditCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_editCommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "edit e/name i/ingredients s1/step"     // Valid command but empty list
    })
    void parseCommand_editCommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_editCommand_invalidArgumentExceptionThrown")
    @ValueSource(strings = {
        "edit i/eggs i/milk s1/whisk together",                                          // Missing name
        "edit e/Pizza i/dough i/cheese s1/make dough s1/bake pizza",                     // Repeated step numbers
        "edit e/Sandwich i/bread i/ham i/cheese s1/spread butter s3/grill sandwich",     // Steps not in order
        "edit e/Soup i/ i/onion s1/cook onions s2/",                                     // Empty ingredient or step
        "edit e/  Porridge  i/ oats  i/milk s1 / cook oats",                             // Whitespace before slash
        "edit nPorridge i oats i milk s1 cook oats",                                     // No slashes
        "edit e//Omelette i/eggs// s1/fry/",                                             // Too many slashes
        "edit e/RamenEggs i/eggsi/soya sauce i/water s1/boil eggss2/eggs in ice bath",   // Missing spaces
        "edit e/Pasta",                                                                  // No changes made

        // Test cases for invalid cuisine and time
        "edit e/Pasta i/pasta i/sauce s1/cook pasta c/Italian t/abc",                    // Invalid time format
        "edit e/Omelette i/eggs c/Breakfast s1/fry t/-10",                               // Invalid time (negative)
        "edit e/Sandwich i/bread i/ham s1/spread butter c/Quick t/0"                     // Invalid time (zero)
    })
    void parseCommand_editCommand_invalidArgumentsExceptionThrown(String command) {
        assertThrows(InvalidArgumentException.class, () -> parseCommand(command, recipes, ingredients));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_findIngredientCommand_success")
    @ValueSource(strings = {
        "findI Pasta"
    })
    void parseCommand_findIngredientCommand_success(String command) {
        try {
            assertInstanceOf(FindIngredientCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_findIngredientCommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "findI Pasta"     // Valid command but empty list
    })
    void parseCommand_findIngredientCommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_findIngredientCommand_invalidArgumentExceptionThrown")
    @ValueSource(strings = {
        "findI"     // Empty ingredient name
    })
    void parseCommand_findIngredientCommand_invalidArgumentExceptionThrown(String command) {
        assertThrows(InvalidArgumentException.class, () -> parseCommand(command, recipes, ingredients));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_randomCommand_success")
    @ValueSource(strings = {
        "random"
    })
    void parseCommand_randomCommand_success(String command) {
        try {
            assertInstanceOf(RandomCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_randomCommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "random"     // Valid command but empty list
    })
    void parseCommand_randomCommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_recommendCommand_success")
    @ValueSource(strings = {
        "recommend"
    })
    void parseCommand_recommendCommand_success(String command) {
        try {
            assertInstanceOf(RecommendCommand.class, parseCommand(command, recipes, ingredients));
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("parseCommand_recommendCommand_emptyListExceptionThrown")
    @ValueSource(strings = {
        "recommend"     // Valid command but empty list
    })
    void parseCommand_recommendCommand_emptyListExceptionThrown(String command) {
        assertThrows(EmptyListException.class, () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }

    @ParameterizedTest
    @DisplayName("parseCommand_invalidCommand_invalidCommandExceptionThrown")
    @ValueSource(strings = {
        "ooga booga",           // Invalid command
        "TEST TEST TEST",       // Invalid command
        "command",              // Invalid command
        " add"                  // Space before command
    })
    void parseCommand_invalidCommand_invalidCommandExceptionThrown(String command) {
        assertThrows(InvalidCommandException.class,
                () -> parseCommand(command, new RecipeList(), new IngredientList()));
    }
}
