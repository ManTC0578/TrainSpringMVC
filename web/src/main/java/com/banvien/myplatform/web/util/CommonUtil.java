package com.banvien.myplatform.web.util;


import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class CommonUtil {
    public static String getBaseFolder() {
        return "/files/";
    }

    public static String getCommonFolderName() {
        return "common";
    }

    public static String getImageFolderName() {
        return "image";
    }

    public static String getFlashFolderName() {
        return "flash";
    }

    public static String getTempFolderName() {
        return "temp";
    }

    public static String getOthersFolderName() {
        return "others";
    }

    /**
     * Helper function to verify if a file extension is allowed or not allowed.
     *
     * @param fileType -
     * @param ext      -
     * @return b
     */
    public static boolean extIsAllowed(String fileType, String ext) {
        List allowList;
        List denyList;
        if (getImageFolderName().equalsIgnoreCase(fileType)) {
            allowList = stringToArrayList("jpg|gif|jpeg|png|bmp");
            denyList = stringToArrayList("");
        } else if (getFlashFolderName().equalsIgnoreCase(fileType)) {
            allowList = stringToArrayList("swf|fla");
            denyList = stringToArrayList("");
        } else {
            allowList = new ArrayList();//stringToArrayList("");
            denyList = stringToArrayList("html|htm|php|php2|php3|php4|php5|phtml|pwml|inc|asp|aspx|ascx|jsp|cfm|cfc|pl|bat|exe|com|dll|vbs|js|reg|cgi|htaccess|asis");
        }

        ext = ext.toLowerCase();
        return allowList.size() == 0 ? !denyList.contains(ext) : denyList
                .size() == 0
                && allowList.contains(ext);

    }

    public static boolean extIsAllowedImage(String fileType, String ext) {
        List allowList;
        List denyList;
        if (getImageFolderName().equalsIgnoreCase(fileType)) {
            allowList = stringToArrayList("jpg|gif|jpeg|png|bmp");
            denyList = stringToArrayList("");
        } else if (getFlashFolderName().equalsIgnoreCase(fileType)) {
            allowList = stringToArrayList("swf|fla");
            denyList = stringToArrayList("");
        } else {
            allowList = stringToArrayList("");
            denyList = stringToArrayList("html|htm|php|php2|php3|php4|php5|phtml|pwml|inc|asp|aspx|ascx|jsp|cfm|cfc|pl|bat|exe|com|dll|vbs|js|reg|cgi|htaccess|asis");
        }

        ext = ext.toLowerCase();
        return allowList.size() == 0 ? !denyList.contains(ext) : denyList
                .size() == 0
                && allowList.contains(ext);

    }

    /**
     * Helper function to verify if a file extension is allowed or not allowed
     * for attachment file
     *
     * @param fileType -
     * @param ext      -
     * @return b
     */
    public static boolean extIsAllowedForAttachment(String fileType, String ext) {
        List allowList;
        List denyList;
        if (getOthersFolderName().equalsIgnoreCase(fileType)) {
            allowList = stringToArrayList("doc|pdf|excel");
            denyList = stringToArrayList("");
        } else if (getFlashFolderName().equalsIgnoreCase(fileType)) {
            allowList = stringToArrayList("swf|fla");
            denyList = stringToArrayList("");
        } else {
            allowList = new ArrayList();//stringToArrayList("");
            denyList = stringToArrayList("html|htm|php|php2|php3|php4|php5|phtml|pwml|inc|asp|aspx|ascx|jsp|cfm|cfc|pl|bat|exe|com|dll|vbs|js|reg|cgi|htaccess|asis");
        }

        ext = ext.toLowerCase();
        return allowList.size() == 0 ? !denyList.contains(ext) : denyList
                .size() == 0
                && allowList.contains(ext);

    }

    /**
     * Helper function to convert the configuration string to an ArrayList.
     *
     * @param str -
     * @return list
     */
    private static ArrayList stringToArrayList(String str) {

        ArrayList<String> tmp = new ArrayList<String>();
        if (str == null || str.trim().length() == 0) {
            String[] strArr = str.split("\\|");
            for (String aStrArr : strArr) {
                tmp.add(aStrArr.toLowerCase());
            }
        }
        return tmp;
    }

    /*
      * get fileName without extension
      */

    public static String getNameWithoutExtension(String fileName) {
        return (fileName.indexOf(".") > 0) ? fileName.substring(0, fileName
                .lastIndexOf(".")) : fileName;
    }

    /*
      * get extension of file
      */

    public static String getExtension(String fileName) {
        return (fileName.indexOf(".") < fileName.length()) ? fileName
                .substring(fileName.lastIndexOf(".") + 1) : "";
    }

    /**
     * @return common Path folder in WEB-INF
     * @author phanthanhson create a directory
     */
    public static String getCommonPath(HttpServletRequest request) {
        String baseDir = CommonUtil.getBaseFolder();
        ServletContext context = request.getSession().getServletContext();
        String realBaseDir = context.getRealPath(baseDir);
        File baseFile = new File(realBaseDir);
        if (!baseFile.exists()) {
            baseFile.mkdir();

        }
        // Get commonPath in String form
        String commonPath = getBaseFolder() + getCommonFolderName();
        return commonPath;
    }

    public static String getCurrentPath(HttpServletRequest request) throws IOException, ServletException {

        // Check type file is allowed
        // String typeStr = request.getParameter("Type");

        String currentPath = getCommonPath(request);
        // Create a directory with path "/type/" in WEB-INF
        
        currentPath = request.getContextPath() + currentPath;
        return currentPath;

    }

    public static String removeDiacritic(String str) {
		String result = Normalizer.normalize(str, Normalizer.Form.NFD);
		result = result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		result = result.replace('đ', 'd');
		result = result.replace('Đ', 'D');
		result = result.replaceAll("[^a-z A-Z0-9-]", "");
		return result;
	}

    public static String transformFilenameWithExtension(String filename) {
        String filenameOnly = removeDiacritic(getNameWithoutExtension(filename));
        String ext = getExtension(filename);
        return filenameOnly + (StringUtils.isNotEmpty(ext) ? ("." + ext) : "");
    }

    public static String getCurrentDirPath(HttpServletRequest request,
                                           HttpServletResponse response) throws IOException, ServletException {
        // Get realpath in WEB-INF
        ServletContext context = request.getSession().getServletContext();
        String currentDirPath = context.getRealPath(getCurrentPath(request));

        // Creae a file or directory object
        File currentDir = new File(currentDirPath);

        // create directory "/type/" if not exist
        if (!currentDir.exists()) {
            currentDir.mkdir();
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        return currentDirPath;
    }

    public static String getCommonDirPath(HttpServletRequest request) {
        ServletContext context = request.getSession().getServletContext();

        // Get realpath in WEB-INF
        String commonDirPath = context.getRealPath(getCommonPath(request));

        // Create a directory
        File commonDir = new File(commonDirPath);
        if (!commonDir.exists()) {
            commonDir.mkdir();
        }
        return commonDirPath;
    }


    public static String generateUUID() {
        return UUID.randomUUID().toString();

    }

    public static String randomstring(int lo, int hi) {
        int n = rand(lo, hi);
        byte b[] = new byte[n];
        for (int i = 0; i < n; i++) {
            b[i] = (byte) rand('a', 'z');
        }
        return new String(b, 0);
    }

    public static int rand(int lo, int hi) {
        Random rn = new Random();
        int n = hi - lo + 1;
        int i = rn.nextInt() % n;
        if (i < 0)
            i = -i;
        return lo + i;
    }

    private static final Pattern EMAIL_P = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern ZIP_P = Pattern.compile("\\d{5}(-\\d{4})?");
    private static final Pattern USERNAME_P = Pattern.compile("^[A-Za-z0-9_-]{3,25}");
    private static final Pattern PROJECTNAME_P = Pattern.compile("^[A-Za-z0-9_-]*");


    public static boolean isValidEmail(String email) {
        Matcher m = EMAIL_P.matcher(email);
        return m.matches();
    }

    public static boolean isValidZip(String email) {
        Matcher m = ZIP_P.matcher(email);
        return m.matches();
    }

    public static boolean isValidUsername(String username) {
        Matcher m = USERNAME_P.matcher(username);
        return m.matches();
    }

    public static boolean isValidProjectName(String username) {
        Matcher m = PROJECTNAME_P.matcher(username);
        return m.matches();
    }

    /**
     * Trims, removes line breaks, multiple spaces and generally cleans text before processing.
     * @param   input      Text to be transformed
     */
    public static String cleanHtmlTags(String input) {
        try{
            //Remove math expression
            input = input.replaceAll("<span class=\"AM\">`", "");
            input = input.replaceAll("`</span>", " ");
            List<String> res = extractText(new StringReader(input));
            input = StringUtils.join(res, ' ');
        }catch (IOException ex) {

        }

        return input;
    }
    public static List<String> extractText(Reader reader) throws IOException {
        final ArrayList<String> list = new ArrayList<String>();

        ParserDelegator parserDelegator = new ParserDelegator();
        HTMLEditorKit.ParserCallback parserCallback = new HTMLEditorKit.ParserCallback() {
            public void handleText(final char[] data, final int pos) {
                list.add(new String(data));
            }
            public void handleStartTag(HTML.Tag tag, MutableAttributeSet attribute, int pos) {}
            public void handleEndTag(HTML.Tag t, final int pos) {  }
            public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, final int pos) {
                if (t.toString().equalsIgnoreCase("img")) {
                    list.add("###");
                }
            }
            public void handleComment(final char[] data, final int pos) { }
            public void handleError(final java.lang.String errMsg, final int pos) { }
        };
        parserDelegator.parse(reader, parserCallback, true);
        return list;
    }
}
