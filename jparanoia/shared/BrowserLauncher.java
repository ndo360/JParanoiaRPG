/*     */ package jparanoia.shared;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BrowserLauncher
/*     */ {
/*     */   private static int jvm;
/*     */   private static Object browser;
/* 201 */   private static boolean loadedWithoutErrors = true;
/* 202 */   static { String str1 = System.getProperty("os.name");
/* 203 */     if (str1.startsWith("Mac OS")) {
/* 204 */       String str2 = System.getProperty("mrj.version");
/* 205 */       String str3 = str2.substring(0, 3);
/*     */       try {
/* 207 */         double d = Double.valueOf(str3).doubleValue();
/* 208 */         if (d == 2.0D) {
/* 209 */           jvm = 0;
/* 210 */         } else if ((d >= 2.1D) && (d < 3.0D))
/*     */         {
/*     */ 
/*     */ 
/* 214 */           jvm = 1;
/* 215 */         } else if (d == 3.0D) {
/* 216 */           jvm = 3;
/* 217 */         } else if (d >= 3.1D)
/*     */         {
/* 219 */           jvm = 4;
/*     */         } else {
/* 221 */           loadedWithoutErrors = false;
/* 222 */           errorMessage = "Unsupported MRJ version: " + d;
/*     */         }
/*     */       } catch (NumberFormatException localNumberFormatException) {
/* 225 */         loadedWithoutErrors = false;
/* 226 */         errorMessage = "Invalid MRJ version: " + str2;
/*     */       }
/* 228 */     } else if (str1.startsWith("Windows")) {
/* 229 */       if (str1.indexOf("9") != -1) {
/* 230 */         jvm = 6;
/*     */       } else {
/* 232 */         jvm = 5;
/*     */       }
/*     */     } else {
/* 235 */       jvm = -1;
/*     */     }
/*     */     
/* 238 */     if (loadedWithoutErrors) {
/* 239 */       loadedWithoutErrors = loadClasses();
/*     */     }
/*     */   }
/*     */   
/*     */   private static Class mrjFileUtilsClass;
/*     */   private static Class mrjOSTypeClass;
/*     */   private static Class aeDescClass;
/*     */   private static Constructor aeTargetConstructor;
/*     */   private static Constructor appleEventConstructor;
/*     */   private static Constructor aeDescConstructor;
/*     */   private static Method findFolder;
/*     */   private static Method getFileCreator;
/*     */   private static Method getFileType;
/*     */   
/*     */   private static boolean loadClasses() {
/*     */     Object localObject;
/* 255 */     switch (jvm) {
/*     */     case 0: 
/*     */       try {
/* 258 */         Class localClass1 = Class.forName("com.apple.MacOS.AETarget");
/* 259 */         localObject = Class.forName("com.apple.MacOS.OSUtils");
/* 260 */         Class localClass3 = Class.forName("com.apple.MacOS.AppleEvent");
/* 261 */         Class localClass4 = Class.forName("com.apple.MacOS.ae");
/* 262 */         aeDescClass = Class.forName("com.apple.MacOS.AEDesc");
/*     */         
/* 264 */         aeTargetConstructor = localClass1.getDeclaredConstructor(new Class[] { Integer.TYPE });
/* 265 */         appleEventConstructor = localClass3.getDeclaredConstructor(new Class[] { Integer.TYPE, Integer.TYPE, localClass1, Integer.TYPE, Integer.TYPE });
/* 266 */         aeDescConstructor = aeDescClass.getDeclaredConstructor(new Class[] { String.class });
/*     */         
/* 268 */         makeOSType = ((Class)localObject).getDeclaredMethod("makeOSType", new Class[] { String.class });
/* 269 */         putParameter = localClass3.getDeclaredMethod("putParameter", new Class[] { Integer.TYPE, aeDescClass });
/* 270 */         sendNoReply = localClass3.getDeclaredMethod("sendNoReply", new Class[0]);
/*     */         
/* 272 */         Field localField2 = localClass4.getDeclaredField("keyDirectObject");
/* 273 */         keyDirectObject = (Integer)localField2.get(null);
/* 274 */         Field localField3 = localClass3.getDeclaredField("kAutoGenerateReturnID");
/* 275 */         kAutoGenerateReturnID = (Integer)localField3.get(null);
/* 276 */         Field localField4 = localClass3.getDeclaredField("kAnyTransactionID");
/* 277 */         kAnyTransactionID = (Integer)localField4.get(null);
/*     */       } catch (ClassNotFoundException localClassNotFoundException1) {
/* 279 */         errorMessage = localClassNotFoundException1.getMessage();
/* 280 */         return false;
/*     */       } catch (NoSuchMethodException localNoSuchMethodException1) {
/* 282 */         errorMessage = localNoSuchMethodException1.getMessage();
/* 283 */         return false;
/*     */       } catch (NoSuchFieldException localNoSuchFieldException1) {
/* 285 */         errorMessage = localNoSuchFieldException1.getMessage();
/* 286 */         return false;
/*     */       } catch (IllegalAccessException localIllegalAccessException1) {
/* 288 */         errorMessage = localIllegalAccessException1.getMessage();
/* 289 */         return false;
/*     */       }
/*     */     case 1: 
/*     */       try
/*     */       {
/* 294 */         mrjFileUtilsClass = Class.forName("com.apple.mrj.MRJFileUtils");
/* 295 */         mrjOSTypeClass = Class.forName("com.apple.mrj.MRJOSType");
/* 296 */         Field localField1 = mrjFileUtilsClass.getDeclaredField("kSystemFolderType");
/* 297 */         kSystemFolderType = localField1.get(null);
/* 298 */         findFolder = mrjFileUtilsClass.getDeclaredMethod("findFolder", new Class[] { mrjOSTypeClass });
/* 299 */         getFileCreator = mrjFileUtilsClass.getDeclaredMethod("getFileCreator", new Class[] { File.class });
/* 300 */         getFileType = mrjFileUtilsClass.getDeclaredMethod("getFileType", new Class[] { File.class });
/*     */       } catch (ClassNotFoundException localClassNotFoundException2) {
/* 302 */         errorMessage = localClassNotFoundException2.getMessage();
/* 303 */         return false;
/*     */       } catch (NoSuchFieldException localNoSuchFieldException2) {
/* 305 */         errorMessage = localNoSuchFieldException2.getMessage();
/* 306 */         return false;
/*     */       } catch (NoSuchMethodException localNoSuchMethodException2) {
/* 308 */         errorMessage = localNoSuchMethodException2.getMessage();
/* 309 */         return false;
/*     */       } catch (SecurityException localSecurityException) {
/* 311 */         errorMessage = localSecurityException.getMessage();
/* 312 */         return false;
/*     */       } catch (IllegalAccessException localIllegalAccessException2) {
/* 314 */         errorMessage = localIllegalAccessException2.getMessage();
/* 315 */         return false;
/*     */       }
/*     */     case 3: 
/*     */       try
/*     */       {
/* 320 */         Class localClass2 = Class.forName("com.apple.mrj.jdirect.Linker");
/* 321 */         localObject = localClass2.getConstructor(new Class[] { Class.class });
/* 322 */         linkage = ((Constructor)localObject).newInstance(new Object[] { BrowserLauncher.class });
/*     */       } catch (ClassNotFoundException localClassNotFoundException3) {
/* 324 */         errorMessage = localClassNotFoundException3.getMessage();
/* 325 */         return false;
/*     */       } catch (NoSuchMethodException localNoSuchMethodException3) {
/* 327 */         errorMessage = localNoSuchMethodException3.getMessage();
/* 328 */         return false;
/*     */       } catch (InvocationTargetException localInvocationTargetException) {
/* 330 */         errorMessage = localInvocationTargetException.getMessage();
/* 331 */         return false;
/*     */       } catch (InstantiationException localInstantiationException) {
/* 333 */         errorMessage = localInstantiationException.getMessage();
/* 334 */         return false;
/*     */       } catch (IllegalAccessException localIllegalAccessException3) {
/* 336 */         errorMessage = localIllegalAccessException3.getMessage();
/* 337 */         return false;
/*     */       }
/*     */     case 4: 
/*     */       try
/*     */       {
/* 342 */         mrjFileUtilsClass = Class.forName("com.apple.mrj.MRJFileUtils");
/* 343 */         openURL = mrjFileUtilsClass.getDeclaredMethod("openURL", new Class[] { String.class });
/*     */       } catch (ClassNotFoundException localClassNotFoundException4) {
/* 345 */         errorMessage = localClassNotFoundException4.getMessage();
/* 346 */         return false;
/*     */       } catch (NoSuchMethodException localNoSuchMethodException4) {
/* 348 */         errorMessage = localNoSuchMethodException4.getMessage();
/* 349 */         return false;
/*     */       }
/*     */     }
/*     */     
/*     */     
/*     */ 
/* 355 */     return true; }
/*     */   
/*     */   private static Method openURL;
/*     */   private static Method makeOSType;
/*     */   private static Method putParameter;
/*     */   private static Method sendNoReply;
/*     */   private static Object kSystemFolderType;
/*     */   private static Integer keyDirectObject;
/*     */   private static Integer kAutoGenerateReturnID;
/*     */   private static Integer kAnyTransactionID;
/*     */   private static Object linkage;
/*     */   
/* 367 */   private static Object locateBrowser() { if (browser != null)
/* 368 */       return browser;
/*     */     Object localObject2;
/* 370 */     switch (jvm) {
/*     */     case 0: 
/*     */       try {
/* 373 */         Integer localInteger1 = (Integer)makeOSType.invoke(null, new Object[] { "MACS" });
/* 374 */         Object localObject1 = aeTargetConstructor.newInstance(new Object[] { localInteger1 });
/* 375 */         Integer localInteger2 = (Integer)makeOSType.invoke(null, new Object[] { "GURL" });
/* 376 */         return appleEventConstructor.newInstance(new Object[] { localInteger2, localInteger2, localObject1, kAutoGenerateReturnID, kAnyTransactionID });
/*     */ 
/*     */ 
/*     */       }
/*     */       catch (IllegalAccessException localIllegalAccessException1)
/*     */       {
/*     */ 
/*     */ 
/* 384 */         browser = null;
/* 385 */         errorMessage = localIllegalAccessException1.getMessage();
/* 386 */         return browser;
/*     */       } catch (InstantiationException localInstantiationException) {
/* 388 */         browser = null;
/* 389 */         errorMessage = localInstantiationException.getMessage();
/* 390 */         return browser;
/*     */       } catch (InvocationTargetException localInvocationTargetException1) {
/* 392 */         browser = null;
/* 393 */         errorMessage = localInvocationTargetException1.getMessage();
/* 394 */         return browser;
/*     */       }
/*     */     case 1: 
/*     */       File localFile;
/*     */       try {
/* 399 */         localFile = (File)findFolder.invoke(null, new Object[] { kSystemFolderType });
/*     */       } catch (IllegalArgumentException localIllegalArgumentException1) {
/* 401 */         browser = null;
/* 402 */         errorMessage = localIllegalArgumentException1.getMessage();
/* 403 */         return browser;
/*     */       } catch (IllegalAccessException localIllegalAccessException2) {
/* 405 */         browser = null;
/* 406 */         errorMessage = localIllegalAccessException2.getMessage();
/* 407 */         return browser;
/*     */       } catch (InvocationTargetException localInvocationTargetException2) {
/* 409 */         browser = null;
/* 410 */         errorMessage = localInvocationTargetException2.getTargetException().getClass() + ": " + localInvocationTargetException2.getTargetException().getMessage();
/* 411 */         return browser;
/*     */       }
/* 413 */       String[] arrayOfString = localFile.list();
/*     */       
/* 415 */       for (int i = 0; i < arrayOfString.length; i++) {
/*     */         try {
/* 417 */           localObject2 = new File(localFile, arrayOfString[i]);
/* 418 */           if (((File)localObject2).isFile())
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 426 */             Object localObject3 = getFileType.invoke(null, new Object[] { localObject2 });
/* 427 */             if ("FNDR".equals(localObject3.toString())) {
/* 428 */               Object localObject4 = getFileCreator.invoke(null, new Object[] { localObject2 });
/* 429 */               if ("MACS".equals(localObject4.toString())) {
/* 430 */                 browser = ((File)localObject2).toString();
/* 431 */                 return browser;
/*     */               }
/*     */             }
/*     */           }
/* 435 */         } catch (IllegalArgumentException localIllegalArgumentException2) { browser = browser;
/* 436 */           errorMessage = localIllegalArgumentException2.getMessage();
/* 437 */           return null;
/*     */         } catch (IllegalAccessException localIllegalAccessException3) {
/* 439 */           browser = null;
/* 440 */           errorMessage = localIllegalAccessException3.getMessage();
/* 441 */           return browser;
/*     */         } catch (InvocationTargetException localInvocationTargetException3) {
/* 443 */           browser = null;
/* 444 */           errorMessage = localInvocationTargetException3.getTargetException().getClass() + ": " + localInvocationTargetException3.getTargetException().getMessage();
/* 445 */           return browser;
/*     */         }
/*     */       }
/* 448 */       browser = null;
/* 449 */       break;
/*     */     case 3: 
/*     */     case 4: 
/* 452 */       browser = "";
/* 453 */       break;
/*     */     case 5: 
/* 455 */       browser = "cmd.exe";
/* 456 */       break;
/*     */     case 6: 
/* 458 */       browser = "command.com";
/* 459 */       break;
/*     */     }
/*     */     
/* 462 */     browser = "netscape";
/*     */     
/*     */ 
/* 465 */     return browser;
/*     */   }
/*     */   
/*     */   private static final String JDirect_MacOSX = "/System/Library/Frameworks/Carbon.framework/Frameworks/HIToolbox.framework/HIToolbox";
/*     */   private static final int MRJ_2_0 = 0;
/*     */   private static final int MRJ_2_1 = 1;
/*     */   private static final int MRJ_3_0 = 3;
/*     */   
/*     */   public static void openURL(String paramString) throws IOException {
/* 474 */     if (!loadedWithoutErrors) {
/* 475 */       throw new IOException("Exception in finding browser: " + errorMessage);
/*     */     }
/* 477 */     Object localObject1 = locateBrowser();
/* 478 */     if (localObject1 == null) {
/* 479 */       throw new IOException("Unable to locate browser: " + errorMessage);
/*     */     }
/*     */     Process localProcess;
/* 482 */     switch (jvm) {
/*     */     case 0: 
/* 484 */       Object localObject2 = null;
/*     */       try {
/* 486 */         localObject2 = aeDescConstructor.newInstance(new Object[] { paramString });
/* 487 */         putParameter.invoke(localObject1, new Object[] { keyDirectObject, localObject2 });
/* 488 */         sendNoReply.invoke(localObject1, new Object[0]);
/*     */       } catch (InvocationTargetException localInvocationTargetException1) {
/* 490 */         throw new IOException("InvocationTargetException while creating AEDesc: " + localInvocationTargetException1.getMessage());
/*     */       } catch (IllegalAccessException localIllegalAccessException1) {
/* 492 */         throw new IOException("IllegalAccessException while building AppleEvent: " + localIllegalAccessException1.getMessage());
/*     */       } catch (InstantiationException localInstantiationException) {
/* 494 */         throw new IOException("InstantiationException while creating AEDesc: " + localInstantiationException.getMessage());
/*     */       } finally {
/* 496 */         localObject2 = null;
/* 497 */         localObject1 = null;
/*     */       }
/*     */     
/*     */     case 1: 
/* 501 */       Runtime.getRuntime().exec(new String[] { (String)localObject1, paramString });
/* 502 */       break;
/*     */     case 3: 
/* 504 */       int[] arrayOfInt1 = new int[1];
/* 505 */       int i = ICStart(arrayOfInt1, 0);
/* 506 */       if (i == 0) {
/* 507 */         int[] arrayOfInt2 = { 0 };
/* 508 */         byte[] arrayOfByte = paramString.getBytes();
/* 509 */         int[] arrayOfInt3 = { arrayOfByte.length };
/* 510 */         i = ICLaunchURL(arrayOfInt1[0], new byte[] { 0 }, arrayOfByte, arrayOfByte.length, arrayOfInt2, arrayOfInt3);
/*     */         
/*     */ 
/* 513 */         if (i == 0)
/*     */         {
/*     */ 
/* 516 */           ICStop(arrayOfInt1);
/*     */         } else {
/* 518 */           throw new IOException("Unable to launch URL: " + i);
/*     */         }
/*     */       } else {
/* 521 */         throw new IOException("Unable to create an Internet Config instance: " + i);
/*     */       }
/*     */       break;
/*     */     case 4: 
/*     */       try {
/* 526 */         openURL.invoke(null, new Object[] { paramString });
/*     */       } catch (InvocationTargetException localInvocationTargetException2) {
/* 528 */         throw new IOException("InvocationTargetException while calling openURL: " + localInvocationTargetException2.getMessage());
/*     */       } catch (IllegalAccessException localIllegalAccessException2) {
/* 530 */         throw new IOException("IllegalAccessException while calling openURL: " + localIllegalAccessException2.getMessage());
/*     */       }
/*     */     
/*     */ 
/*     */ 
/*     */     case 5: 
/*     */     case 6: 
/* 537 */       localProcess = Runtime.getRuntime().exec(new String[] { (String)localObject1, "/c", "start", "\"\"", '"' + paramString + '"' });
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 545 */         localProcess.waitFor();
/* 546 */         localProcess.exitValue();
/*     */       } catch (InterruptedException localInterruptedException1) {
/* 548 */         throw new IOException("InterruptedException while launching browser: " + localInterruptedException1.getMessage());
/*     */       }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     case -1: 
/* 555 */       localProcess = Runtime.getRuntime().exec(new String[] { (String)localObject1, "-remote", "'openURL(" + paramString + ")'" });
/*     */       
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 561 */         int j = localProcess.waitFor();
/* 562 */         if (j != 0) {
/* 563 */           Runtime.getRuntime().exec(new String[] { (String)localObject1, paramString });
/*     */         }
/*     */       } catch (InterruptedException localInterruptedException2) {
/* 566 */         throw new IOException("InterruptedException while launching browser: " + localInterruptedException2.getMessage());
/*     */       }
/*     */     
/*     */     case 2: 
/*     */     default: 
/* 571 */       Runtime.getRuntime().exec(new String[] { (String)localObject1, paramString });
/*     */     }
/*     */   }
/*     */   
/*     */   private static final int MRJ_3_1 = 4;
/*     */   private static final int WINDOWS_NT = 5;
/*     */   private static final int WINDOWS_9x = 6;
/*     */   private static final int OTHER = -1;
/*     */   private static final String FINDER_TYPE = "FNDR";
/*     */   private static final String FINDER_CREATOR = "MACS";
/*     */   private static final String GURL_EVENT = "GURL";
/*     */   private static final String FIRST_WINDOWS_PARAMETER = "/c";
/*     */   private static final String SECOND_WINDOWS_PARAMETER = "start";
/*     */   private static final String THIRD_WINDOWS_PARAMETER = "\"\"";
/*     */   private static final String NETSCAPE_REMOTE_PARAMETER = "-remote";
/*     */   private static final String NETSCAPE_OPEN_PARAMETER_START = "'openURL(";
/*     */   private static final String NETSCAPE_OPEN_PARAMETER_END = ")'";
/*     */   private static String errorMessage;
/*     */   private static native int ICStart(int[] paramArrayOfInt, int paramInt);
/*     */   
/*     */   private static native int ICStop(int[] paramArrayOfInt);
/*     */   
/*     */   private static native int ICLaunchURL(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2);
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\BrowserLauncher.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */