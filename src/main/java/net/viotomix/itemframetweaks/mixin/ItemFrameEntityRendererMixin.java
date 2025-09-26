package net.viotomix.itemframetweaks.mixin;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.entity.decoration.ItemFrameEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ItemFrameEntityRenderer.class)
public abstract class ItemFrameEntityRendererMixin<T extends ItemFrameEntity>
		extends EntityRenderer<T> {
	protected ItemFrameEntityRendererMixin(EntityRendererFactory.Context ctx) {
		super(ctx);
	}

	@ModifyConstant(method = "render*", constant = @Constant(floatValue = 0.5f), slice = @Slice(
		from = @At(value = "INVOKE", target = "net/minecraft/entity/decoration/ItemFrameEntity.getMapId(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/component/type/MapIdComponent;"),
		to = @At(value = "INVOKE", target = "net/minecraft/entity/decoration/ItemFrameEntity.getRotation()I")
	))
	private float noInvisItemFrameOffset(float value) {
		return 0.4375f;
	}
}
