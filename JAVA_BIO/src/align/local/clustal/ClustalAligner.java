package align.local.clustal;

import align.local.ExecutableAligner;
import format.clustal.ClustalAln;
import format.fasta.Fasta;
import util.ExecutableUtilFileOperator;

import java.io.File;
import java.util.List;
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
 *TODO Document
 */
public abstract class ClustalAligner<F extends Fasta> extends ExecutableAligner<F> {

    protected ClustalAln output;
    protected final ExecutableUtilFileOperator<F> fileOperator;
    protected final File inputFile;
    protected final File outputFile;

    protected ClustalAligner(List<F> sequences, File tmpDir, File executable, String[] parameterList, ExecutableUtilFileOperator<F> fileOperator) {
        super(sequences, tmpDir, executable, parameterList);
        this.fileOperator=fileOperator;
        this.inputFile=new File(this.tmpDir,"in_"+this.hashCode());
        this.outputFile=new File(this.tmpDir,"out_"+this.hashCode());
    }

    protected ClustalAligner(File tmpDir, File executable, String[] parameterList, ExecutableUtilFileOperator<F> fileOperator) {
        super(tmpDir, executable, parameterList);
        this.fileOperator=fileOperator;
        this.inputFile=new File(this.tmpDir,"in_"+this.hashCode());
        this.outputFile=new File(this.tmpDir,"out_"+this.hashCode());
    }

    protected abstract void checkRecordsRedundancy() throws Exception;
}
