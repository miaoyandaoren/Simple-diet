package net.mydr.diet.world.test;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class SetIntComponent implements IntComponent, AutoSyncedComponent {
    private int value;

    public SetIntComponent(int value) {
        this.value = value;
    }

    @Override public int getValue() { return this.value; }

    @Override
    public void setVitality(int value) {
        this.value = value;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.value = tag.getInt("value");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("value", this.value);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayer player) {
        return true;
    }
}
