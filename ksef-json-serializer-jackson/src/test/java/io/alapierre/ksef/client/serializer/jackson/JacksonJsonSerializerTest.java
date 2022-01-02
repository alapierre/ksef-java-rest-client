package io.alapierre.ksef.client.serializer.jackson;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
class JacksonJsonSerializerTest {

    private final JacksonJsonSerializer serializer = new JacksonJsonSerializer();

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
