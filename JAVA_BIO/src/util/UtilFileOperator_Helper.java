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
	 * @param file {@link File} to get MD5 sum from
	 * @return {@code byte[]} from file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getFileMD5(File file) throws IOException,
			NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance(MD5);
		FileInputStream fileInputStream = new FileInputStream(file);

		byte[] rawBytes = new byte[1024];

		int counter;
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
	 * @param md5Bytes {@code byte[]} to get the MD5 from
	 * @return {@link String} representation of MD5
	 */
	public static String MD5ToString(byte[] md5Bytes) {
		StringBuffer hexString = new StringBuffer();
        for (byte md5Byte : md5Bytes) {
            String hex = Integer.toHexString(0xff & md5Byte);
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
	 * @param files {@link File} that points to the file to zip
	 * @param zipFile {@link File} that points to the target archive file
	 * @throws IOException
	 */
	public static void ZIPFiles(File[] files, File zipFile) throws IOException {
		byte[] buffer = new byte[1024];

		ZipOutputStream zipOutputStream = new ZipOutputStream(
				new FileOutputStream(zipFile));

		FileInputStream fileInputStream;
		ZipEntry zipEntry;
        for (File file : files) {

            zipEntry = new ZipEntry(file.getPath());
            zipOutputStream.putNextEntry(zipEntry);

            fileInputStream = new FileInputStream(file);

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
	 * @param zipFile  {@link File} that points to the zip file
	 * @param outputFolder {@link File} that points to the output directory
	 * @throws IOException
	 */
	@SuppressWarnings("ResultOfMethodCallIgnored")
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
		Map<String, String> MD5FM = new HashMap<>();
        for (File aFilesToMap : filesToMap) {
            MD5FM.put(aFilesToMap.getName(), UtilFileOperator_Helper
                    .MD5ToString(UtilFileOperator_Helper
                            .getFileMD5(aFilesToMap)));
        }

		return MD5FM;
	}

	public static boolean fileConfirmsMD5(String hexMD5, File file)
			throws NoSuchAlgorithmException, IOException {
        return UtilFileOperator_Helper.MD5ToString(
                UtilFileOperator_Helper.getFileMD5(file)).equals(hexMD5);
	}
}
