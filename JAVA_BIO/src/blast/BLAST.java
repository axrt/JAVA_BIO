/**
 * 
 */
package blast;

import java.util.ArrayList;
import java.util.List;

import blast.event.BLAST_FinishedEvent;
import blast.event.BLAST_TaskFinished_listener;
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
 * @author axrt A general abstraction, represents a
 *         "Basic Local Alignment Tool process" concept.
 */
public abstract class BLAST implements Runnable {
	/**
	 * A list of {@link BLAST_TaskFinished_listener}-implementing modules that
	 * get notified when a blast finishes a task
	 */
	private List<BLAST_TaskFinished_listener> listeners;

	/**
	 * Constructor
	 */
	protected BLAST() {
		// Initialize a list of listeners
		this.listeners = new ArrayList<>();
	}

	/**
	 * Adds another listener to a list of those being notified when the task
	 * finishes
	 * @param listener {@link BLAST_TaskFinished_listener} listener of a finished blast event
	 */
	public synchronized void addListener(BLAST_TaskFinished_listener listener) {
		this.listeners.add(listener);
	}

	/**
	 * Removes a certain listener from a list of those being notified when the
	 * task finishes
	 * 
	 * @param listener {@link BLAST_TaskFinished_listener} listener to remove
	 */
	public synchronized void removeListener(BLAST_TaskFinished_listener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Notifies all the listening modules form the list of listeners
	 */
	protected void notifyListeners() {
        for (BLAST_TaskFinished_listener listener : this.listeners) {
            listener.handleAFinishedBLAST(
                    new BLAST_FinishedEvent<BLAST>(this));
        }
	}
    /**
     * Should launch the blast process. In case a separate thread is created
     * alongside the main thread, this should be called within the run()
     *
     * @throws Exception
     */

    protected abstract void BLAST() throws Exception;
}
