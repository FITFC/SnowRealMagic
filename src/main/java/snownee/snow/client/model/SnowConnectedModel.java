package snownee.snow.client.model;

import java.util.Random;
import java.util.function.Supplier;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import snownee.snow.CoreModule;

public class SnowConnectedModel extends ForwardingBakedModel implements SnowVariantModel {

	public SnowConnectedModel(BakedModel model) {
		wrapped = model;
	}

	@Override
	public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		BakedModel model = null;
		if (pos != null && blockView.getBlockState(pos.below()).is(CoreModule.TILE_BLOCK)) {
			model = getSnowVariant();
		}
		if (model == null) {
			model = wrapped;
		}
		((FabricBakedModel) model).emitBlockQuads(blockView, state, pos, randomSupplier, context);
	}

	@Override
	public BakedModel getSnowVariant() {
		return ((SnowVariantModel) wrapped).getSnowVariant();
	}

	@Override
	public void setSnowVariant(BakedModel model) {
		((SnowVariantModel) wrapped).setSnowVariant(model);
	}

}
