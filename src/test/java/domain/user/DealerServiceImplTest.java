package domain.user;

import common.BlackjackConfig;
import domain.BlackjackPrinter;
import domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DealerServiceImplTest {
    private DealerServiceImpl dealerService;
    private Deck mockDeck = mock(Deck.class);
    private BlackjackPrinter mockBlackjackPrinter = mock(BlackjackPrinter.class);
    private User mockUser = mock(User.class);

    @BeforeEach
    void init() {
        dealerService = new DealerServiceImpl(mockDeck, mockBlackjackPrinter);
    }

    @Test
    void confirmCardsWhenPointOutOfRange() {
        //given
        when(mockUser.calculateScore()).thenReturn(BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER + 1);

        //when
        dealerService.confirmCards(mockUser);

        //then
        verify(mockUser, times(0)).addCard(any());
    }

    @Test
    void confirmCardsWhenPointInRange() {
        //given
        when(mockUser.calculateScore())
                .thenReturn(BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER -1)
                .thenReturn(BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER + 1);

        //when
        dealerService.confirmCards(mockUser);

        //then
        verify(mockUser).addCard(any());

    }
}