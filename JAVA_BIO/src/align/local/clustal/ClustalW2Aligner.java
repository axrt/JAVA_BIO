package align.local.clustal;

import format.clustal.AlignmentException;
import format.clustal.ClustalW2Aln;
import format.fasta.Fasta;
import util.ExecutableUtilFileOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
 * TODO Document
 */
public class ClustalW2Aligner<F extends Fasta> extends ClustalAligner<F> {

    protected ClustalW2Aln output;

    protected ClustalW2Aligner(List<F> sequences, File tmpDir, File executable, String[] parameterList, ExecutableUtilFileOperator<F> fileOperator) {
        super(sequences, tmpDir, executable, parameterList, fileOperator);

    }

    protected ClustalW2Aligner(File tmpDir, File executable, String[] parameterList, ExecutableUtilFileOperator<F> fileOperator) {
        super(tmpDir, executable, parameterList, fileOperator);

    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void align() throws IOException, InterruptedException, AlignmentException {
        if(this.sequences==null){
            throw new IllegalStateException("No sequences provided yet!");
        }else if(this.sequences.size()==1){
            throw new IllegalStateException("A single sequence provided, at least two sequences required for a proper alignment!");
        }
        //
        this.checkRecordsRedundancy();
        //
        this.fileOperator.createTMPFolder(this.tmpDir);
        //
        this.fileOperator.writeFastaListToFile(this.sequences, this.inputFile);
        //
        final String[] command = new String[this.parameterList.length + 3];
        command[0] = this.executable.getPath();
        command[1] = "-infile"+"="+this.inputFile.getPath();
        command[2] = "-outfile"+"="+this.outputFile.getPath();
        System.arraycopy(parameterList, 0, command, 3, command.length - 3);
        try {
            // Build a process
            final Process p = Runtime.getRuntime().exec(command);
            p.waitFor();

            String s;
            try (BufferedReader stdNorm = new BufferedReader(
                    new InputStreamReader(p.getInputStream()))) {

                //TODO: For debuggin purposes only, an API should not print out anything!!!
                while ((s = stdNorm.readLine()) != null) {
                    System.out.println("STD:> " + s);
                }
            }
            // In case of an error - try to recover
            try (BufferedReader stdError = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()))) {

                while ((s = stdError.readLine()) != null) {
                    System.out.println("ERR:> " + s);

                }
            }
            //Suck in the results
            this.output = ClustalW2Aln.newInstaceFromFile(this.outputFile);
        } finally {
            this.inputFile.delete();
            this.outputFile.delete();
            new File(this.inputFile.getPath()+".dnd").delete();//TODO: Temporal fix, should suppress in clustal
        }
    }

    public ClustalW2Aln getOutput() {
        return output;
    }

    @Override
    protected void checkRecordsRedundancy() throws AlignmentException {
        if(this.sequences==null){
            throw new IllegalStateException("No sequences provided yet!");
        }
        Set<String> acs=new HashSet<>(this.sequences.size());
        //System.out.println(this.sequences.get(0).getAC());
        for(int i=0;i<this.sequences.size();i++){
            if(!acs.add(this.sequences.get(i).getAC().trim())){
              throw new AlignmentException("Redundant ACs in input: "+this.sequences.get(i).getAC()+" at "+i);
            }
        }
    }
    public static <F extends Fasta>ClustalW2Aligner<F> newDefaultInstance(List<F> sequences, File tmpDir, File executable){
        return new ClustalW2Aligner<F>(sequences,tmpDir,executable,new String[]{},new ExecutableUtilFileOperator<F>() {
        });
    }
    public static <F extends Fasta>ClustalW2Aligner<F> newInstanceWithSequences(List<F> sequences, File tmpDir, File executable, String[] parameterList, ExecutableUtilFileOperator<F> fileOperator){
        return new ClustalW2Aligner<F>(sequences,tmpDir,executable,parameterList,fileOperator);
    }
    public static <F extends Fasta>ClustalW2Aligner<F> newInstance(File tmpDir, File executable, String[] parameterList, ExecutableUtilFileOperator<F> fileOperator){
        return new ClustalW2Aligner<F>(tmpDir,executable,parameterList,fileOperator);
    }
}
