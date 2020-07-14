package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

interface DataType {

    void sort(ArrayList<String> inputDataLines, String sortingType, File outputFile);

}

class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}

class Long implements DataType {

    @Override
    public void sort(ArrayList<String> inputDataLines, String sortingType, File outputFile) {
        ArrayList<Integer> integerArrayList = getInputDataByDataType(inputDataLines);
        switch (sortingType) {
            case "natural":
                Collections.sort(integerArrayList);
                System.out.printf("Total numbers: %d\n" +
                        "Sorted data: ", integerArrayList.size());
                integerArrayList.forEach(num -> System.out.print(num + " "));
                break;
            case "byCount":

                Map<Integer, Integer> treemap = new TreeMap<>();
                Map<Integer, Integer> finalTreemap = treemap;

                integerArrayList.forEach(n -> {
                    finalTreemap.merge(n, 1, Integer::sum);
                });

                treemap = MapUtil.sortByValue(treemap);

                int total = integerArrayList.size();

                if (outputFile == null) {

                    System.out.printf("Total numbers: %d.\n", total);

                    for (Integer num : treemap.keySet()) {
                        System.out.printf(
                                "%d: %d time(s), %d%%\n",
                                num, treemap.get(num),
                                Math.round((double) (treemap.get(num)) / total * 100)
                        );
                    }

                } else {
                    try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                        printWriter.printf("Total numbers: %d.\n", total);
                        for (Integer num : treemap.keySet()) {
                            printWriter.printf(
                                    "%d: %d time(s), %d%%\n",
                                    num, treemap.get(num),
                                    Math.round((double) (treemap.get(num)) / total * 100)
                            );
                        }
                    } catch (IOException e) {
                        System.out.printf("An exception occurs %s", e.getMessage());
                    }
                }
                break;
        }
    }

    private ArrayList<Integer> getInputDataByDataType(ArrayList<String> inputDataLines) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (String line : inputDataLines) {
            for (String word : line.split("\\s+")) {
                if (word.matches("(-|\\+)?\\d+")) {
                    integerArrayList.add(Integer.valueOf(word));
                } else {
                    System.out.printf("\"%s\" isn't a long. It's skipped.\n", word);
                }
            }
        }
        return integerArrayList;
    }
}

class Word implements DataType {

    @Override
    public void sort(ArrayList<String> inputDataLines, String sortingType, File outputFile) {
        ArrayList<String> wordsArrayList = getInputDataByDataType(inputDataLines);
        switch (sortingType) {
            case "natural":
                Collections.sort(wordsArrayList);
                System.out.printf("Total words: %d\n" +
                        "Sorted data: ", wordsArrayList.size());
                wordsArrayList.forEach(word -> System.out.print(word + " "));
                break;
            case "byCount":

                Map<String, Integer> treemap = new TreeMap<>();
                Map<String, Integer> finalTreemap = treemap;

                wordsArrayList.forEach(n -> {
                    finalTreemap.merge(n, 1, Integer::sum);
                });

                treemap = MapUtil.sortByValue(treemap);

                int total = wordsArrayList.size();

                if (outputFile == null) {

                    System.out.printf("Total words: %d.\n", total);

                    for (String num : treemap.keySet()) {
                        System.out.printf(
                                "%s: %d time(s), %d%%\n",
                                num, treemap.get(num),
                                Math.round((double) (treemap.get(num)) / total * 100)
                        );
                    }

                } else {
                    try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                        printWriter.printf("Total words: %d.\n", total);
                        for (String num : treemap.keySet()) {
                            printWriter.printf(
                                    "%s: %d time(s), %d%%\n",
                                    num, treemap.get(num),
                                    Math.round((double) (treemap.get(num)) / total * 100)
                            );
                        }
                    } catch (IOException e) {
                        System.out.printf("An exception occurs %s", e.getMessage());
                    }
                }
                break;
        }
    }

    private ArrayList<String> getInputDataByDataType(ArrayList<String> inputDataLines) {
        ArrayList<String> wordsArrayList = new ArrayList<>();
        for (String line : inputDataLines) {
            Collections.addAll(wordsArrayList, line.split("\\s+"));
        }
        return wordsArrayList;
    }

}

