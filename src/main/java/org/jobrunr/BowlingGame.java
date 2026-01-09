package org.jobrunr;

public class BowlingGame {

    private boolean strike = false;
    private int strikeBonus = 0;
    private boolean spare = false;
    private int rollCount = 0;
    private int pinsLeft = 10;
    private int[] scoreArray = new int[12];

    public void roll(int pinsDown) {
        scoreArray[rollCount] += pinsDown;
        pinsLeft -= pinsDown;

        if (rollCount < 11) {
            rollCount++;
        }

        if (strike) {
            scoreArray[rollCount - 1] += pinsDown;
            strikeBonus++;
            pinsLeft = 10;
            if (strikeBonus < 1) {
                strike = false;
                strikeBonus = 0;
            }
        }

        if (spare) {
            scoreArray[rollCount] += pinsDown;
            spare = false;
        }

        if (pinsDown == 10) {
            if (strike && rollCount < 9) {
                scoreArray[rollCount - 1] += pinsDown;
            }

            strike = true;
            strikeBonus = 0;
            return;
        }

        if (pinsLeft == 0) {
            spare = true;
            pinsLeft = 10;
            return;
        }

        if (rollCount % 2 == 0) {
            pinsLeft = 10;
        }
    }

    public int score() {
        int sc = 0;

        for (int frame = 0; frame < scoreArray.length; frame++) {
            sc += scoreArray[frame];
        }

        return sc;
    }
}
