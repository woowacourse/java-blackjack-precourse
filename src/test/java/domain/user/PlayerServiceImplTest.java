package domain.user;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.BlackjackPrinter;
import domain.UserInterface;
import domain.card.Deck;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceImplTest {

    private PlayerService playerService;
    private User mockUser = mock(User.class);
    private Deck mockDeck = mock(Deck.class);
    private BlackjackPrinter mockBlackjackPrinter = mock(BlackjackPrinter.class);
    private UserInterface mockUserInterface = mock(UserInterface.class);
    private PlayerFactory mockPlayerFactory = mock(PlayerFactory.class);

    @BeforeEach
    void init() {
        playerService = new PlayerServiceImpl(mockDeck, mockBlackjackPrinter, mockUserInterface, mockPlayerFactory);
    }

    @Test
    @DisplayName("#confirmCards: 이미 버스트일 때")
    void confirmCardsWhenBust() {
        //given
        when(mockUser.isBust()).thenReturn(true);

        //when
        playerService.confirmCards(mockUser);

        //then
        verify(mockUserInterface, times(0)).getWillForMoreCard(mockUser);
    }

    @Test
    @DisplayName("#confirmCards: 버스트가 아니고, stay를 원할 때")
    void confirmCardsWhenNotBustWantToStay() {

        //given
        when(mockUser.isBust())
                .thenReturn(false);

        when(mockUserInterface.getWillForMoreCard(mockUser)).thenReturn("n");

        //when
        playerService.confirmCards(mockUser);

        //then
        verify(mockUser, times(0)).addCard(any());
    }

    @Test
    @DisplayName("#confirmCards: 버스트가 아니고, hit을 원할 때")
    void confirmCardsWhenNotBustWantToHit() {
        //given
        when(mockUser.isBust())
                .thenReturn(false)
                .thenReturn(true);

        when(mockUserInterface.getWillForMoreCard(mockUser))
                .thenReturn("y");

        //when
        playerService.confirmCards(mockUser);

        //then
        verify(mockUser).addCard(any());
    }

    @Test
    @DisplayName("#join: 두 명 참여")
    void join() {
        String pobiName = "pobi";
        String jasonName = "jason";
        double bettingMoney = 100.0;
        Player pobi = new Player(pobiName, bettingMoney);
        Player jason = new Player(jasonName, bettingMoney);
        String[] names = {pobiName, jasonName};
        when(mockUserInterface.extractNames()).thenReturn(names);
        when(mockUserInterface.getBettingMoney(pobiName)).thenReturn(bettingMoney);
        when(mockUserInterface.getBettingMoney(jasonName)).thenReturn(bettingMoney);
        when(mockPlayerFactory.create(pobiName, bettingMoney)).thenReturn(pobi);
        when(mockPlayerFactory.create(jasonName, bettingMoney)).thenReturn(jason);

        assertEquals(Arrays.asList(pobi, jason), playerService.join());
    }
}