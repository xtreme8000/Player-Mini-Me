// Decompiled with: CFR 0.150
package com.gitlab.indicode.fabric.paperdoll.mixin;

import com.gitlab.indicode.fabric.paperdoll.PaperDoll;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HudMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method="render", at=@At("RETURN"))
    public void render(MatrixStack matrices, float f, CallbackInfo ci) {

        // Don't draw if the F3 screen is open
        if (this.client.options.debugEnabled || this.client.options.hudHidden || this.client.player == null) {
            return;
        }

        ClientPlayerEntity player = this.client.player;
        
        // Don't render if it's set to only activity
        if (!(!PaperDoll.config.only_activity || player.isSneaking() || player.isSprinting() || player.isSwimming() || player.handSwinging || player.isFallFlying())) {
            return;
        }
        
        EntityRenderDispatcher entityRenderDispatcher = this.client.getEntityRenderDispatcher();
        MatrixStack matrixStack = new MatrixStack();
        
        // Store sensitive info to put back later
        float pitch = player.pitch;
        float yaw = player.yaw;
        float headYaw = player.headYaw;
        RenderSystem.pushMatrix();
        boolean entityShadows = entityRenderDispatcher.gameOptions.entityShadows;

        // Setting the doll to be neutral, and position and scale
        player.pitch = 0.0f;
        player.setBodyYaw(180.0f - (float)PaperDoll.config.rotation);
        player.setHeadYaw(180.0f - (float)PaperDoll.config.rotation);
        float glY = (float)PaperDoll.config.y + (20.0f + (float)PaperDoll.config.render_height / 2.0f);
        float glX = (float)PaperDoll.config.x + 20.0f;
        int scaleY = MathHelper.ceil((float)PaperDoll.config.render_height / (PaperDoll.config.dynamic_scale ? player.getHeight() : 2.0f));
        int scaleX = MathHelper.ceil((float)PaperDoll.config.render_width / (PaperDoll.config.dynamic_scale ? player.getWidth() : 1.0f));
        float scale = Math.min(scaleX, scaleY) * -1.0f;        
        
        RenderSystem.translatef(glX, glY - (PaperDoll.config.change_swim_fly && (player.isSwimming() || player.isFallFlying()) ? (float) PaperDoll.config.render_height / 2.0f : 0.0f), 50.0f);
        matrixStack.scale(scale, scale, scale);

        // Render the doll
        entityRenderDispatcher.setRenderShadows(false);
        VertexConsumerProvider.Immediate immediate = this.client.getBufferBuilders().getEntityVertexConsumers();
        entityRenderDispatcher.render(player, 0.0, 0.0, 0.0, 0.0f, 1.0f, matrixStack, immediate, 0xF000F0);
        immediate.draw();

        // Put back the info we stored earlier
        entityRenderDispatcher.setRenderShadows(entityShadows);
        RenderSystem.popMatrix();
        player.pitch = pitch;
        player.setBodyYaw(yaw);
        player.setHeadYaw(headYaw);
    }
}
