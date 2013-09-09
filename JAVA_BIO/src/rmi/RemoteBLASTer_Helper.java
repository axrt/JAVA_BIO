package rmi;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import util.UtilFileOperator_Helper;
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
//TODO: document
public class RemoteBLASTer_Helper {

	private RemoteBLASTer_Helper() {
		throw new AssertionError();
	}

	public static final String rmi_sever_hostname = "java.rmi.server.hostname";
    
	
	public static List<String> checkDatabaseFiles(
			Map<String, String> MD5FileMap, File dbDirectory)
			throws NoSuchAlgorithmException, IOException {
		if (!dbDirectory.isDirectory()) {
			throw new AssertionError(dbDirectory.getPath()
					+ " is not a valid directory!");
		}
		List<String> lackingFiles = new ArrayList<>();
		Map<String, File> fileNames = new HashMap<>();
		File[] files = dbDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                fileNames.put(file.getName(), file);
            }
        }
        Iterator<Map.Entry<String, String>> it = MD5FileMap.entrySet()
				.iterator();
		Map.Entry<String, String> pairs;
		while (it.hasNext()) {
			pairs = it.next();
			if (!fileNames.containsKey(pairs.getKey())) {
				lackingFiles.add(pairs.getKey());
			} else if (!UtilFileOperator_Helper.fileConfirmsMD5(
					pairs.getValue(), fileNames.get(pairs.getKey()))) {
				lackingFiles.add(pairs.getKey());
			}
		}
		return lackingFiles;
	}
}
