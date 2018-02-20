/*    */
package util;
/*    */
/*    */
import java.util.Vector;

/*    */
/*    */ public class FiniteVector
        /*    */ extends Vector
        /*    */ {
    /*  8 */   private boolean infinite = false;
    private boolean contentsLocked = false;
    private boolean maxLocked = false;
    /*    */   private Integer currentMaxNumContents;
    /*    */   private Integer maxNumContents;

    /*    */
    /* 12 */
    public FiniteVector( int paramInt ) {
        super( paramInt );
        /* 13 */
        if ( paramInt > -1 ) {
            /* 14 */
            this.currentMaxNumContents = this.maxNumContents = new Integer( paramInt );
            /*    */
        } else {
            /* 16 */
            throw new IllegalArgumentException( "FiniteVector(int) can not be called with a negative integer." );
            /*    */
        }
        /*    */
    }

    /*    */
    /*    */
    public boolean add( Object paramObject ) {
        /* 21 */
        if ( this.contentsLocked ) {
            return false;
            /*    */
        }
        /* 23 */
        if ( size() < this.currentMaxNumContents.intValue() )
            /*    */ {
            /* 25 */
            super.add( paramObject );
            /* 26 */
            return true;
            /*    */
        }
        /*    */
        /* 29 */
        return false;
        /*    */
    }

    /*    */
    /*    */
    public boolean remove( Object paramObject )
    /*    */ {
        /* 34 */
        if ( this.contentsLocked ) {
            return false;
        }
        /* 35 */
        return super.remove( paramObject );
        /*    */
    }

    /*    */
    /*    */
    public Object remove( int paramInt )
    /*    */ {
        /* 40 */
        if ( this.contentsLocked ) {
            return null;
        }
        /* 41 */
        return super.remove( paramInt );
        /*    */
    }

    /*    */
    /*    */
    public int getMaxNumContents() {
        /* 45 */
        return this.maxNumContents.intValue();
        /*    */
    }

    /*    */
    /* 48 */
    public int getCurrentMaxNumContents() {
        return this.currentMaxNumContents.intValue();
    }

    /*    */
    /*    */
    public boolean setMaxNumContents( int paramInt )
    /*    */ {
        /* 52 */
        if ( this.maxLocked ) {
            return false;
            /*    */
        }
        /* 54 */
        if ( paramInt >= size() )
            /*    */ {
            /* 56 */
            this.maxNumContents = new Integer( paramInt );
            /* 57 */
            return true;
            /*    */
        }
        /*    */
        /* 60 */
        return false;
        /*    */
    }

    /*    */
    /*    */
    public void lockContents( boolean paramBoolean ) {
        /* 64 */
        this.contentsLocked = paramBoolean;
        /*    */
    }

    /*    */
    /* 67 */
    public boolean contentsLocked() {
        return this.contentsLocked;
    }

    /*    */
    /*    */
    public void lockCurrentMax()
    /*    */ {
        /* 71 */
        this.maxLocked = true;
        /* 72 */
        this.currentMaxNumContents = new Integer( size() );
        /*    */
    }

    /*    */
    /*    */
    public void unlockCurrentMax()
    /*    */ {
        /* 77 */
        this.maxLocked = false;
        /* 78 */
        this.currentMaxNumContents = this.maxNumContents;
        /*    */
    }

    /*    */
    /*    */
    public void makeInfinite()
    /*    */ {
        /* 83 */
        this.infinite = true;
        /*    */
    }

    /*    */
    /*    */
    public boolean makeFinite()
    /*    */ {
        /* 88 */
        if ( size() > this.maxNumContents.intValue() ) {
            return false;
            /*    */
        }
        /* 90 */
        this.infinite = false;
        /* 91 */
        return true;
        /*    */
    }

    /*    */
    /*    */
    public int spaceRemaining()
    /*    */ {
        /* 96 */
        if ( this.contentsLocked ) {
            return 0;
        }
        /* 97 */
        return this.currentMaxNumContents.intValue() - size();
        /*    */
    }
    /*    */
}

