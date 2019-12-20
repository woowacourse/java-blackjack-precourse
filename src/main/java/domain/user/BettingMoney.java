package domain.user;

import domain.outcome.OutcomeType;

class BettingMoney {
    private final double bettingMoney;

    BettingMoney(double bettingMoney) {
        valid(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    private void valid(double bettingMoney) {
        if (bettingMoney > 0) {
            return;
        }
        throw new IllegalArgumentException("올바르지 않은 베팅 금액입니다.");
    }

    double calcurateBlackJackBenefit(
            boolean isDealerBlackJack,
            boolean isPlayerBlackJack
    ) {
        return OutcomeType
                .calcurateBlackJackBenefit(isDealerBlackJack, isPlayerBlackJack)
                .getMultipleValue()
                * bettingMoney;
    }

//    double calcureateBenefit(boolean isWin) {
//        return OutcomeType.calcureateWinnerBenefit(isWin).getMultipleValue()
//                * bettingMoney;
//    }
//
//    double calcureateDrawBenefit() {
//        return OutcomeType.DRAW.getMultipleValue() * bettingMoney;
//    }
}
