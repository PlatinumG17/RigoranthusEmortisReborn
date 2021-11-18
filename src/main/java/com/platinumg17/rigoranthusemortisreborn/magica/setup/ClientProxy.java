package com.platinumg17.rigoranthusemortisreborn.magica.setup;


import com.platinumg17.rigoranthusemortisreborn.magica.IProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().level;
    }

    @Override
    public PlayerEntity getPlayer(){return Minecraft.getInstance().player;}

    @Override
    public Minecraft getMinecraft(){return Minecraft.getInstance();}
}