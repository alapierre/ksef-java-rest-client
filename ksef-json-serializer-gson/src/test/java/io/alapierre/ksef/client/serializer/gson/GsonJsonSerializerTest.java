package io.alapierre.ksef.client.serializer.gson;

import io.alapierre.ksef.client.JsonSerializer;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.function.Consumer;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GsonJsonSerializerTest {

    @Nested
    @DisplayName("Tests serialize and deserialize")
    class SerializerTest {

        GsonJsonSerializer serializer = new GsonJsonSerializer();

        @Test
        @DisplayName("Serialize from not empty Json")
        void fromJson() {
            val json = "{\"name\":\"Ala\"}";
            val bean = serializer.fromJson(json, SampleBean.class);

            Assertions.assertTrue(bean.isPresent());
            val name = bean.get().getName();
            Assertions.assertEquals("Ala", name);
        }

        @Test
        @DisplayName("Serialize from null")
        void fromNullJson() {
            val bean = serializer.fromJson(null, SampleBean.class);
            Assertions.assertFalse(bean.isPresent());
        }

        @Test
        @DisplayName("Serialize from empty string")
        void fromEmptyJson() {
            val bean = serializer.fromJson("", SampleBean.class);
            Assertions.assertFalse(bean.isPresent());
        }

        @Test
        void toJson() {

            val sampleBean = new SampleBean();
            sampleBean.setName("Ala");
            val json = serializer.toJson(sampleBean);

            System.out.println(json);
            Assertions.assertEquals("{\"name\":\"Ala\"}", json);
        }
    }

    @Nested
    @DisplayName("Tests service loader")
    class ServiceFinderTest {

        @Test
        void serviceLoader() {

            ServiceLoader<JsonSerializer> loader = ServiceLoader.load(JsonSerializer.class);

            Iterator<JsonSerializer> iterator = loader.iterator();
            val counter = new Counter();
            iterator.forEachRemaining(counter);

            System.out.println(counter.getCount());
            Assertions.assertEquals(1, counter.getCount());
        }
    }

    private class Counter implements Consumer<JsonSerializer> {

        private int counter;

        @Override
        public void accept(JsonSerializer jsonSerializer) {
            counter++;
        }

        @NotNull
        @Override
        public Consumer<JsonSerializer> andThen(@NotNull Consumer<? super JsonSerializer> consumer) {
            return Consumer.super.andThen(consumer);
        }

        public int getCount() {
            return counter;
        }
    }
}

