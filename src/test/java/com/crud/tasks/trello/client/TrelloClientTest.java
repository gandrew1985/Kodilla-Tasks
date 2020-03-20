package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloAppToken()).thenReturn("test");
        when(trelloConfig.getTrelloAppUsername()).thenReturn("gandrewgolab");

    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        TrelloBoardDto[] boardDto = new TrelloBoardDto[1];
        boardDto[0] = new TrelloBoardDto("test_board", "test_id", new ArrayList<>());
        URI url = new URI("http://test.com/members/gandrewgolab/boards?key=test&token=test&fields=name,id&lists=all");
        System.out.println(url);
        when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(boardDto);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "test task",
                "test description",
                "top",
                "test_id");

        URI url = new URI("http://test.com/cards?key=test&token=test&name=test%20task&desc=test%20description" +
                "&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "Test task",
                "http://test.com"
        );

        when(restTemplate.postForObject(url, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //When
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        System.out.println(url);
        //Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        URI url = new URI(
                "http://test.com/members/gandrewgolab/boards?key=test&token=test&fields=name,id&lists=all"
        );
        TrelloBoardDto trelloBoardDto = null;
        when(restTemplate.getForObject(url, TrelloBoardDto.class)).thenReturn(trelloBoardDto);
        //When
        List<TrelloBoardDto> emptyList = trelloClient.getTrelloBoards();
        //Then
        assertEquals(0, emptyList.size());

    }
}
