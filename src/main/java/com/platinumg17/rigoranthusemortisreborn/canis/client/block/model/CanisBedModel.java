package com.platinumg17.rigoranthusemortisreborn.canis.client.block.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.CanisBedBlock;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity.CanisBedTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.BlockPart;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.registries.IRegistryDelegate;
import com.platinumg17.rigoranthusemortisreborn.api.registry.IBeddingMaterial;
import com.platinumg17.rigoranthusemortisreborn.api.registry.ICasingMaterial;

public class CanisBedModel implements IBakedModel {
    public static CanisBedItemOverride ITEM_OVERIDE = new CanisBedItemOverride();
    private static final ResourceLocation MISSING_TEXTURE = new ResourceLocation("missingno");
    private ModelLoader modelLoader;
    private BlockModel model;
    private IBakedModel bakedModel;
    private final Map<Triple<IRegistryDelegate<ICasingMaterial>, IRegistryDelegate<IBeddingMaterial>, Direction>, IBakedModel> cache = Maps.newHashMap();
    public CanisBedModel(ModelLoader modelLoader, BlockModel model, IBakedModel bakedModel) {
        this.modelLoader = modelLoader;
        this.model = model;
        this.bakedModel = bakedModel;
    }
    public IBakedModel getModelVariant(@Nonnull IModelData data) {return this.getModelVariant(data.getData(CanisBedTileEntity.CASING), data.getData(CanisBedTileEntity.BEDDING), data.getData(CanisBedTileEntity.FACING));}
    public IBakedModel getModelVariant(ICasingMaterial casing, IBeddingMaterial bedding, Direction facing) {
        Triple<IRegistryDelegate<ICasingMaterial>, IRegistryDelegate<IBeddingMaterial>, Direction> key =
                ImmutableTriple.of(casing != null ? casing.delegate : null, bedding != null ? bedding.delegate : null, facing != null ? facing : Direction.NORTH);
        return this.cache.computeIfAbsent(key, (k) -> bakeModelVariant(k.getLeft(), k.getMiddle(), k.getRight()));
    }
    @Override public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {return this.getModelVariant(null, null, Direction.NORTH).getQuads(state, side, rand, EmptyModelData.INSTANCE);}
    @Override public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData data) {return this.getModelVariant(data).getQuads(state, side, rand, data);}
    @Override public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data) {return this.getModelVariant(data).getParticleTexture(data);}

    @Override
    public IModelData getModelData(@Nonnull IBlockDisplayReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData) {
        ICasingMaterial casing = null;
        IBeddingMaterial bedding = null;
        Direction facing = Direction.NORTH;
        TileEntity tile = world.getBlockEntity(pos);
        if (tile instanceof CanisBedTileEntity) {
            casing = ((CanisBedTileEntity) tile).getCasing();
            bedding = ((CanisBedTileEntity) tile).getBedding();
        }
        if (state.hasProperty(CanisBedBlock.FACING)) {
            facing = state.getValue(CanisBedBlock.FACING);
        }
        tileData.setData(CanisBedTileEntity.CASING, casing);
        tileData.setData(CanisBedTileEntity.BEDDING, bedding);
        tileData.setData(CanisBedTileEntity.FACING, facing);
        return tileData;
    }

    public IBakedModel bakeModelVariant(@Nullable IRegistryDelegate<ICasingMaterial> casingResource, @Nullable IRegistryDelegate<IBeddingMaterial> beddingResource, @Nonnull Direction facing) {
        List<BlockPart> parts = this.model.getElements();
        List<BlockPart> elements = new ArrayList<>(parts.size());
        for (BlockPart part : parts) {
            elements.add(new BlockPart(part.from, part.to, Maps.newHashMap(part.faces), part.rotation, part.shade));
        }
        BlockModel newModel = new BlockModel(this.model.getParentLocation(), elements,
                Maps.newHashMap(this.model.textureMap), this.model.hasAmbientOcclusion(), this.model.getGuiLight(),
                this.model.getTransforms(), new ArrayList<>(this.model.getOverrides()));
        newModel.name = this.model.name;
        newModel.parent = this.model.parent;
        Either<RenderMaterial, String> casingTexture = findCasingTexture(casingResource);
        newModel.textureMap.put("bedding", findBeddingTexture(beddingResource));
        newModel.textureMap.put("casing", casingTexture);
        newModel.textureMap.put("particle", casingTexture);
        return newModel.bake(this.modelLoader, newModel, ModelLoader.defaultTextureGetter(), getModelRotation(facing), createResourceVariant(casingResource, beddingResource, facing), true);
    }

    private ResourceLocation createResourceVariant(@Nonnull IRegistryDelegate<ICasingMaterial> casingResource, @Nonnull IRegistryDelegate<IBeddingMaterial> beddingResource, @Nonnull Direction facing) {
        String beddingKey = beddingResource != null
                ? beddingResource.name().toString().replace(':', '.')
                : "rigoranthusemortisreborn.canisbed.bedding.missing";
        String casingKey = beddingResource != null
                ? casingResource.name().toString().replace(':', '.')
                : "rigoranthusemortisreborn.canisbed.casing.missing";
        return new ModelResourceLocation(EmortisConstants.MOD_ID, "blocks/canis_bed#bedding=" + beddingKey + ",casing=" + casingKey + ",facing=" + facing.getName());
    }
    private Either<RenderMaterial, String> findCasingTexture(@Nullable IRegistryDelegate<ICasingMaterial> resource) {return findTexture(resource != null ? resource.get().getTexture() : null);}
    private Either<RenderMaterial, String> findBeddingTexture(@Nullable IRegistryDelegate<IBeddingMaterial> resource) {return findTexture(resource != null ? resource.get().getTexture() : null);}
    private Either<RenderMaterial, String> findTexture(@Nullable ResourceLocation resource) {
        if (resource == null) {resource = MISSING_TEXTURE;}
        return Either.left(new RenderMaterial(PlayerContainer.BLOCK_ATLAS, resource));
    }

    private ModelRotation getModelRotation(@Nonnull Direction dir) {
        switch (dir) {
            default:    return ModelRotation.X0_Y0; // North
            case EAST:  return ModelRotation.X0_Y90;
            case SOUTH: return ModelRotation.X0_Y180;
            case WEST:  return ModelRotation.X0_Y270;
        }
    }
    @Override public boolean useAmbientOcclusion() {
        return this.bakedModel.useAmbientOcclusion();
    }
    @Override public boolean isGui3d() {
        return this.bakedModel.isGui3d();
    }
    @Override public boolean usesBlockLight() {
        return this.bakedModel.usesBlockLight();
    }
    @Override public boolean isCustomRenderer() {
        return this.bakedModel.isCustomRenderer();
    }
    @Override public TextureAtlasSprite getParticleIcon() {
        return this.bakedModel.getParticleIcon();
    }
    @Override public ItemCameraTransforms getTransforms() {
        return this.bakedModel.getTransforms();
    }
    @Override public ItemOverrideList getOverrides() {
        return ITEM_OVERIDE;
    }
}