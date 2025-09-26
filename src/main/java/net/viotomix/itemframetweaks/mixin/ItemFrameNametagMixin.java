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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemFrameEntityRenderer.class)
public abstract class ItemFrameNametagMixin<T extends ItemFrameEntity>
        extends EntityRenderer<T> {
    protected ItemFrameNametagMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Redirect(method = "renderLabelIfPresent*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V"))
    private void noNametagRendering(EntityRenderer<T> entityRenderer, Entity entity, Text text, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, float f) {
    }
}
