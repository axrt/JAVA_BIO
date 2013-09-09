package blast.ncbi.local.exec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import blast.ncbi.output.BlastOutput;

import format.fasta.Fasta;

import util.ExecutableUtilFileOperator;
import util.UtilFileOperator;
/**
 * Java Bio is a free open source library for routine bioinformatics tasks.
 * Copyright (C) 2013  Alexander Tuzhikov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * An implementation of a {@link UtilFileOperator} that is used specifically by
 * {@link NCBI_EX_BLAST}-extending implementations to output to disk and input
 * the results from the process
 *
 * @author axrt
 */
public class NCBI_EX_BLAST_FileOperator<F extends Fasta> extends ExecutableUtilFileOperator<F> {
    /**
     * Default constructor
     */
    protected NCBI_EX_BLAST_FileOperator() {
        super();
    }

    /**
     * Opens an input stream that is further used by the {@link NCBI_EX_BLAST}
     * -extending implementations to suck in their output and unmarshall it from
     * XML to ab object representation of a JAXB-pre-compiled
     * {@link BlastOutput}
     *
     * @param outputFile {@link File} An XML file that will be created by the blast+
     *                   executable as an output
     * @return {@link InputStream} opened from the file
     * @throws IOException
     */
    protected InputStream readOutputXML(File outputFile) throws IOException {
        // Another funny way would be to return like this:
        // return new URL("file://"+outputFile.getPath()).openStream();
        return new FileInputStream(outputFile);
    }

}
