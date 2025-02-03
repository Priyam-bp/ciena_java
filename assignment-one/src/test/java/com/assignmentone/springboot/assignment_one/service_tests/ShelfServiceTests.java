package com.assignmentone.springboot.assignment_one.service_tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;
import com.assignmentone.springboot.assignment_one.service.ShelfService;

@ExtendWith(MockitoExtension.class)
public class ShelfServiceTests {
    
    @InjectMocks
    private ShelfService shelfService;

    @Mock
    private ShelfRepository shelfRepository;

    private ShelfVO shelf1;
    private ShelfVO shelf2;

    @BeforeEach
    public void setUp(){
        shelf1 = new ShelfVO(1L, "Shelf A", "Type 1");
        shelf2 = new ShelfVO(2L, "Shelf B", "Type 2");
    }

    @Test
    public void testSaveShelf(){
        when(shelfRepository.save(any(ShelfVO.class))).thenReturn(shelf1);

        ShelfVO result = shelfService.saveShelf(shelf1);

        assertNotNull(result);
        assertEquals("Shelf A", result.getName());
        verify(shelfRepository, times(1)).save(shelf1);
    }

    @Test
    public void testGetShelfById(){
        when(shelfRepository.findById(1L)).thenReturn(Optional.of(shelf1));

        ShelfVO result = shelfService.getShelf(1L);

        assertNotNull(result);
        assertEquals("Shelf A", result.getName());
        verify(shelfRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetShelfById_NotFound(){
        when(shelfRepository.findById(3L)).thenReturn(Optional.empty());

        ShelfVO result = shelfService.getShelf(3L);

        assertNull(result);
        verify(shelfRepository, times(1)).findById(3L);
    }

    @Test
    public void testGetAllShelves(){
        when(shelfRepository.findAll()).thenReturn(Arrays.asList(shelf1, shelf2));

        List<ShelfVO> result = shelfService.getAllShelves();

        assertEquals(2, result.size());
        verify(shelfRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteShelfById(){
        doNothing().when(shelfRepository).deleteById(1L);

        shelfService.deleteShelfById(1L);

        verify(shelfRepository, times(1)).deleteById(1L);
    }
}
