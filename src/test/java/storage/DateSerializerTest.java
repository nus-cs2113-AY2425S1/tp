//@@author Bev-low

package storage;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateSerializerTest {
    private DateSerializer dateSerializer;

    @BeforeEach
    public void setUp() {
        dateSerializer = new DateSerializer();
    }

    @Test
    public void testSerialize_validDate() {
        LocalDate date = LocalDate.of(2024, 12, 12);
        JsonElement jsonElement = dateSerializer.serialize(date, LocalDate.class, null);
        assertEquals("12-12-2024", jsonElement.getAsString());
    }

    @Test
    public void testSerialize_invalidDate() {
        LocalDate invalidDate = null;
        assertThrows(AssertionError.class, () -> {
            dateSerializer.serialize(invalidDate, LocalDate.class, null);
        }, "Serializer should throw an AssertionError for null input.");
    }

    @Test
    public void testSerialize_emptyDate() {
        assertThrows(Exception.class, () -> {
            LocalDate emptyDate = LocalDate.parse("");
            dateSerializer.serialize(emptyDate, LocalDate.class, null);
        }, "Serializer should throw an exception for empty date string.");
    }

    @Test
    public void testDeserialize_validJson() {
        JsonElement jsonElement = new JsonPrimitive("12-12-2024");
        LocalDate date = dateSerializer.deserialize(jsonElement, LocalDate.class, null);
        assertEquals(LocalDate.of(2024, 12, 12), date);
    }

    @Test
    public void testDeserialize_invalidJson() {
        JsonElement invalidJson = new JsonPrimitive("invalid-date");
        assertThrows(DateTimeParseException.class, () -> {
            dateSerializer.deserialize(invalidJson, LocalDate.class, null);
        });
    }

    @Test
    public void testDeserialize_emptyJson() {
        JsonElement emptyJson = new JsonPrimitive("");
        assertThrows(DateTimeParseException.class, () -> {
            dateSerializer.deserialize(emptyJson, LocalDate.class, null);
        });
    }
}

