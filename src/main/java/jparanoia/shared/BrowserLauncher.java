package jparanoia.shared;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BrowserLauncher {
    private static final String JDirect_MacOSX = "/System/Library/Frameworks/Carbon.framework/Frameworks/HIToolbox.framework/HIToolbox";
    private static final int MRJ_2_0 = 0;
    private static final int MRJ_2_1 = 1;
    private static final int MRJ_3_0 = 3;
    private static final int MRJ_3_1 = 4;
    private static final int WINDOWS_NT = 5;
    private static final int WINDOWS_9x = 6;
    private static final int OTHER = -1;
    private static final String FINDER_TYPE = "FNDR";
    private static final String FINDER_CREATOR = "MACS";
    private static final String GURL_EVENT = "GURL";
    private static final String FIRST_WINDOWS_PARAMETER = "/c";
    private static final String SECOND_WINDOWS_PARAMETER = "start";
    private static final String THIRD_WINDOWS_PARAMETER = "\"\"";
    private static final String NETSCAPE_REMOTE_PARAMETER = "-remote";
    private static final String NETSCAPE_OPEN_PARAMETER_START = "'openURL(";
    private static final String NETSCAPE_OPEN_PARAMETER_END = ")'";
    private static int jvm;
    private static Object browser;
    private static boolean loadedWithoutErrors = true;
    private static Class mrjFileUtilsClass;
    private static Class mrjOSTypeClass;
    private static Class aeDescClass;
    private static Constructor aeTargetConstructor;
    private static Constructor appleEventConstructor;
    private static Constructor aeDescConstructor;
    private static Method findFolder;
    private static Method getFileCreator;
    private static Method getFileType;
    private static Method openURL;
    private static Method makeOSType;
    private static Method putParameter;
    private static Method sendNoReply;
    private static Object kSystemFolderType;
    private static Integer keyDirectObject;
    private static Integer kAutoGenerateReturnID;
    private static Integer kAnyTransactionID;
    private static Object linkage;
    private static String errorMessage;

    static {
        String str1 = System.getProperty( "os.name" );
        if ( str1.startsWith( "Mac OS" ) ) {
            String str2 = System.getProperty( "mrj.version" );
            String str3 = str2.substring( 0, 3 );
            try {
                double d = Double.valueOf( str3 );
                if ( d == 2.0D ) {
                    jvm = 0;
                } else if ( d >= 2.1D && d < 3.0D ) {
                    jvm = 1;
                } else if ( d == 3.0D ) {
                    jvm = 3;
                } else if ( d >= 3.1D ) {
                    jvm = 4;
                } else {
                    loadedWithoutErrors = false;
                    errorMessage = "Unsupported MRJ version: " + d;
                }
            } catch ( NumberFormatException localNumberFormatException ) {
                loadedWithoutErrors = false;
                errorMessage = "Invalid MRJ version: " + str2;
            }
        } else if ( str1.startsWith( "Windows" ) ) {
            if ( str1.contains( "9" ) ) {
                jvm = 6;
            } else {
                jvm = 5;
            }
        } else {
            jvm = -1;
        }
        if ( loadedWithoutErrors ) {
            loadedWithoutErrors = loadClasses();
        }
    }

    private static boolean loadClasses() {
        Object localObject;
        switch ( jvm ) {
            case 0:
                try {
                    Class localClass1 = Class.forName( "com.apple.MacOS.AETarget" );
                    localObject = Class.forName( "com.apple.MacOS.OSUtils" );
                    Class localClass3 = Class.forName( "com.apple.MacOS.AppleEvent" );
                    Class localClass4 = Class.forName( "com.apple.MacOS.ae" );
                    aeDescClass = Class.forName( "com.apple.MacOS.AEDesc" );
                    aeTargetConstructor = localClass1.getDeclaredConstructor( Integer.TYPE );
                    appleEventConstructor = localClass3.getDeclaredConstructor( Integer.TYPE, Integer.TYPE, localClass1, Integer.TYPE, Integer.TYPE );
                    aeDescConstructor = aeDescClass.getDeclaredConstructor( String.class );
                    makeOSType = ( (Class) localObject ).getDeclaredMethod( "makeOSType", String.class );
                    putParameter = localClass3.getDeclaredMethod( "putParameter", Integer.TYPE, aeDescClass );
                    sendNoReply = localClass3.getDeclaredMethod( "sendNoReply" );
                    Field localField2 = localClass4.getDeclaredField( "keyDirectObject" );
                    keyDirectObject = (Integer) localField2.get( null );
                    Field localField3 = localClass3.getDeclaredField( "kAutoGenerateReturnID" );
                    kAutoGenerateReturnID = (Integer) localField3.get( null );
                    Field localField4 = localClass3.getDeclaredField( "kAnyTransactionID" );
                    kAnyTransactionID = (Integer) localField4.get( null );
                } catch ( ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException localClassNotFoundException1 ) {
                    errorMessage = localClassNotFoundException1.getMessage();
                    return false;
                }
            case 1:
                try {
                    mrjFileUtilsClass = Class.forName( "com.apple.mrj.MRJFileUtils" );
                    mrjOSTypeClass = Class.forName( "com.apple.mrj.MRJOSType" );
                    Field localField1 = mrjFileUtilsClass.getDeclaredField( "kSystemFolderType" );
                    kSystemFolderType = localField1.get( null );
                    findFolder = mrjFileUtilsClass.getDeclaredMethod( "findFolder", mrjOSTypeClass );
                    getFileCreator = mrjFileUtilsClass.getDeclaredMethod( "getFileCreator", File.class );
                    getFileType = mrjFileUtilsClass.getDeclaredMethod( "getFileType", File.class );
                } catch ( ClassNotFoundException | IllegalAccessException | SecurityException | NoSuchMethodException | NoSuchFieldException localClassNotFoundException2 ) {
                    errorMessage = localClassNotFoundException2.getMessage();
                    return false;
                }
            case 3:
                try {
                    Class localClass2 = Class.forName( "com.apple.mrj.jdirect.Linker" );
                    localObject = localClass2.getConstructor( Class.class );
                    linkage = ( (Constructor) localObject ).newInstance( BrowserLauncher.class );
                } catch ( ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException localClassNotFoundException3 ) {
                    errorMessage = localClassNotFoundException3.getMessage();
                    return false;
                }
            case 4:
                try {
                    mrjFileUtilsClass = Class.forName( "com.apple.mrj.MRJFileUtils" );
                    openURL = mrjFileUtilsClass.getDeclaredMethod( "openURL", String.class );
                } catch ( ClassNotFoundException | NoSuchMethodException localClassNotFoundException4 ) {
                    errorMessage = localClassNotFoundException4.getMessage();
                    return false;
                }
        }
        return true;
    }

    public static void openURL( String paramString ) throws IOException {
        if ( !loadedWithoutErrors ) {
            throw new IOException( "Exception in finding browser: " + errorMessage );
        }
        Object localObject1 = locateBrowser();
        if ( localObject1 == null ) {
            throw new IOException( "Unable to locate browser: " + errorMessage );
        }
        Process localProcess;
        switch ( jvm ) {
            case 0:
                Object localObject2 = null;
                try {
                    localObject2 = aeDescConstructor.newInstance( paramString );
                    putParameter.invoke( localObject1, keyDirectObject, localObject2 );
                    sendNoReply.invoke( localObject1 );
                } catch ( InvocationTargetException localInvocationTargetException1 ) {
                    throw new IOException( "InvocationTargetException while creating AEDesc: " +
                            localInvocationTargetException1.getMessage() );
                } catch ( IllegalAccessException localIllegalAccessException1 ) {
                    throw new IOException( "IllegalAccessException while building AppleEvent: " +
                            localIllegalAccessException1.getMessage() );
                } catch ( InstantiationException localInstantiationException ) {
                    throw new IOException( "InstantiationException while creating AEDesc: " +
                            localInstantiationException.getMessage() );
                } finally {
                    localObject2 = null;
                    localObject1 = null;
                }
            case 1:
                Runtime.getRuntime().exec( new String[]{(String) localObject1, paramString} );
                break;
            case 3:
                int[] arrayOfInt1 = new int[1];
                int i = ICStart( arrayOfInt1, 0 );
                if ( i == 0 ) {
                    int[] arrayOfInt2 = {0};
                    byte[] arrayOfByte = paramString.getBytes();
                    int[] arrayOfInt3 = {arrayOfByte.length};
                    i = ICLaunchURL( arrayOfInt1[0], new byte[]{0}, arrayOfByte, arrayOfByte.length, arrayOfInt2, arrayOfInt3 );
                    if ( i == 0 ) {
                        ICStop( arrayOfInt1 );
                    } else {
                        throw new IOException( "Unable to launch URL: " + i );
                    }
                } else {
                    throw new IOException( "Unable to create an Internet Config instance: " + i );
                }
                break;
            case 4:
                try {
                    openURL.invoke( null, paramString );
                } catch ( InvocationTargetException localInvocationTargetException2 ) {
                    throw new IOException( "InvocationTargetException while calling openURL: " +
                            localInvocationTargetException2.getMessage() );
                } catch ( IllegalAccessException localIllegalAccessException2 ) {
                    throw new IOException( "IllegalAccessException while calling openURL: " +
                            localIllegalAccessException2.getMessage() );
                }
            case 5:
            case 6:
                localProcess = Runtime.getRuntime()
                        .exec( new String[]{(String) localObject1, "/c", "start", "\"\"", '"' + paramString + '"'} );
                try {
                    localProcess.waitFor();
                    localProcess.exitValue();
                } catch ( InterruptedException localInterruptedException1 ) {
                    throw new IOException( "InterruptedException while launching browser: " +
                            localInterruptedException1.getMessage() );
                }
            case -1:
                localProcess = Runtime.getRuntime()
                        .exec( new String[]{(String) localObject1, "-remote", "'openURL(" + paramString + ")'"} );
                try {
                    int j = localProcess.waitFor();
                    if ( j != 0 ) {
                        Runtime.getRuntime().exec( new String[]{(String) localObject1, paramString} );
                    }
                } catch ( InterruptedException localInterruptedException2 ) {
                    throw new IOException( "InterruptedException while launching browser: " +
                            localInterruptedException2.getMessage() );
                }
            case 2:
            default:
                Runtime.getRuntime().exec( new String[]{(String) localObject1, paramString} );
        }
    }

    private static Object locateBrowser() {
        if ( browser != null ) {
            return browser;
        }
        Object localObject2;
        switch ( jvm ) {
            case 0:
                try {
                    Integer localInteger1 = (Integer) makeOSType.invoke( null, new Object[]{"MACS"} );
                    Object localObject1 = aeTargetConstructor.newInstance( localInteger1 );
                    Integer localInteger2 = (Integer) makeOSType.invoke( null, new Object[]{"GURL"} );
                    return appleEventConstructor.newInstance( localInteger2, localInteger2, localObject1, kAutoGenerateReturnID, kAnyTransactionID );
                } catch ( IllegalAccessException | InvocationTargetException | InstantiationException localIllegalAccessException1 ) {
                    browser = null;
                    errorMessage = localIllegalAccessException1.getMessage();
                    return browser;
                }
            case 1:
                File localFile;
                try {
                    localFile = (File) findFolder.invoke( null, new Object[]{kSystemFolderType} );
                } catch ( IllegalArgumentException | IllegalAccessException localIllegalArgumentException1 ) {
                    browser = null;
                    errorMessage = localIllegalArgumentException1.getMessage();
                    return browser;
                } catch ( InvocationTargetException localInvocationTargetException2 ) {
                    browser = null;
                    errorMessage = localInvocationTargetException2.getTargetException().getClass() +
                            ": " +
                            localInvocationTargetException2.getTargetException().getMessage();
                    return browser;
                }
                String[] arrayOfString = localFile.list();
                for ( int i = 0; i < (arrayOfString != null ? arrayOfString.length : 0); i++ ) {
                    try {
                        localObject2 = new File( localFile, arrayOfString[i] );
                        if ( ( (File) localObject2 ).isFile() ) {
                            Object localObject3 = getFileType.invoke( null, localObject2 );
                            if ( "FNDR".equals( localObject3.toString() ) ) {
                                Object localObject4 = getFileCreator.invoke( null, localObject2 );
                                if ( "MACS".equals( localObject4.toString() ) ) {
                                    browser = localObject2.toString();
                                    return browser;
                                }
                            }
                        }
                    } catch ( IllegalArgumentException localIllegalArgumentException2 ) {
                        browser = browser;
                        errorMessage = localIllegalArgumentException2.getMessage();
                        return null;
                    } catch ( IllegalAccessException localIllegalAccessException3 ) {
                        browser = null;
                        errorMessage = localIllegalAccessException3.getMessage();
                        return browser;
                    } catch ( InvocationTargetException localInvocationTargetException3 ) {
                        browser = null;
                        errorMessage = localInvocationTargetException3.getTargetException().getClass() +
                                ": " +
                                localInvocationTargetException3.getTargetException().getMessage();
                        return browser;
                    }
                }
                browser = null;
                break;
            case 3:
            case 4:
                browser = "";
                break;
            case 5:
                browser = "cmd.exe";
                break;
            case 6:
                browser = "command.com";
                break;
        }
        browser = "netscape";
        return browser;
    }

    private static native int ICStart( int[] paramArrayOfInt, int paramInt );

    private static native int ICStop( int[] paramArrayOfInt );

    private static native int ICLaunchURL( int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2 );
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\BrowserLauncher.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
