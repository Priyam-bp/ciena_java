package com.assignmentone.springboot.assignment_one.service_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;
import com.assignmentone.springboot.assignment_one.service.ShelfPositionService;

@ExtendWith(MockitoExtension.class)
public class ShelfPostionTests {
    
    @InjectMocks
    private ShelfPositionService shelfPositionService;

    @Mock
    private ShelfPositionRepository shelfPositionRepository;

    @Mock
    private ShelfRepository shelfRepository;

    private ShelfPositionVO shelfPosition1;
    private ShelfPositionVO shelfPosition2;
    private ShelfVO shelf1;

    @BeforeEach
    public void setUp(){
        shelfPosition1 = new ShelfPositionVO("Shelf Postion 1");
        shelfPosition1.setId(1L);
        shelfPosition2 = new ShelfPositionVO("Shelf Postion 2");
        shelfPosition2.setId(2L);
        
        shelf1 = new ShelfVO(1L, "shelf 1", "type A");
    }

    @Test
    public void getShelfPositionById(){
       when(shelfPositionRepository.findById(1L)).thenReturn(Optional.of(shelfPosition1));

        ShelfPositionVO result = shelfPositionService.getShelfPostion(1L);

        assertNotNull(result);
        assertEquals("Shelf Postion 1", result.getName());
        verify(shelfPositionRepository, times(1)).findById(1L);
    }

    @Test
    public void getAllshelfPositions(){
        when(shelfPositionRepository.findAll()).thenReturn(List.of(shelfPosition1,shelfPosition2));

        assertEquals(2, shelfPositionService.getAllshelfPositions().size());        
    }

    @Test
    public void saveShelfPostion(){
        when(shelfPositionRepository.save(any(ShelfPositionVO.class))).thenReturn(shelfPosition1);

        ShelfPositionVO res = shelfPositionService.saveSheldPostion(shelfPosition1);
        assertEquals(res, shelfPosition1);
        verify(shelfPositionRepository, times(1)).save(any(ShelfPositionVO.class));
    }

    @Test
    public void addShelfToShelfPosition(){
        when(shelfPositionRepository.findById(1L)).thenReturn(Optional.of(shelfPosition1));
        when(shelfRepository.findById(1L)).thenReturn(Optional.of(shelf1));

        shelfPositionService.addShelfToShelfPosition(1L, 1L);

        assertTrue(shelfPosition1.getShelf() == shelf1);
    }
}
