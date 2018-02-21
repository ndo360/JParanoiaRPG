package util;
import java.util.Vector;

public class FiniteVector extends Vector {
    private boolean infinite = false;
    private boolean contentsLocked = false;
    private boolean maxLocked = false;
    private Integer currentMaxNumContents;
    private Integer maxNumContents;

    public FiniteVector( int paramInt ) {
        super( paramInt );
        if ( paramInt > -1 ) {
            this.currentMaxNumContents = this.maxNumContents = new Integer( paramInt );
        } else {
            throw new IllegalArgumentException( "FiniteVector(int) can not be called with a negative integer." );
        }
    }

    public boolean add( Object paramObject ) {
        if ( this.contentsLocked ) {
            return false;
        }
        if ( size() < this.currentMaxNumContents.intValue() ) {
            super.add( paramObject );
            return true;
        }
        return false;
    }

    public boolean remove( Object paramObject ) {
        if ( this.contentsLocked ) {
            return false;
        }
        return super.remove( paramObject );
    }

    public Object remove( int paramInt ) {
        if ( this.contentsLocked ) {
            return null;
        }
        return super.remove( paramInt );
    }

    public int getMaxNumContents() {
        return this.maxNumContents.intValue();
    }

    public int getCurrentMaxNumContents() {
        return this.currentMaxNumContents.intValue();
    }

    public boolean setMaxNumContents( int paramInt ) {
        if ( this.maxLocked ) {
            return false;
        }
        if ( paramInt >= size() ) {
            this.maxNumContents = new Integer( paramInt );
            return true;
        }
        return false;
    }

    public void lockContents( boolean paramBoolean ) {
        this.contentsLocked = paramBoolean;
    }

    public boolean contentsLocked() {
        return this.contentsLocked;
    }

    public void lockCurrentMax() {
        this.maxLocked = true;
        this.currentMaxNumContents = new Integer( size() );
    }

    public void unlockCurrentMax() {
        this.maxLocked = false;
        this.currentMaxNumContents = this.maxNumContents;
    }

    public void makeInfinite() {
        this.infinite = true;
    }

    public boolean makeFinite() {
        if ( size() > this.maxNumContents.intValue() ) {
            return false;
        }
        this.infinite = false;
        return true;
    }

    public int spaceRemaining() {
        if ( this.contentsLocked ) {
            return 0;
        }
        return this.currentMaxNumContents.intValue() - size();
    }
}

