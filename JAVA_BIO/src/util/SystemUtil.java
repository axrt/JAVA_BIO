package util;

import java.io.File;

public enum SystemUtil {
	Instance;
	private SystemUtil() {

	}
	
	/**
     * System file system path separator
     */
    public static final String SysFS=System.getProperty("file.separator");
    /**
     * User home directory
     */
    public static final String userHomeDir = System.getProperty("user.home", ".");
    /**
     * Platform (OS) name
     */
    public static final String platform=System.getProperty("os.name");
    /**
     * Capacity here is "32/64 bit" feature of the OS 
     */
    public static final String capacity=System.getProperty("sun.arch.data.model");
    /**
     * A path to the jar that is being executed
     */
    public static final String jarPath = new File(SystemUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParent();
}
