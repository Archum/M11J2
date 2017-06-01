package ast;

import cil.CIL;

public class LabelIndex {

    public static int LABEL_COUNT = 0;

    final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE };

    static int sizeOfInt(int x) {
        for (int i = 0;; i++)
            if (x <= sizeTable[i])
                return i + 1;
    }

    public String generateLabel() {
        String label = CIL.LABLE;
        for (int i = sizeOfInt(LABEL_COUNT); i < 4; i++) {
            label += "0";
        }
        label += String.valueOf(LABEL_COUNT);
        LABEL_COUNT++;
        return label;
    }
}
