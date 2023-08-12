package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMA_SYMBOL = ",";
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT_STRING = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        String text = readFile(fromFile);
        String statistic = getStatisticFromText(text);
        writeStatistic(toFile, statistic);
    }

    private void writeStatistic(File toFile, String statistic) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFile));
            writer.write(statistic);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getStatisticFromText(String text) {
        int supplySum = 0;
        int buySum = 0;
        String[] lines = text.split("\n");
        for (String line : lines) {
            String[] data = line.split(COMA_SYMBOL);
            if (data[0].equals(SUPPLY_STRING)) {
                supplySum += Integer.parseInt(data[1]);
                continue;
            }
            buySum += Integer.parseInt(data[1]);
        }
        return SUPPLY_STRING + COMA_SYMBOL + supplySum + System.lineSeparator()
                + BUY_STRING + COMA_SYMBOL + buySum + System.lineSeparator()
                + RESULT_STRING + COMA_SYMBOL + (supplySum - buySum);
    }

    private String readFile(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder result = new StringBuilder();
        reader.lines().forEach(s -> result.append(s).append("\n"));
        return result.toString();
    }
}
