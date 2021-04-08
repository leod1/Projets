package fr.leod1.ppmproject.ppmprojects.comparotor;

import fr.leod1.ppmproject.ppmprojects.PPMprojects;

import java.util.Comparator;

public class AlphabethiqueComparator implements Comparator<PPMprojects> {
    @Override
    public int compare(PPMprojects o1, PPMprojects o2) {
        return o1.getNameOfProject().compareToIgnoreCase(o2.getNameOfProject());
    }
}
