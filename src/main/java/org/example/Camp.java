package org.example;

public class Camp {
    private boolean isPit = false;
    private boolean isSmelly = false;
    private boolean isWindy = false;
    private boolean isPlayer = false;

    private boolean isWumpus = false;

    private boolean isMonster2 = false;

    public boolean IsPit(){
        return isPit;
    }

    public void setPit(boolean isPit)
    {
        this.isPit = isPit;
    }

    public boolean IsSmelly()
    {
        return isSmelly;
    }

    public void setSmelly(boolean isSmelly)
    {
        this.isSmelly = isSmelly;
    }

    public boolean isWindy()
    {
        return isWindy;
    }

    public void setWindy(boolean isWindy)
    {
        this.isWindy = isWindy;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }
}
