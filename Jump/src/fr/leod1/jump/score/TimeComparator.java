package fr.leod1.jump.score;

import java.util.Comparator;

public class TimeComparator implements Comparator<BestTimeScore> {
    @Override
    public int compare(BestTimeScore Score1, BestTimeScore Score2) {
        if (Score1.getTime() == Score2.getTime()){
            return -1;
        }
        return (int) (Score1.getTime() - Score2.getTime());
    }

}
