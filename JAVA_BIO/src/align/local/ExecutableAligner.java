package align.local;

import align.Aligner;

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
public abstract class ExecutableAligner<T> extends Aligner<T>{

    protected final File tmpDir;
    protected final File executable;
    protected final String[] parameterList;

    protected ExecutableAligner(List<T> sequences,File tmpDir,File executable,String[] parameterList) {
        super(sequences);
        this.tmpDir=tmpDir;
        this.executable=executable;
        this.parameterList=parameterList;
    }

    protected ExecutableAligner(File tmpDir,File executable,String[] parameterList) {
        super();
        this.tmpDir=tmpDir;
        this.executable=executable;
        this.parameterList=parameterList;
    }
}
