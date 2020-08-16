// Decompiled with: CFR 0.150
package com.gitlab.indicode.fabric.paperdoll.mixin;

import com.gitlab.indicode.fabric.paperdoll.Config;
import com.gitlab.indicode.fabric.paperdoll.PaperDoll;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.math.Quaternion;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
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
        if (this.client.options.debugEnabled || this.client.options.hudHidden) {
            return;
        }

        Config config = PaperDoll.config;
        ClientPlayerEntity player = this.client.player;
        
        // Don't render if it's set to only activity
        if (!(!config.only_activity || player.isSneaking() || player.isSprinting() || player.isSwimming() || player.handSwinging || player.isFallFlying())) {
            return;
        }
        
        // Store sensitive info to put back later
        float pitch = player.pitch;
        float yaw = player.yaw;
        float headYaw = player.headYaw;
        RenderSystem.pushMatrix();

        // Setting the doll to be neutral
        player.pitch = 0.0f;
        player.setYaw((float)config.rotation);
        player.setHeadYaw((float)config.rotation);
        float glY = (float)config.y + (20.0f + (float)config.render_height / 2.0f);
        float glX = (float)config.x + 20.0f;
        int scaleY = MathHelper.ceil((float)config.render_height / (config.dynamic_scale ? player.getHeight() : 2.0f));
        int scaleX = MathHelper.ceil((float)config.render_width / (config.dynamic_scale ? player.getWidth() : 1.0f));
        float scale = Math.min(scaleX, scaleY);

        RenderSystem.translatef(glX, glY - (config.change_swim_fly && (player.isSwimming() || player.isFallFlying()) ? (float)config.render_height / 2.0f : 0.0f), 50.0f);

        MatrixStack matrixStack = new MatrixStack();
        matrixStack.scale(scale, scale, scale);

        // Turn the doll upright and looking at the middle of the screen
        Quaternion quaternion = Vector3f.POSITIVE_X.getDegreesQuaternion(180.0f);
        Quaternion quaternion2 = Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0f);
        quaternion.hamiltonProduct(quaternion2);
        matrixStack.multiply(quaternion);

        EntityRenderDispatcher entityRenderDispatcher = this.client.getEntityRenderManager();
        entityRenderDispatcher.setRenderShadows(false);
        VertexConsumerProvider.Immediate immediate = this.client.getBufferBuilders().getEntityVertexConsumers();
        entityRenderDispatcher.render(player, 0.0, 0.0, 0.0, 0.0f, 1.0f, matrixStack, immediate, 0xF000F0);
        immediate.draw();
        entityRenderDispatcher.setRenderShadows(true);

        // Put back the info we stored earlier
        RenderSystem.popMatrix();
        player.pitch = pitch;
        player.setYaw(yaw);
        player.setHeadYaw(headYaw);
    }
}