class Line implements DataType {

    @Override
    public void sort(ArrayList<String> inputDataLines, String sortingType, File outputFile) {
        ArrayList<String> linesArrayList = inputDataLines;
        switch (sortingType) {
            case "natural":
                Collections.sort(linesArrayList);
                Collections.reverse(linesArrayList);
                System.out.printf("Total lines: %d\n" +
                        "Sorted data: ", linesArrayList.size());
                linesArrayList.forEach(System.out::println);
                break;
            case "byCount":

                Map<String, Integer> treemap = new TreeMap<>();
                Map<String, Integer> finalTreemap = treemap;

                linesArrayList.forEach(n -> {
                    finalTreemap.merge(n, 1, Integer::sum);
                });

                treemap = MapUtil.sortByValue(treemap);

                int total = linesArrayList.size();

                if (outputFile == null) {

                    System.out.printf("Total lines: %d.\n", total);

                    for (String num : treemap.keySet()) {
                        System.out.printf(
                                "%s: %d time(s), %d%%\n",
                                num, treemap.get(num),
                                Math.round((double) (treemap.get(num)) / total * 100)
                        );
                    }

                } else {
                    try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                        printWriter.printf("Total lines: %d.\n", total);
                        for (String num : treemap.keySet()) {
                            printWriter.printf(
                                    "%s: %d time(s), %d%%\n",
                                    num, treemap.get(num),
                                    Math.round((double) (treemap.get(num)) / total * 100)
                            );
                        }
                    } catch (IOException e) {
                        System.out.printf("An exception occurs %s", e.getMessage());
                    }
                }
                break;
        }
    }

}

class SortingTool {

    private String sortingType;
    private DataType dataType;
    private File outputFile;

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public void setDataType(String dataType) {
        switch (dataType) {
            case "long":
                this.dataType = new Long();
                break;
            case "word":
                this.dataType = new Word();
                break;
            case "line":
                this.dataType = new Line();
                break;
            default:
                System.out.println("Error!");
                System.exit(0);
        }
    }

    public void sort(ArrayList<String> inputDataLines) {
        this.dataType.sort(inputDataLines, sortingType, outputFile);
    }

}

public class Main {
    public static void main(final String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> inputDataLines = new ArrayList<>();

        String sortingType = "natural";
        String dataType = "line";
        String inputFilePath = null;
        String outputFilePath = null;

        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("-sortingType")) {
                try {
                    switch (args[index + 1]) {
                        case "byCount":
                            sortingType = "byCount";
                            break;
                        case "natural":
                            sortingType = "natural";
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("No sorting type defined!");
                }
            }
            if (args[index].equals("-dataType")) {
                try {
                    switch (args[index + 1]) {
                        case "long":
                            dataType = "long";
                            break;
                        case "line":
                            dataType = "line";
                            break;
                        case "word":
                            dataType = "word";
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("No data type defined!");
                }
            }
            if (args[index].equals("-inputFile")) {
                try {
                    inputFilePath = args[index + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No input file defined!");
                }
            }
            if (args[index].equals("-outputFile")) {
                try {
                    outputFilePath = args[index + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No output file defined!");
                }
            }
        }

        if (inputFilePath == null) {
            while (scanner.hasNextLine()) {
                inputDataLines.add(scanner.nextLine());
            }
            scanner.close();
        } else {
            File inputFile = new File(inputFilePath);
            try (Scanner fileScanner = new Scanner(inputFile)) {
                while (fileScanner.hasNextLine()) {
                    inputDataLines.add(fileScanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("No file found: " + inputFilePath);
            }
        }
        File outputFile = null;
        if (outputFilePath != null) {
            outputFile = new File(outputFilePath);
        }

        SortingTool sortingTool = new SortingTool();
        sortingTool.setSortingType(sortingType);
        sortingTool.setDataType(dataType);
        sortingTool.setOutputFile(outputFile);
        sortingTool.sort(inputDataLines);

    }
}
