package org.example;

public class Camp {
    private boolean isPit = false;
    private boolean isSmelly = false;
    private boolean isWindy = false;
    private boolean isPlayer = false;
    private boolean isWumpus = false;
    private boolean isMonster2 = false;
    private boolean isHidden = true;
    private boolean isWood = false;
    private boolean isGold = false;

    private boolean isBow = false;

    private boolean isLantern = false;

    private boolean isArrow = false;

    public boolean isPit(){
        return isPit;
    }

    public void setPit(boolean isPit)
    {
        this.isPit = isPit;
    }

    public boolean isSmelly()
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

    public boolean isHidden() {
        return isHidden;
    }
    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public boolean isWumpus() {
        return isWumpus;
    }

    public void setWumpus(boolean wumpus) {
        isWumpus = wumpus;
    }

    public boolean isMonster2() {
        return isMonster2;
    }

    public void setMonster2(boolean monster2) {
        isMonster2 = monster2;
    }

    public boolean isWood() {
        return isWood;
    }

    public void setWood(boolean wood) {
        isWood = wood;
    }

    public boolean isGold() {
        return isGold;
    }

    public void setGold(boolean gold) {
        isGold = gold;
    }

    public void setLantern(boolean lantern) {
        isLantern = lantern;
    }

    public void setBow(boolean bow) {
        isBow = bow;
    }

    public boolean isArrow() {
        return isArrow;
    }

    public void setArrow(boolean arrow) {
        isArrow = arrow;
    }

    public boolean isBow() {
        return isBow;
    }

    public boolean isLantern() {
        return isLantern;
    }

    public boolean isItem(){
        return isGold || isLantern || isArrow || isWood || isBow;
    }
}
