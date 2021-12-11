package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.tileentity.TileEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EmorticCortexTile extends TileEntity implements IAnimatable {
    AnimationFactory manager = new AnimationFactory(this);

    public EmorticCortexTile() {
        super(BlockRegistry.EMORTIC_CORTEX_TILE);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "brain", 0, this::idlePredicate));
    }
    private <P extends IAnimatable> PlayState idlePredicate(AnimationEvent<P> pAnimationEvent) {
        pAnimationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("brain_float", true));
        return PlayState.CONTINUE;
    }
//    @Override
//    public void tick() {
//        if(level.isClientSide) {
//            ParticleColor randColor = new ParticleColor(level.random.nextInt(255), level.random.nextInt(255), level.random.nextInt(255));
//            for (int i = 0; i < 6; i++) {
//                level.addParticle(
//                        GlowParticleData.createData(randColor),
//                        worldPosition.getX() + 0.5 + ParticleUtil.inRange(-0.3, 0.3), worldPosition.getY() + 0.5 + ParticleUtil.inRange(-0.3, 0.3), worldPosition.getZ() + 0.5 + ParticleUtil.inRange(-0.3, 0.3),
//                        0, 0, 0);
//            }
//        }
//    }
    @Override
    public AnimationFactory getFactory() {
        return manager;
    }
}