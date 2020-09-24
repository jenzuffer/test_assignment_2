public class Game {
    private int rolls[] = new int[21];
    private int currentroll = 0;

    public void rolls(int pins) {
        if (currentroll < rolls.length && pins > 0) {
            rolls[currentroll++] = pins;
        }
    }

    public int score() {
        int score = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikebonus(frameIndex);
                frameIndex++;
            } else if (isspare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumofballs_inframe(frameIndex);
                frameIndex += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int frameindex) {
        return rolls[frameindex] == 10;
    }

    private int sumofballs_inframe(int frameindex) {
        return rolls[frameindex] + rolls[frameindex + 1];
    }

    private int spareBonus(int frameindex) {
        return rolls[frameindex + 2];
    }

    private int strikebonus(int frameindex) {
        return rolls[frameindex + 1] + rolls[frameindex + 2];
    }

    private boolean isspare(int frameindex) {
        return rolls[frameindex] + rolls[frameindex + 1] == 10;
    }
}
