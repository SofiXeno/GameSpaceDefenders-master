package com.mygdx.game.model;

public class Weapon{
    public static final int SINGLE = 1;
    public static final int DOUBLE = 2;
    public static final int MINIGUN = 3;
    private int cooldownTime;
    private int type;

    public Weapon(int cooldownTime, int weaponType) {
        this.cooldownTime = cooldownTime;
        this.type = weaponType;

    }
    public static Weapon getDefaultSingle(){
        return new Weapon(300, Weapon.SINGLE);
    }
    public int getCooldownTime() {
        return cooldownTime;
    }

    public int getType() {
        return type;
    }

}
