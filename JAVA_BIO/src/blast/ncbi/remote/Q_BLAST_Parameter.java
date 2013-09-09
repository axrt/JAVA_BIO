package blast.ncbi.remote;
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
 * An abstract parameter key-value pare for a QBLAST request
 * @author axrt
 *
 */
public abstract class Q_BLAST_Parameter {
	/**
	 * Key name
	 */
	protected final String key;
	/**
	 * Value
	 */
	protected final String value;

	/**
	 * Constructor 
	 * @param key {@link String} key
	 * @param value {@link String} value
	 */
	protected Q_BLAST_Parameter(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	/**
	 * @return the {@link String} key.
	 */
	public String getKey() {
		return this.key;
	}
	/**
	 * @return the {@link String} value.
	 */
	public String getValue() {
		return this.value;
	}
}
