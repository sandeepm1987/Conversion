package com.momentum.conversion.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Test
    public void testGetDiscount() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/convert/v1/discount/{VALUE}/{PERCENTAGE}", 200, 50))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\n" +
                        "    \"input\": \"200\",\n" +
                        "    \"result\": \"100.0\",\n" +
                        "    \"percentageDiscount\": \"50\"\n" +
                        "}"));
    }

    @Test
    public void testGetDiscount_Exception() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/convert/v1/discount/{VALUE}/{PERCENTAGE}", 200, "50_A"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\n" +
                        "    \"errorMessage\": \"Input Percentage should be numeric value\"\n" +
                        "}"));
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/convert/v1/discount/{VALUE}/{PERCENTAGE}", "200_!", 50))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\n" +
                        "    \"errorMessage\": \"Input conversion should be numeric value\"\n" +
                        "}"));
    }


    @Test
    public void testGetTime() throws Exception {
        String s = "{\n" +
                "    \"input\": \"60\",\n" +
                "    \"result\": \"1.0\",\n" +
                "    \"from\": \"SEC\",\n" +
                "    \"to\": \"MIN\"\n" +
                "}";
        helper("/convert/v1/time/sec/min/{SEC}", s, "60");
    }

    @Test
    public void testGetMass() throws Exception {
        String s = "{\n" +
                "    \"input\": \"20\",\n" +
                "    \"result\": \"44.1\",\n" +
                "    \"from\": \"KILOGRAM\",\n" +
                "    \"to\": \"POUND\"\n" +
                "}";
        helper("/convert/v1/mass/kilogram/pound/{KG}", s, "20");
    }

    @Test
    public void testGetTime_Exception() throws Exception {
        String s = "{\n" +
                "    \"errorMessage\": \"Input conversion should be numeric value\"\n" +
                "}";
        helper("/convert/v1/time/sec/min/{SEC}", s, "60_TEST");
    }

    @Test
    public void testGetTemp() throws Exception {
        String s = "{\n" +
                "    \"input\": \"15\",\n" +
                "    \"result\": \"59.0\",\n" +
                "    \"from\": \"CELSIUS\",\n" +
                "    \"to\": \"FAHRENHEIT\"\n" +
                "}";
        helper("/convert/v1/temperature/celsius/fahrenheit/{C}", s, "15");
    }

    @Test
    public void testGetData() throws Exception {
        String s = "{\n" +
                "    \"input\": \"1\",\n" +
                "    \"result\": \"1024.0\",\n" +
                "    \"from\": \"GB\",\n" +
                "    \"to\": \"MB\"\n" +
                "}";
        helper("/convert/v1/digitalstorage/gb/mb/{GB}", s, "1");
    }


    private void helper(String url, String s, String i) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(url, i))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(s));
    }

}
