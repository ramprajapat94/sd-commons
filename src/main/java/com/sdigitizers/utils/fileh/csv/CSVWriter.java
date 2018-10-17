package com.sdigitizers.utils.fileh.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * This class provides functionalities for writing CSV files. For more ops
 * download:
 * <Strong>OpenCSV.jar</Strong>
 * @author Shriram Prajapat
 */
public class CSVWriter implements AutoCloseable {

    private final FileWriter fileWriter;

    /**
     * Creates an instance of CSV file where CSV file writing operations can be
     * performed <br>
     *
     * @param filepath Complete path of the CSV file (including file name and
     * extension) <br>
     * @throws IOException
     */
    public CSVWriter(String filepath) throws IOException {
        if (!filepath.endsWith(".csv")) {
            filepath = filepath.concat(".csv");
        }
        fileWriter = new FileWriter(filepath);
    }

    /**
     * This method should be called first<br>
     * Write values in a line(row)<br>
     *
     * @param values List of values to be written in a line(row)
     * @throws IOException
     */
    public void writeHeader(List<String> values) throws IOException {
        writeLine(fileWriter, values);
    }

    /**
     * Write values in a line(row)<br>
     *
     * @param values List of values to be written in a line(row)
     * @throws IOException
     */
    public void writeLine(List<String> values) throws IOException {
        writeLine(fileWriter, values);
    }

    /**
     * Write values in a line(row)<br>
     *
     * @param lines List of a list of values to be written in file at once
     * @throws IOException
     */
    public void writeLines(List<List<String>> lines) throws IOException {
        for (List<String> list : lines) {
            writeLine(list);
        }
    }

    /**
     * Flush and close the fileWriter object
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if (fileWriter != null) {
            fileWriter.flush();
            fileWriter.close();
        }
    }

    /////////////////Static Members
    private static final char DEFAULT_SEPARATOR = ',';

    //https://tools.ietf.org/html/rfc4180
    private static String followCSVformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;
    }

    /**
     * Eg.: writeLine(writer, Arrays.asList("a", "b", "c", "d"));
     *
     * @param w FileWriter
     * @param values List of values to be written in a line
     * @throws IOException
     */
    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    /**
     * Eg.: writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), ',', '"');
     *
     * @param w FileWriter
     * @param values List of values to be written in a line
     * @param separators Any custom separator character
     * @throws IOException
     */
    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {
        boolean first = true;
        //default customQuote is empty
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCSVformat(value));
            } else {
                sb.append(customQuote).append(followCSVformat(value)).append(customQuote);
            }
            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }

}
