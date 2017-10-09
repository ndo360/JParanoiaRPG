/*      */ package net.roydesign.mac;
/*      */ 
/*      */ import com.apple.eio.FileManager;
/*      */ import com.apple.mrj.MRJFileUtils;
/*      */ import com.apple.mrj.MRJOSType;
/*      */ import java.awt.Component;
/*      */ import java.awt.Frame;
/*      */ import java.awt.MenuBar;
/*      */ import java.awt.Window;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.LineNumberReader;
/*      */ import java.io.Reader;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.net.URLClassLoader;
/*      */ import java.util.Properties;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.UIManager;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class MRJAdapter
/*      */   implements MRJFolderConstants
/*      */ {
/*      */   public static float javaVersion;
/*  104 */   public static float mrjVersion = -1.0F;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static ClassLoader cocoaClassLoader;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static String startupDisk;
/*      */   
/*      */ 
/*      */ 
/*      */   private static String applicationPath;
/*      */   
/*      */ 
/*      */ 
/*      */   private static Frame invisibleFrame;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static
/*      */   {
/*  129 */     String str = System.getProperty("java.version");
/*  130 */     javaVersion = new Float(str.substring(0, 3)).floatValue();
/*      */     
/*      */ 
/*  133 */     str = System.getProperty("mrj.version");
/*  134 */     if (str != null)
/*      */     {
/*      */ 
/*      */ 
/*  138 */       int i = str.length();
/*  139 */       int j = str.indexOf('.');
/*  140 */       if ((j != -1) && (j != i - 1))
/*  141 */         j = str.indexOf('.', j + 1);
/*  142 */       if (j == -1)
/*  143 */         j = i;
/*  144 */       mrjVersion = new Float(str.substring(0, j)).floatValue();
/*      */     }
/*      */     
/*      */ 
/*  148 */     if (mrjVersion >= 3.0F)
/*      */     {
/*      */       try
/*      */       {
/*  152 */         cocoaClassLoader = new URLClassLoader(new URL[] { new URL("file://127.0.0.1/System/Library/Java/") });
/*      */       }
/*      */       catch (MalformedURLException localMalformedURLException) {}
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setFileType(File paramFile, String paramString)
/*      */     throws IOException
/*      */   {
/*  183 */     if (mrjVersion >= 4.0F) {
/*  184 */       FileManager.setFileType(paramFile.getAbsolutePath(), osTypeStringToInt(paramString));
/*  185 */     } else if (mrjVersion >= 1.5F) {
/*  186 */       MRJFileUtils.setFileType(paramFile, new MRJOSType(osTypeStringToInt(paramString)));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String getFileType(File paramFile)
/*      */     throws IOException
/*      */   {
/*  206 */     if (mrjVersion >= 4.0F)
/*      */     {
/*  208 */       long l = FileManager.getFileType(paramFile.getAbsolutePath());
/*  209 */       return osTypeIntToString((int)l); }
/*      */     Object localObject;
/*  211 */     if (paramFile.isDirectory())
/*      */     {
/*      */ 
/*  214 */       localObject = new File(paramFile, "Contents/Info.plist");
/*  215 */       if (((File)localObject).exists())
/*      */       {
/*      */ 
/*      */ 
/*  219 */         File localFile = new File(paramFile, "Contents/PkgInfo");
/*  220 */         if (localFile.exists())
/*      */         {
/*  222 */           str = parsePkgInfo(localFile, "type");
/*  223 */           return str == null ? "" : str;
/*      */         }
/*      */         
/*      */ 
/*  227 */         String str = parseInfoPlist((File)localObject, "CFBundlePackageType");
/*  228 */         return str == null ? "" : str;
/*      */       }
/*      */       
/*      */     }
/*  232 */     else if (mrjVersion >= 1.5F)
/*      */     {
/*  234 */       localObject = MRJFileUtils.getFileType(paramFile);
/*  235 */       return ((MRJOSType)localObject).toInt() == 0 ? "" : ((MRJOSType)localObject).toString();
/*      */     }
/*  237 */     return "";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setFileCreator(File paramFile, String paramString)
/*      */     throws IOException
/*      */   {
/*  253 */     if (mrjVersion >= 4.0F) {
/*  254 */       FileManager.setFileCreator(paramFile.getAbsolutePath(), osTypeStringToInt(paramString));
/*  255 */     } else if (mrjVersion >= 1.5F) {
/*  256 */       MRJFileUtils.setFileCreator(paramFile, new MRJOSType(osTypeStringToInt(paramString)));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String getFileCreator(File paramFile)
/*      */     throws IOException
/*      */   {
/*  276 */     if (mrjVersion >= 4.0F)
/*      */     {
/*  278 */       long l = FileManager.getFileCreator(paramFile.getAbsolutePath());
/*  279 */       return osTypeIntToString((int)l); }
/*      */     Object localObject;
/*  281 */     if (paramFile.isDirectory())
/*      */     {
/*      */ 
/*  284 */       localObject = new File(paramFile, "Contents/Info.plist");
/*  285 */       if (((File)localObject).exists())
/*      */       {
/*      */ 
/*      */ 
/*  289 */         File localFile = new File(paramFile, "Contents/PkgInfo");
/*  290 */         if (localFile.exists())
/*      */         {
/*  292 */           str = parsePkgInfo(localFile, "creator");
/*  293 */           return str == null ? "" : str;
/*      */         }
/*      */         
/*      */ 
/*  297 */         String str = parseInfoPlist((File)localObject, "CFBundleSignature");
/*  298 */         return str == null ? "" : str;
/*      */       }
/*      */       
/*      */     }
/*  302 */     else if (mrjVersion >= 1.5F)
/*      */     {
/*  304 */       localObject = MRJFileUtils.getFileCreator(paramFile);
/*  305 */       return ((MRJOSType)localObject).toInt() == 0 ? "" : ((MRJOSType)localObject).toString();
/*      */     }
/*  307 */     return "";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setFileCreatorAndType(File paramFile, String paramString1, String paramString2)
/*      */     throws IOException
/*      */   {
/*  325 */     if (mrjVersion >= 4.0F)
/*      */     {
/*  327 */       FileManager.setFileTypeAndCreator(paramFile.getAbsolutePath(), osTypeStringToInt(paramString2), osTypeStringToInt(paramString1));
/*      */ 
/*      */     }
/*  330 */     else if (mrjVersion >= 1.5F)
/*      */     {
/*  332 */       MRJFileUtils.setFileTypeAndCreator(paramFile, new MRJOSType(osTypeStringToInt(paramString2)), new MRJOSType(osTypeStringToInt(paramString1)));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean setFileLastModified(File paramFile, long paramLong)
/*      */   {
/*  352 */     if (javaVersion >= 1.2F)
/*  353 */       return paramFile.setLastModified(paramLong);
/*  354 */     if (mrjVersion >= 1.5F)
/*  355 */       return MRJFileUtils.setFileLastModified(paramFile, paramLong);
/*  356 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static File findFolder(short paramShort, int paramInt, boolean paramBoolean)
/*      */     throws FileNotFoundException
/*      */   {
/*  382 */     if (mrjVersion >= 4.0F)
/*  383 */       return new File(FileManager.findFolder(paramShort, paramInt, paramBoolean));
/*  384 */     if (mrjVersion >= 3.0F)
/*  385 */       return MRJFileUtils.findFolder(paramShort, new MRJOSType(paramInt), paramBoolean);
/*  386 */     if (mrjVersion >= 1.5F)
/*  387 */       return MRJFileUtils.findFolder(new MRJOSType(paramInt));
/*  388 */     throw new FileNotFoundException();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static File findFolder(short paramShort, String paramString, boolean paramBoolean)
/*      */     throws FileNotFoundException
/*      */   {
/*  417 */     return findFolder(paramShort, osTypeStringToInt(paramString), paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static File findApplication(String paramString)
/*      */     throws FileNotFoundException
/*      */   {
/*  434 */     if (mrjVersion >= 3.0F)
/*      */     {
/*      */       try
/*      */       {
/*      */ 
/*  439 */         StringBuffer localStringBuffer = new StringBuffer();
/*  440 */         localStringBuffer.append("tell application \"Finder\" to get POSIX path of (application file id \"");
/*  441 */         localStringBuffer.append(paramString);
/*  442 */         localStringBuffer.append("\" as alias)");
/*  443 */         return new File(runAppleScript(localStringBuffer.toString()));
/*      */ 
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException) {}
/*      */ 
/*      */     }
/*  450 */     else if (mrjVersion >= 1.5F)
/*      */     {
/*  452 */       return MRJFileUtils.findApplication(new MRJOSType(osTypeStringToInt(paramString)));
/*      */     }
/*  454 */     throw new FileNotFoundException();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static File getBundleResource(String paramString)
/*      */     throws FileNotFoundException
/*      */   {
/*  471 */     if (mrjVersion >= 4.0F)
/*  472 */       return new File(FileManager.getResource(paramString));
/*  473 */     if (mrjVersion >= 3.0F)
/*  474 */       return MRJFileUtils.getResource(paramString);
/*  475 */     throw new FileNotFoundException();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static File getBundleResource(String paramString1, String paramString2)
/*      */     throws FileNotFoundException
/*      */   {
/*  494 */     if (mrjVersion >= 4.0F)
/*  495 */       return new File(FileManager.getResource(paramString1, paramString2));
/*  496 */     if (mrjVersion >= 3.0F)
/*  497 */       return MRJFileUtils.getResource(paramString1, paramString2);
/*  498 */     throw new FileNotFoundException();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void openURL(String paramString)
/*      */     throws IOException
/*      */   {
/*  514 */     if (mrjVersion >= 4.0F)
/*      */     {
/*  516 */       FileManager.openURL(paramString);
/*      */     }
/*  518 */     else if (mrjVersion >= 2.2F)
/*      */     {
/*  520 */       MRJFileUtils.openURL(paramString);
/*      */     }
/*  522 */     else if (mrjVersion >= 1.5F)
/*      */     {
/*  524 */       File localFile = MRJFileUtils.findApplication(new MRJOSType("MACS"));
/*  525 */       Runtime.getRuntime().exec(new String[] { localFile.getPath(), paramString });
/*      */     }
/*      */     else
/*      */     {
/*  529 */       throw new IOException("openURL not supported on this platform");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isAboutAutomaticallyPresent()
/*      */   {
/*  540 */     return mrjVersion != -1.0F;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addAboutListener(ActionListener paramActionListener)
/*      */   {
/*  553 */     addAboutListener(paramActionListener, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addAboutListener(ActionListener paramActionListener, Object paramObject)
/*      */   {
/*  565 */     if (mrjVersion >= 4.0F) {
/*  566 */       MRJ4EventProxy.getInstance().addAboutListener(paramActionListener, paramObject);
/*  567 */     } else if (mrjVersion >= 1.5F) {
/*  568 */       MRJ23EventProxy.getInstance().addAboutListener(paramActionListener, paramObject);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void removeAboutListener(ActionListener paramActionListener)
/*      */   {
/*  577 */     if (mrjVersion >= 4.0F) {
/*  578 */       MRJ4EventProxy.getInstance().removeAboutListener(paramActionListener);
/*  579 */     } else if (mrjVersion >= 1.5F) {
/*  580 */       MRJ23EventProxy.getInstance().removeAboutListener(paramActionListener);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isPreferencesAutomaticallyPresent()
/*      */   {
/*  590 */     return mrjVersion >= 3.0F;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addPreferencesListener(ActionListener paramActionListener)
/*      */   {
/*  603 */     addPreferencesListener(paramActionListener, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addPreferencesListener(ActionListener paramActionListener, Object paramObject)
/*      */   {
/*  615 */     if (mrjVersion >= 4.0F) {
/*  616 */       MRJ4EventProxy.getInstance().addPreferencesListener(paramActionListener, paramObject);
/*  617 */     } else if (mrjVersion >= 3.0F) {
/*  618 */       MRJ23EventProxy.getInstance().addPreferencesListener(paramActionListener, paramObject);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void removePreferencesListener(ActionListener paramActionListener)
/*      */   {
/*  627 */     if (mrjVersion >= 4.0F) {
/*  628 */       MRJ4EventProxy.getInstance().removePreferencesListener(paramActionListener);
/*  629 */     } else if (mrjVersion >= 3.0F) {
/*  630 */       MRJ23EventProxy.getInstance().removePreferencesListener(paramActionListener);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isPreferencesEnabled()
/*      */   {
/*  641 */     if (mrjVersion >= 4.0F)
/*  642 */       return MRJ4EventProxy.getInstance().isPreferencesEnabled();
/*  643 */     if (mrjVersion >= 3.0F)
/*  644 */       return MRJ23EventProxy.getInstance().isPreferencesEnabled();
/*  645 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setPreferencesEnabled(boolean paramBoolean)
/*      */   {
/*  656 */     if (mrjVersion >= 4.0F) {
/*  657 */       MRJ4EventProxy.getInstance().setPreferencesEnabled(paramBoolean);
/*  658 */     } else if (mrjVersion >= 3.0F) {
/*  659 */       MRJ23EventProxy.getInstance().setPreferencesEnabled(paramBoolean);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addOpenApplicationListener(ActionListener paramActionListener)
/*      */   {
/*  672 */     addOpenApplicationListener(paramActionListener, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addOpenApplicationListener(ActionListener paramActionListener, Object paramObject)
/*      */   {
/*  684 */     if (mrjVersion >= 4.0F) {
/*  685 */       MRJ4EventProxy.getInstance().addOpenApplicationListener(paramActionListener, paramObject);
/*  686 */     } else if (mrjVersion >= 2.2F) {
/*  687 */       MRJ23EventProxy.getInstance().addOpenApplicationListener(paramActionListener, paramObject);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void removeOpenApplicationListener(ActionListener paramActionListener)
/*      */   {
/*  696 */     if (mrjVersion >= 4.0F) {
/*  697 */       MRJ4EventProxy.getInstance().removeOpenApplicationListener(paramActionListener);
/*  698 */     } else if (mrjVersion >= 2.2F) {
/*  699 */       MRJ23EventProxy.getInstance().removeOpenApplicationListener(paramActionListener);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addReopenApplicationListener(ActionListener paramActionListener)
/*      */   {
/*  712 */     addReopenApplicationListener(paramActionListener, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addReopenApplicationListener(ActionListener paramActionListener, Object paramObject)
/*      */   {
/*  724 */     if (mrjVersion >= 4.0F) {
/*  725 */       MRJ4EventProxy.getInstance().addReopenApplicationListener(paramActionListener, paramObject);
/*  726 */     } else if (mrjVersion >= 2.2F) {
/*  727 */       MRJ23EventProxy.getInstance().addReopenApplicationListener(paramActionListener, paramObject);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void removeReopenApplicationListener(ActionListener paramActionListener)
/*      */   {
/*  736 */     if (mrjVersion >= 4.0F) {
/*  737 */       MRJ4EventProxy.getInstance().removeReopenApplicationListener(paramActionListener);
/*  738 */     } else if (mrjVersion >= 2.2F) {
/*  739 */       MRJ23EventProxy.getInstance().removeReopenApplicationListener(paramActionListener);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isQuitAutomaticallyPresent()
/*      */   {
/*  749 */     return mrjVersion >= 3.0F;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addQuitApplicationListener(ActionListener paramActionListener)
/*      */   {
/*  762 */     addQuitApplicationListener(paramActionListener, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addQuitApplicationListener(ActionListener paramActionListener, Object paramObject)
/*      */   {
/*  774 */     if (mrjVersion >= 4.0F) {
/*  775 */       MRJ4EventProxy.getInstance().addQuitApplicationListener(paramActionListener, paramObject);
/*  776 */     } else if (mrjVersion >= 1.5F) {
/*  777 */       MRJ23EventProxy.getInstance().addQuitApplicationListener(paramActionListener, paramObject);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void removeQuitApplicationListener(ActionListener paramActionListener)
/*      */   {
/*  786 */     if (mrjVersion >= 4.0F) {
/*  787 */       MRJ4EventProxy.getInstance().removeQuitApplicationListener(paramActionListener);
/*  788 */     } else if (mrjVersion >= 1.5F) {
/*  789 */       MRJ23EventProxy.getInstance().removeQuitApplicationListener(paramActionListener);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addOpenDocumentListener(ActionListener paramActionListener)
/*      */   {
/*  803 */     addOpenDocumentListener(paramActionListener, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addOpenDocumentListener(ActionListener paramActionListener, Object paramObject)
/*      */   {
/*  815 */     if (mrjVersion >= 4.0F) {
/*  816 */       MRJ4EventProxy.getInstance().addOpenDocumentListener(paramActionListener, paramObject);
/*  817 */     } else if (mrjVersion >= 1.5F) {
/*  818 */       MRJ23EventProxy.getInstance().addOpenDocumentListener(paramActionListener, paramObject);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void removeOpenDocumentListener(ActionListener paramActionListener)
/*      */   {
/*  827 */     if (mrjVersion >= 4.0F) {
/*  828 */       MRJ4EventProxy.getInstance().removeOpenDocumentListener(paramActionListener);
/*  829 */     } else if (mrjVersion >= 1.5F) {
/*  830 */       MRJ23EventProxy.getInstance().removeOpenDocumentListener(paramActionListener);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addPrintDocumentListener(ActionListener paramActionListener)
/*      */   {
/*  844 */     addPrintDocumentListener(paramActionListener, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void addPrintDocumentListener(ActionListener paramActionListener, Object paramObject)
/*      */   {
/*  856 */     if (mrjVersion >= 4.0F) {
/*  857 */       MRJ4EventProxy.getInstance().addPrintDocumentListener(paramActionListener, paramObject);
/*  858 */     } else if (mrjVersion >= 1.5F) {
/*  859 */       MRJ23EventProxy.getInstance().addPrintDocumentListener(paramActionListener, paramObject);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void removePrintDocumentListener(ActionListener paramActionListener)
/*      */   {
/*  868 */     if (mrjVersion >= 4.0F) {
/*  869 */       MRJ4EventProxy.getInstance().removePrintDocumentListener(paramActionListener);
/*  870 */     } else if (mrjVersion >= 1.5F) {
/*  871 */       MRJ23EventProxy.getInstance().removePrintDocumentListener(paramActionListener);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean swingUsesScreenMenuBar()
/*      */   {
/*  884 */     boolean bool = false;
/*  885 */     String str1 = UIManager.getLookAndFeel().getClass().getName();
/*  886 */     String str2; if (mrjVersion >= 4.0F)
/*      */     {
/*  888 */       str2 = System.getProperty("apple.laf.useScreenMenuBar");
/*  889 */       bool = (str2 != null) && (str2.equalsIgnoreCase("true")) && (str1.equals("apple.laf.AquaLookAndFeel"));
/*      */ 
/*      */     }
/*  892 */     else if (mrjVersion >= 3.0F)
/*      */     {
/*  894 */       str2 = System.getProperty("com.apple.macos.useScreenMenuBar");
/*  895 */       bool = (str2 != null) && (str2.equalsIgnoreCase("true")) && (str1.equals("com.apple.mrj.swing.MacLookAndFeel"));
/*      */ 
/*      */     }
/*  898 */     else if (mrjVersion != -1.0F)
/*      */     {
/*      */ 
/*  901 */       bool = str1.equals("it.unitn.ing.swing.plaf.macos.MacOSLookAndFeel");
/*      */     }
/*  903 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setFramelessMenuBar(MenuBar paramMenuBar)
/*      */   {
/*      */     try
/*      */     {
/*  921 */       if ((invisibleFrame != null) && (Class.forName("javax.swing.JFrame").isInstance(invisibleFrame)))
/*      */       {
/*      */ 
/*  924 */         invisibleFrame.dispose();
/*  925 */         invisibleFrame = null;
/*      */       }
/*      */     }
/*      */     catch (Exception localException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  936 */     if (mrjVersion >= 4.0F)
/*      */     {
/*  938 */       if (invisibleFrame == null)
/*      */       {
/*  940 */         invisibleFrame = new Frame();
/*  941 */         invisibleFrame.setSize(0, 0);
/*  942 */         invisibleFrame.pack();
/*      */       }
/*  944 */       if (!invisibleFrame.isVisible()) {
/*  945 */         invisibleFrame.setVisible(true);
/*      */       }
/*  947 */     } else if (mrjVersion != -1.0F)
/*      */     {
/*  949 */       if (invisibleFrame == null)
/*      */       {
/*  951 */         invisibleFrame = new Frame();
/*  952 */         invisibleFrame.setLocation(0, 10000);
/*  953 */         invisibleFrame.pack();
/*      */       }
/*  955 */       if (!invisibleFrame.isVisible()) {
/*  956 */         invisibleFrame.setVisible(true);
/*      */       }
/*      */       
/*      */     }
/*  960 */     else if (invisibleFrame == null) {
/*  961 */       invisibleFrame = new Frame();
/*      */     }
/*      */     
/*      */ 
/*  965 */     invisibleFrame.setMenuBar(paramMenuBar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static MenuBar getFramelessMenuBar()
/*      */   {
/*  975 */     if (invisibleFrame != null)
/*  976 */       return invisibleFrame.getMenuBar();
/*  977 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setFramelessJMenuBar(JMenuBar paramJMenuBar)
/*      */   {
/*  996 */     if ((invisibleFrame != null) && (!(invisibleFrame instanceof JFrame)))
/*      */     {
/*      */ 
/*  999 */       invisibleFrame.dispose();
/* 1000 */       invisibleFrame = null;
/*      */     }
/*      */     
/*      */ 
/* 1004 */     if (swingUsesScreenMenuBar())
/*      */     {
/* 1006 */       if (mrjVersion >= 4.0F)
/*      */       {
/* 1008 */         if (invisibleFrame == null)
/*      */         {
/* 1010 */           invisibleFrame = new JFrame();
/* 1011 */           invisibleFrame.setSize(0, 0);
/* 1012 */           invisibleFrame.pack();
/*      */         }
/* 1014 */         if (!invisibleFrame.isVisible()) {
/* 1015 */           invisibleFrame.setVisible(true);
/*      */         }
/* 1017 */       } else if (mrjVersion != -1.0F)
/*      */       {
/* 1019 */         if (invisibleFrame == null)
/*      */         {
/* 1021 */           invisibleFrame = new JFrame();
/* 1022 */           invisibleFrame.setLocation(0, 10000);
/* 1023 */           invisibleFrame.pack();
/*      */         }
/* 1025 */         if (!invisibleFrame.isVisible()) {
/* 1026 */           invisibleFrame.setVisible(true);
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 1031 */     else if (invisibleFrame == null) {
/* 1032 */       invisibleFrame = new JFrame();
/*      */     }
/*      */     
/*      */ 
/* 1036 */     ((JFrame)invisibleFrame).setJMenuBar(paramJMenuBar);
/* 1037 */     invisibleFrame.pack();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JMenuBar getFramelessJMenuBar()
/*      */   {
/* 1047 */     if ((invisibleFrame instanceof JFrame))
/* 1048 */       return ((JFrame)invisibleFrame).getJMenuBar();
/* 1049 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int osTypeStringToInt(String paramString)
/*      */   {
/* 1067 */     byte[] arrayOfByte1 = new byte[4];
/* 1068 */     int i = paramString.length();
/* 1069 */     if (i > 0)
/*      */     {
/* 1071 */       if (i > 4)
/* 1072 */         i = 4;
/* 1073 */       byte[] arrayOfByte2 = paramString.getBytes();
/* 1074 */       System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, Math.min(4, arrayOfByte2.length));
/*      */     }
/* 1076 */     int j = 0;
/* 1077 */     for (int k = 0; k < arrayOfByte1.length; k++)
/*      */     {
/* 1079 */       if (k > 0)
/* 1080 */         j <<= 8;
/* 1081 */       j |= arrayOfByte1[k] & 0xFF;
/*      */     }
/* 1083 */     return j;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String osTypeIntToString(int paramInt)
/*      */   {
/* 1098 */     if (paramInt == 0)
/* 1099 */       return "";
/* 1100 */     byte[] arrayOfByte = { (byte)(paramInt >> 24), (byte)(paramInt >> 16), (byte)(paramInt >> 8), (byte)paramInt };
/* 1101 */     return new String(arrayOfByte, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String parsePkgInfo(File paramFile, String paramString)
/*      */     throws IOException
/*      */   {
/* 1119 */     String str1 = null;
/* 1120 */     LineNumberReader localLineNumberReader = new LineNumberReader(new FileReader(paramFile));
/* 1121 */     String str2 = localLineNumberReader.readLine();
/* 1122 */     if (str2 != null)
/*      */     {
/* 1124 */       if (paramString.equals("type"))
/*      */       {
/* 1126 */         if (str2.length() >= 4) {
/* 1127 */           str1 = str2.substring(0, 4);
/*      */         }
/* 1129 */       } else if (paramString.equals("creator"))
/*      */       {
/* 1131 */         if (str2.length() >= 8)
/* 1132 */           str1 = str2.substring(4, 8);
/*      */       }
/*      */     }
/* 1135 */     localLineNumberReader.close();
/* 1136 */     return str1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String parseInfoPlist(File paramFile, String paramString)
/*      */     throws IOException
/*      */   {
/* 1153 */     String str1 = null;
/* 1154 */     LineNumberReader localLineNumberReader = new LineNumberReader(new FileReader(paramFile));
/*      */     String str2;
/* 1156 */     while ((str2 = localLineNumberReader.readLine()) != null)
/*      */     {
/* 1158 */       if (str2.indexOf(paramString) != -1)
/*      */       {
/* 1160 */         if ((str2 = localLineNumberReader.readLine()) == null)
/*      */           break;
/* 1162 */         str2 = str2.trim();
/* 1163 */         str1 = str2.substring(str2.indexOf('>') + 1, str2.lastIndexOf('<')); break;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1168 */     localLineNumberReader.close();
/* 1169 */     return str1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String parseMRJAppProperties(File paramFile, String paramString)
/*      */     throws IOException
/*      */   {
/* 1184 */     FileInputStream localFileInputStream = new FileInputStream(paramFile);
/* 1185 */     Properties localProperties = new Properties();
/* 1186 */     localProperties.load(localFileInputStream);
/* 1187 */     localFileInputStream.close();
/* 1188 */     return localProperties.getProperty(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String getStartupDisk()
/*      */     throws IOException
/*      */   {
/* 1200 */     if (startupDisk == null)
/*      */     {
/* 1202 */       if (mrjVersion >= 3.0F)
/*      */       {
/*      */ 
/* 1205 */         startupDisk = runAppleScript("tell application \"Finder\" to get name of startup disk");
/*      */       }
/* 1207 */       else if (mrjVersion != -1.0F)
/*      */       {
/* 1209 */         String str = MRJFileUtils.findFolder(new MRJOSType("macs")).getPath();
/* 1210 */         startupDisk = str.substring(1, str.indexOf('/', 1));
/*      */       }
/*      */       else
/*      */       {
/* 1214 */         throw new IOException();
/*      */       }
/*      */     }
/* 1217 */     return startupDisk;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static String getApplicationPath()
/*      */     throws IOException
/*      */   {
/* 1230 */     if (applicationPath == null)
/*      */     {
/* 1232 */       if (mrjVersion >= 3.0F)
/*      */       {
/*      */         try
/*      */         {
/* 1236 */           Class localClass = Class.forName("com.apple.cocoa.foundation.NSBundle", true, cocoaClassLoader);
/*      */           
/* 1238 */           Method localMethod1 = localClass.getMethod("mainBundle", null);
/* 1239 */           Object localObject = localMethod1.invoke(null, null);
/* 1240 */           Method localMethod2 = localClass.getMethod("bundlePath", null);
/* 1241 */           applicationPath = (String)localMethod2.invoke(localObject, null);
/*      */         }
/*      */         catch (Exception localException)
/*      */         {
/* 1245 */           throw new IOException(localException.getMessage());
/*      */         }
/*      */       } else {
/* 1248 */         if (mrjVersion != -1.0F)
/*      */         {
/*      */ 
/* 1251 */           throw new IOException();
/*      */         }
/*      */         
/*      */ 
/* 1255 */         throw new IOException();
/*      */       }
/*      */     }
/* 1258 */     return applicationPath;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static String runAppleScript(String paramString)
/*      */     throws IOException
/*      */   {
/* 1271 */     Process localProcess = Runtime.getRuntime().exec(new String[] { "osascript", "-e", paramString });
/* 1272 */     InputStreamReader localInputStreamReader = new InputStreamReader(localProcess.getInputStream());
/* 1273 */     StringBuffer localStringBuffer = new StringBuffer();
/* 1274 */     char[] arrayOfChar = new char[''];
/*      */     int i;
/* 1276 */     while ((i = localInputStreamReader.read(arrayOfChar)) != -1)
/* 1277 */       localStringBuffer.append(arrayOfChar, 0, i);
/* 1278 */     localInputStreamReader.close();
/* 1279 */     return localStringBuffer.toString().trim();
/*      */   }
/*      */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\mac\MRJAdapter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */