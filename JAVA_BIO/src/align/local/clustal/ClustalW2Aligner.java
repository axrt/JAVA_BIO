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
 * TODO Document
 */
public class ClustalW2Aligner<F extends Fasta> extends ClustalAligner<F> {

    protected ClustalW2Aln output;

    protected ClustalW2Aligner(List<F> sequences, File tmpDir, File executable, String[] parameterList, ExecutableUtilFileOperator<F> fileOperator) {
        super(sequences, tmpDir, executable, parameterList, fileOperator);

    }

    @Override
    public void align() throws IOException, InterruptedException, AlignmentException {
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
        for (int i = 3; i < command.length; i++) {
            command[i] = parameterList[i - 3];
        }
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
        }
    }

    public ClustalW2Aln getOutput() {
        return output;
    }

    @Override
    protected void checkRecordsRedundancy() throws AlignmentException {
        Set<String> acs=new HashSet<>(this.sequences.size());
        for(F f:this.sequences){
            if(acs.add(f.getAC().trim()));
        }
        if(acs.size()!=this.sequences.size()){
            throw new AlignmentException("Redundant ACs in input!");
        }
    }

    public static <F extends Fasta>ClustalW2Aligner newInstance(List<F> sequences, File tmpDir, File executable, String[] parameterList, ExecutableUtilFileOperator<F> fileOperator){
        return new ClustalW2Aligner(sequences,tmpDir,executable,parameterList,fileOperator);
    }
}
