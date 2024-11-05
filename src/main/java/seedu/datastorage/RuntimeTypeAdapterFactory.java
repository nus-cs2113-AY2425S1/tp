package seedu.datastorage;

import com.google.gson.TypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;

/**
 * A custom TypeAdapterFactory for Gson that supports polymorphic serialization and deserialization.
 * This class allows Gson to serialize and deserialize instances of subtypes based on a type field.
 *
 * <p>This implementation was inspired by Google Gson's example of supporting polymorphism in serialization.</p>
 *
 * @param <T> The base type of the polymorphic serialization.
 */
public class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<?> baseType;
    private final String typeFieldName;
    private final Map<String, Class<?>> labelToSubtype = new HashMap<>();
    private final Map<Class<?>, String> subtypeToLabel = new HashMap<>();

    /**
     * Private constructor to initialize the RuntimeTypeAdapterFactory with a base type and a type field name.
     *
     * @param baseType The base class for the polymorphic type.
     * @param typeFieldName The name of the JSON field that stores the type information.
     */
    private RuntimeTypeAdapterFactory(Class<?> baseType, String typeFieldName) {
        this.baseType = baseType;
        this.typeFieldName = typeFieldName;
    }

    /**
     * Creates a new RuntimeTypeAdapterFactory instance for a specific base type and type field name.
     *
     * @param <T> The base type for the factory.
     * @param baseType The base class for the polymorphic type.
     * @param typeFieldName The name of the JSON field that stores the type information.
     * @return A new RuntimeTypeAdapterFactory instance.
     */
    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName) {
        return new RuntimeTypeAdapterFactory<T>(baseType, typeFieldName);
    }

    /**
     * Registers a subtype and its corresponding label with the RuntimeTypeAdapterFactory.
     *
     * @param subtype The subclass to register.
     * @param label The label representing the subclass in JSON.
     * @return The current RuntimeTypeAdapterFactory instance for method chaining.
     */
    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> subtype, String label) {
        labelToSubtype.put(label, subtype);
        subtypeToLabel.put(subtype, label);
        return this;
    }

    /**
     * Creates a TypeAdapter for the specified type using the provided Gson instance.
     * This adapter handles the serialization and deserialization of polymorphic types based on a type field.
     *
     * @param <R> The type for which the TypeAdapter is created.
     * @param gson The Gson instance used for creating the adapter.
     * @param type The specific type for which a TypeAdapter is requested.
     * @return A TypeAdapter for the specified type, or null if the type is not assignable from the base type.
     */
    @Override
    public <R> TypeAdapter<R> create(final Gson gson, final TypeToken<R> type) {
        if (!baseType.isAssignableFrom(type.getRawType())) {
            return null;
        }

        final Map<String, TypeAdapter<?>> labelToDelegate = new HashMap<>();
        final Map<Class<?>, TypeAdapter<?>> subtypeToDelegate = new HashMap<>();

        labelToSubtype.forEach((label, subtype) -> {
            TypeAdapter<?> delegate = gson.getDelegateAdapter(this, TypeToken.get(subtype));
            labelToDelegate.put(label, delegate);
            subtypeToDelegate.put(subtype, delegate);
        });

        return new PolymorphicTypeAdapter<>(typeFieldName, subtypeToLabel, labelToDelegate, subtypeToDelegate, gson);
    }
}
