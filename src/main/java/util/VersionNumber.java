package util;
public class VersionNumber implements Comparable {
    private int major;
    private int minor;
    private int patch;

    public VersionNumber( int paramInt1, int paramInt2, int paramInt3 ) {
        this.major = paramInt1;
        this.minor = paramInt2;
        this.patch = paramInt3;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public int getPatch() {
        return this.patch;
    }

    public boolean equals( VersionNumber paramObject ) {
        return toString().equals( paramObject.toString() );
    }

    public String toString() {
        return Integer.toString( this.major ) +
                "." +
                Integer.toString( this.minor ) +
                "." +
                Integer.toString( this.patch );
    }

    public int compareTo( Object paramObject ) {
        VersionNumber localVersionNumber = (VersionNumber) paramObject;
        if ( this.major < localVersionNumber.major ) {
            return -1;
        }
        if ( this.major > localVersionNumber.major ) {
            return 1;
        }
        if ( this.minor < localVersionNumber.minor ) {
            return -1;
        }
        if ( this.minor > localVersionNumber.minor ) {
            return 1;
        }
        if ( this.patch < localVersionNumber.patch ) {
            return -1;
        }
        if ( this.patch > localVersionNumber.patch ) {
            return 1;
        }
        return 0;
    }
}

