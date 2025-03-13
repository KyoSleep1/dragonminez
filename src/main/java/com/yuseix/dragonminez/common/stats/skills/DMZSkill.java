package com.yuseix.dragonminez.common.stats.skills;

import net.minecraft.network.FriendlyByteBuf;

public class DMZSkill {

    private String name;
    private String desc;
    private int level;
    private boolean isActive;

    public DMZSkill(String name, String desc, int level) {
        this.name = name;
        this.desc = desc;
        this.level = level;
    }

    public DMZSkill(String name, String desc, int level, boolean isActive) {
        this.name = name;
        this.desc = desc;
        this.level = level;
        this.isActive = isActive;
    }
    // Constructor para leer desde el buffer
    public DMZSkill(FriendlyByteBuf buf) {
        this.name = buf.readUtf();
        this.desc = buf.readUtf();
        this.level = buf.readInt();
        this.isActive = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(name);
        buf.writeUtf(desc);
        buf.writeInt(level);
        buf.writeBoolean(isActive);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
