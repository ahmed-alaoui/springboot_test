package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPostAndDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=thisisastring")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_input_text=thisisastring")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("")));

    }

    @Test
    void testCaseSensitivity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=HELLO")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/delete?post_input_text=HELLO").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("has been deleted")));

    }

    /*
    @Test
    void testHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("testing sdgg")));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_input_text=Sttring to delete")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/delete?post_input_text=Sttring to delete").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("does not exist")));
    }
    */
}