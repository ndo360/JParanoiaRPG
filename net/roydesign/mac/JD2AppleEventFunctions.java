package net.roydesign.mac;

import com.apple.mrj.macos.libraries.InterfaceLib;

class JD2AppleEventFunctions
  implements InterfaceLib
{
  public static native int AEInstallEventHandler(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean);
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\mac\JD2AppleEventFunctions.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */