package domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.BlackjackConfig;
import domain.BlackjackPrinter;
import domain.card.Deck;

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
    @DisplayName("#confirmCards: 점수가 범위를 벗어날 때")
    void confirmCardsWhenPointOutOfRange() {
        //given
        when(mockUser.calculateScore()).thenReturn(BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER + 1);

        //when
        dealerService.confirmCards(mockUser);

        //then
        verify(mockUser, times(0)).addCard(any());
    }

    @Test
    @DisplayName("#confirmCards: 점수가 범위 안에 있을 때")
    void confirmCardsWhenPointInRange() {
        //given
        when(mockUser.calculateScore())
                .thenReturn(BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER - 1)
                .thenReturn(BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER + 1);

        //when
        dealerService.confirmCards(mockUser);

        //then
        verify(mockUser).addCard(any());

    }
}