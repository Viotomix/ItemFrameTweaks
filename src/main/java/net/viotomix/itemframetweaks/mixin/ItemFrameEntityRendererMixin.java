package net.viotomix.itemframetweaks.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;

import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ItemFrameEntityRenderer.class)
public abstract class ItemFrameEntityRendererMixin<T extends ItemFrameEntity>
		extends EntityRenderer<T> {
	protected ItemFrameEntityRendererMixin(EntityRendererFactory.Context ctx) {
		super(ctx);
	}

	@ModifyConstant(method = "render*", constant = @Constant(floatValue = 0.5f), slice = @Slice(
		from = @At(value = "INVOKE", target = "net/minecraft/entity/decoration/ItemFrameEntity.getMapId()Lnet/minecraft/component/type/MapIdComponent;"),
		to = @At(value = "INVOKE", target = "net/minecraft/entity/decoration/ItemFrameEntity.getRotation()I")
	))
	private float noInvisItemFrameOffset(float value) {
		return 0.4375f;
	}

	@Redirect(method = "renderLabelIfPresent*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V"))
	private void noNametagRendering(EntityRenderer<T> entityRenderer, Entity entity, Text text, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, float f) {
	}
}
