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
