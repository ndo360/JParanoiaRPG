package jparanoia.shared;
import java.util.StringTokenizer;

public class JPVersionNumber implements Comparable {
    private int major;
    private int minor;
    private int patch;
    private boolean isValid = true;

    public JPVersionNumber( int paramInt1, int paramInt2, int paramInt3 ) {
        this.major = paramInt1;
        this.minor = paramInt2;
        this.patch = paramInt3;
    }

    public JPVersionNumber( String paramString ) {
        StringTokenizer localStringTokenizer = new StringTokenizer( paramString, "." );
        if ( localStringTokenizer.countTokens() != 3 ) {
            this.isValid = false;
        } else {
            this.major = Integer.parseInt( localStringTokenizer.nextToken() );
            this.minor = Integer.parseInt( localStringTokenizer.nextToken() );
            this.patch = Integer.parseInt( localStringTokenizer.nextToken() );
        }
    }

    public boolean isValid() {
        return this.isValid;
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

    public boolean equals( JPVersionNumber paramObject ) {
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
        JPVersionNumber localJPVersionNumber = (JPVersionNumber) paramObject;
        if ( this.major < localJPVersionNumber.major ) {
            return -1;
        }
        if ( this.major > localJPVersionNumber.major ) {
            return 1;
        }
        if ( this.minor < localJPVersionNumber.minor ) {
            return -1;
        }
        if ( this.minor > localJPVersionNumber.minor ) {
            return 1;
        }
        if ( this.patch < localJPVersionNumber.patch ) {
            return -1;
        }
        if ( this.patch > localJPVersionNumber.patch ) {
            return 1;
        }
        return 0;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JPVersionNumber.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
