package com.assignmentone.springboot.assignment_one.controller_tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import com.assignmentone.springboot.assignment_one.AssignmentOneApplication;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.service.ShelfService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(
    classes = AssignmentOneApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)

@AutoConfigureMockMvc
public class ShelfControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ShelfService shelfService; 

    @Test
    public void getAllShelves() throws Exception {
        List<ShelfVO> list = List.of(
            new ShelfVO(1L, "shelf1", "Shelf type 1"),
            new ShelfVO(2L, "shelf2", "Shelf type 2")
        );

        when(shelfService.getAllShelves()).thenReturn(list); 

        mockMvc.perform(get("/shelf"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) 
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("shelf1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("shelf2"));
    }

    @Test
    public void getShelfById() throws Exception{
        ShelfVO shelf1 = new ShelfVO(1L, "shelf1", "Shelf type 1");

        when(shelfService.getShelf(1L)).thenReturn(shelf1);

        mockMvc.perform(get("/shelf/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("shelf1"))
                .andExpect(jsonPath("$.id").value(1L));

    }

    @Test
    public void saveShelf() throws Exception{
        ShelfVO shelf1 = new ShelfVO(1L,"shelf1", "Shelf type 1");

        when(shelfService.saveShelf(any(ShelfVO.class))).thenReturn(shelf1);

        mockMvc.perform(post("/shelf")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(shelf1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("shelf1"));
    }
}
