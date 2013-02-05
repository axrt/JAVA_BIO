package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

//TODO: document 
public class UtilFileOperator_Helper {
	/**
	 * Private constructor, prevents instantiation
	 */
	private UtilFileOperator_Helper() {
		throw new AssertionError();
	}

	public static String MD5 = "MD5";

	/**
	 * Code partially used from <a
	 * href="http://www.mkyong.com/java/java-md5-hashing-example/">mkyong's
	 * awesome page</a>.
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getFileMD5(File file) throws IOException,
			NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance(MD5);
		FileInputStream fileInputStream = new FileInputStream(file);

		byte[] rawBytes = new byte[1024];

		int counter = 0;
		while ((counter = fileInputStream.read(rawBytes)) != -1) {
			md.update(rawBytes, 0, counter);
		}

		byte[] md5Bytes = md.digest();
		fileInputStream.close();
		return md5Bytes;
	}

	/**
	 * Code partially used from <a
	 * href="http://www.mkyong.com/java/java-md5-hashing-example/">mkyong's
	 * awesome page</a>.
	 * 
	 * @param md5Bytes
	 * @return
	 */
	public static String MD5ToString(byte[] md5Bytes) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			String hex = Integer.toHexString(0xff & md5Bytes[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return new String(hexString);
	}

	/**
	 * Code partially used from <a
	 * href="http://www.mkyong.com/java/how-to-compress-files-in-zip-format/"
	 * >mkyong's awesome page</a>.
	 * 
	 * @param files
	 * @param zipFile
	 * @throws IOException
	 */
	public static void ZIPFiles(File[] files, File zipFile) throws IOException {
		byte[] buffer = new byte[1024];

		ZipOutputStream zipOutputStream = new ZipOutputStream(
				new FileOutputStream(zipFile));

		FileInputStream fileInputStream;
		ZipEntry zipEntry;
		for (int i = 0; i < files.length; i++) {

			zipEntry = new ZipEntry(files[i].getPath());
			zipOutputStream.putNextEntry(zipEntry);

			fileInputStream = new FileInputStream(files[i]);

			int len;
			while ((len = fileInputStream.read(buffer)) > 0) {
				zipOutputStream.write(buffer, 0, len);
			}

			zipOutputStream.close();
		}
	}

	/**
	 * Code partially used from <a href=
	 * "http://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/"
	 * >mkyong's awesome page</a>.
	 * 
	 * @param zipFile
	 * @param outputFolder
	 * @throws IOException
	 */
	public void UNZIPFiles(File zipFile, File outputFolder) throws IOException {
		if (!outputFolder.isDirectory()) {
			throw new AssertionError(outputFolder.getPath()
					+ " is not a valid directory!");
		}
		if (!outputFolder.exists()) {
			outputFolder.mkdir();
		}
		byte[] buffer = new byte[1024];
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(
				zipFile));

		ZipEntry zipEntry = zipInputStream.getNextEntry();
		String fileName;
		File newFile;
		FileOutputStream fileOutputStream;
		while (zipEntry != null) {

			fileName = zipEntry.getName();
			newFile = new File(outputFolder + SystemUtil.SysFS + fileName);

			new File(newFile.getParent()).mkdirs();

			fileOutputStream = new FileOutputStream(newFile);

			int len;
			while ((len = zipInputStream.read(buffer)) > 0) {
				fileOutputStream.write(buffer, 0, len);
			}

			fileOutputStream.close();
			zipEntry = zipInputStream.getNextEntry();
		}

		zipInputStream.closeEntry();
		zipInputStream.close();

	}

	public static Map<String, String> MD5FileMap(File[] filesToMap)
			throws NoSuchAlgorithmException, IOException {
		Map<String, String> MD5FM = new HashMap<String, String>();
		for (int i = 0; i < filesToMap.length; i++) {
			MD5FM.put(filesToMap[i].getName(), UtilFileOperator_Helper
					.MD5ToString(UtilFileOperator_Helper
							.getFileMD5(filesToMap[i])));
		}

		return MD5FM;
	}

	public static boolean fileConfirmsMD5(String hexMD5, File file)
			throws NoSuchAlgorithmException, IOException {
		if (UtilFileOperator_Helper.MD5ToString(
				UtilFileOperator_Helper.getFileMD5(file)).equals(hexMD5)) {
			return true;
		} else {
			return false;
		}
	}
}
