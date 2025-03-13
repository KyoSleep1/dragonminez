package com.yuseix.dragonminez.common.stats.forms;

import net.minecraft.network.FriendlyByteBuf;

public class FormsData {

    private String name;
    private int level;

    public FormsData(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public FormsData(FriendlyByteBuf buf) {
        this.name = buf.readUtf();
        this.level = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(name);
        buf.writeInt(level);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
