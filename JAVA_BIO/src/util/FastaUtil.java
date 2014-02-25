package util;

import format.fasta.Fasta;

import java.io.*;

/**
 * Created by alext on 2/25/14.
 */
public class FastaUtil {

    /**
     * Counts Fasta record in a given input
     * @param fastaInputStream {@link java.io.InputStream} that can be read as text to allow Fasta records to be counted
     * @return {@code long} number of records as counted by times that ">" sign occurs
     * @throws IOException typical for inputstreams
     */
    public static long countFasta(final InputStream fastaInputStream) throws IOException {
        long count = 0L;
        try (
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fastaInputStream));
        ) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(Fasta.fastaStart)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Counts Fasta records in a given fasta record file
     * @param fastaFile {@link java.io.File} that contains fasta records
     * @return a {@code long} value of the number of records
     * @throws IOException typical for file operations
     */
    public static long countFasta(final File fastaFile) throws IOException {
        try(
                final FileInputStream fileInputStream=new FileInputStream(fastaFile);
        ){
            return countFasta(fileInputStream);
        }
    }

}
