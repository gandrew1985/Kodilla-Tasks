package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> listZero = new ArrayList<>();
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "First", listZero);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "testSecond", listZero);
        TrelloBoard trelloBoard3 = new TrelloBoard("3", "third", listZero);
        trelloBoardList.add(trelloBoard1);
        trelloBoardList.add(trelloBoard2);
        trelloBoardList.add(trelloBoard3);
        System.out.println(trelloBoardList.size());
        //When
        List<TrelloBoardDto> resultBoard = trelloMapper.mapToBoardsDto(trelloBoardList);
        System.out.println(resultBoard.size());
        //Then
        assertEquals(3, resultBoard.size());
    }

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> listDto = new ArrayList<>();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("Letters", "22", listDto);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("numbers", "120", listDto);
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(trelloBoardDto1);
        trelloBoardDto.add(trelloBoardDto2);
        System.out.println(trelloBoardDto.size());
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDto);
        //Then
        assertEquals(2, trelloBoards.size());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card One", "Colours", "Up", "23");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card One", "Colours", "Up", "22");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("12", "Twelve", true);
        TrelloList trelloList2 = new TrelloList("14", "Twel", true);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList1);
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(2, trelloListDto.size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("12", "Twelve", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("14", "Twel", true);
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);
        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals(2, trelloList.size());
    }


}