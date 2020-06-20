package pl.sda.shopapp.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-20
 */
@SpringBootTest
@AutoConfigureMockMvc
class CustomerRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testCreateCompany() throws Exception {
        mvc.perform(post("/api/customers")
                .header("Content-Type", "application/json")
                .content("{\n" +
                        "    \"name\": \"TEST 2 S.A.\",\n" +
                        "    \"vatNumber\": \"0123456789\"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }
}
