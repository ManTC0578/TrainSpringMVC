package com.banvien.myplatform.core;


/**
 * Constant values used throughout the application.
 *
 */
public final class Constants {

    private Constants() {
        // hide me
    }
    //~ Static fields/initializers =============================================
    
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    /**
     * The Alphabet number for search query
     */
    public static final String ALPHABET_SEARCH_PREFIX = "^$^";
    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute that holds the list form
     */
    public static final String LIST_MODEL_KEY = "items";
    
    /**
     * The request scope attribute that holds the form
     */
    public static final String FORM_MODEL_KEY = "item";
    

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ROLE_ADMIN = "ADMIN";
    
    public static final String ROLE_USER = "USER";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME = "csstheme";
    
    /**
     * Sort direction constants
     */
    public static final String SORT_ASC = "2";
    public static final String SORT_DESC = "1";
    
    /**
     * User status constants
     */
    public static final byte USER_ACTIVE = 1;
    public static final byte USER_INACTIVE = 2;
    public static final byte USER_DISABLED = 3;
    public static final byte USER_IS_UNLIMITED = 1;
    
    /**
     *  Action Request
     */
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_SEARCH = "search";
    public static final String ACTION_INSERT_UPDATE = "insert-update";
    public static final String ACTION_IMPORT = "import";

    public static final String MESSAGE_RESPONSE_MODEL_KEY = "messageResponse";

    /**
     * Cookie for web and content security
     */
    public static final String LOGIN_USER_ID_COOKIE = "j_loggined_userid";
    public static final String LOGIN_CHECKSUM = "j_loginned_checksum";

    /**
     * Acegi security constants
     */
    
    public static final String ACEGI_SECURITY_FORM_USERNAME_KEY = "j_username";
	public static final String ACEGI_SECURITY_FORM_PASSWORD_KEY = "j_password";
	public static final String ACEGI_SECURITY_LAST_USERNAME_KEY = "ACEGI_SECURITY_LAST_USERNAME";
	

    /**
     * SESSION KEY
     */

    public static final String GLOBAL_META_ROLE_PREFIX = "GLOBAL_META_ROLE_PREFIX";
    
    public static final byte TRUE_BYTE_VAKUE = 1;
}