package pl.sda.shopapp.rest;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.shopapp.service.CustomerService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @MockBean
    private CustomerService service;

    @Test
    void testCreateCompany() throws Exception {
        // given
        var id = UUID.randomUUID();
        given(service.createCompany(any())).willReturn(id);

        // when
        mvc.perform(post("/api/customers")
                .header("Content-Type", "application/json")
                .content("{\n" +
                        "    \"name\": \"TEST 2 S.A.\",\n" +
                        "    \"vatNumber\": \"0123456789\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void testFailedData() throws Exception {
        mvc.perform(post("/api/customers")
                .header("Content-Type", "application/json")
                .content("{\n" +
                        "    \"name\": \"TEST 2 S.A.\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
