package tutorlink.lists;

import tutorlink.component.Component;
import tutorlink.exceptions.ComponentNotFoundException;
import tutorlink.exceptions.DuplicateComponentException;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a list of components. Provides methods for adding, deleting, and searching for components,
 * as well as calculating the total weight of all components in the list.
 */
public class ComponentList {
    private static final String ERROR_COMPONENT_NOT_FOUND = "Error! Component %s does not exist in the list!";
    private static final String ERROR_DUPLICATE_COMPONENT = "Error! Component already exists in the list!";

    private ArrayList<Component> componentArrayList;

    /**
     * Creates an empty {@code ComponentList}.
     */
    public ComponentList() {
        this.componentArrayList = new ArrayList<>();
    }

    /**
     * Creates a {@code ComponentList} initialized with the given list of components.
     *
     * @param componentArrayList The initial list of components.
     */
    public ComponentList(ArrayList<Component> componentArrayList) {
        this.componentArrayList = componentArrayList;
    }

    /**
     * Searches for components in the list by name.
     *
     * @param name The name or partial name of the component to search for.
     * @return A {@code ComponentList} containing all components that match the search criteria.
     * @throws ComponentNotFoundException If no matching components are found.
     */
    public ComponentList findComponent(String name) throws ComponentNotFoundException {
        ComponentList filteredList = new ComponentList();
        filteredList.componentArrayList = componentArrayList
                .stream()
                .filter(comp -> comp.getName().equalsIgnoreCase(name))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredList.componentArrayList.isEmpty()) {
            throw new ComponentNotFoundException(String.format(ERROR_COMPONENT_NOT_FOUND, name));
        }
        return filteredList;
    }

    /**
     * Adds a component to the list if it does not already exist.
     *
     * @param component The component to add.
     * @throws DuplicateComponentException If a component with the same properties already exists in the list.
     */
    public void addComponent(Component component) throws DuplicateComponentException {
        for (Component comp : componentArrayList) {
            if (comp.equals(component)) {
                throw new DuplicateComponentException(ERROR_DUPLICATE_COMPONENT);
            }
        }
        componentArrayList.add(component);
    }

    /**
     * Deletes a component from the list.
     *
     * @param component The component to delete.
     * @throws ComponentNotFoundException If the component is not found in the list.
     */
    public void deleteComponent(Component component) throws ComponentNotFoundException {
        for (Component comp : componentArrayList) {
            if (comp.equals(component)) {
                componentArrayList.remove(comp);
                return;
            }
        }
        throw new ComponentNotFoundException(String.format(ERROR_COMPONENT_NOT_FOUND, component));
    }

    /**
     * Calculates the total weight of all components in the list.
     *
     * @return The total weight as an integer.
     */
    public int getTotalWeighting() {
        return componentArrayList.stream().mapToInt(Component::getWeight).sum();
    }

    /**
     * Returns a formatted string representation of the components in the list.
     *
     * @return A numbered list of components as a string.
     */
    @Override
    public String toString() {
        return "\t" +
                IntStream.range(0, componentArrayList.size())
                        .mapToObj(i -> (i + 1) + ": " + componentArrayList.get(i))
                        .collect(Collectors.joining("\n\t"));
    }

    /**
     * Retrieves all components in the list as a new {@code ArrayList}.
     *
     * @return A new list containing all components in this list.
     */
    public ArrayList<Component> findAllComponents() {
        return new ArrayList<>(componentArrayList);
    }

    /**
     * Returns the underlying list of components.
     *
     * @return The list of components as an {@code ArrayList}.
     */
    public ArrayList<Component> getComponentArrayList() {
        return componentArrayList;
    }

    /**
     * Returns the number of components in the list.
     *
     * @return The size of the component list.
     */
    public int size() {
        return componentArrayList.size();
    }
}
