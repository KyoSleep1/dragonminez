package com.yuseix.dragonminez.stats.storymode;

public class DMZSaga {
    private final String id;
    private final String displayName;

    public DMZSaga(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}