package blast.event;

import java.util.EventObject;

import blast.BLAST;
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
 * An event wrapper class that allows to notify a listening object (presumably
 * {@link BLAST_TaskFinished_listener} by passing a pointer
 * 
 * @author axrt
 * 
 */
public class BLAST_FinishedEvent<B extends BLAST> extends EventObject {
	/**
	 * A constructor that wraps the source object into {@link Object}
	 * 
	 * @param source {@code B} that finished
	 */
	public BLAST_FinishedEvent(B source) {
		super(source);
	}

}
