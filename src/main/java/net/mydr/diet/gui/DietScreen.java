package net.mydr.diet.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.mydr.diet.diet;
import net.mydr.diet.world.test.IntComponent;

import java.awt.*;

import static net.mydr.diet.world.test.MyComponents.*;

@Environment(EnvType.CLIENT)
public class DietScreen extends Screen {

    private static final ResourceLocation BACKGROUND =
            new ResourceLocation("minecraft", "textures/gui/demo_background.png");
    private static final ResourceLocation ICONS =
            new ResourceLocation(diet.MOD_ID, "textures/gui/ui.png");
    private static final ResourceLocation FULL =
            new ResourceLocation(diet.MOD_ID, "textures/gui/full.png");
    private static final ResourceLocation EMPTY =
            new ResourceLocation(diet.MOD_ID, "textures/gui/empty.png");

    private final int xSize;
    private final int ySize;

    public DietScreen() {
        super(Component.literal("Diet Screen"));
        this.xSize = 248;
        this.ySize = 5 * 20 + 60;
    }

    @Override
    public void renderBackground(PoseStack poseStack, int i) {
        super.renderBackground(poseStack, i);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int k = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        GuiComponent.blit(poseStack, k, j, this.xSize, 4, 0, 0, 248, 4, 256, 256);
        GuiComponent.blit(poseStack, k, j + 4, this.xSize, this.ySize - 8, 0, 4, 248, 24, 256, 256);
        GuiComponent.blit(poseStack, k, j + this.ySize - 4, this.xSize, 4, 0, 162, 248, 4, 256, 256);
        k = k + 40;
        j = j + 10;
        ItemStack appleStack = new ItemStack(Items.APPLE); // Create an ItemStack of an apple
        itemRenderer.renderGuiItem(appleStack, k, j); // Render the apple with its texture at position (100, 100)
        ItemStack breadStack = new ItemStack(Items.BREAD); // Create an ItemStack of an apple
        itemRenderer.renderGuiItem(breadStack, k, j+30*1); // Render the apple with its texture at position (100, 100)
        ItemStack beefStack = new ItemStack(Items.COOKED_BEEF); // Create an ItemStack of an apple
        itemRenderer.renderGuiItem(beefStack, k, j+30*2); // Render the apple with its texture at position (100, 100)
        ItemStack carrotStack = new ItemStack(Items.CARROT); // Create an ItemStack of an apple
        itemRenderer.renderGuiItem(carrotStack, k, j+30*3); // Render the apple with its texture at position (100, 100)
        ItemStack HONEYStack = new ItemStack(Items.HONEY_BOTTLE); // Create an ItemStack of an apple
        itemRenderer.renderGuiItem(HONEYStack, k, j+30*4); // Render the apple with its texture at position (100, 100)
    }

    @Override
    public void render(PoseStack poseStack, int i, int j, float f) {
        renderBackground(poseStack,0);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,ICONS);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        int percent1 = DIET1.maybeGet(Minecraft.getInstance().player).map(IntComponent::getValue).orElse(0);
        int percent2 = DIET2.maybeGet(Minecraft.getInstance().player).map(IntComponent::getValue).orElse(0);
        int percent3 = DIET3.maybeGet(Minecraft.getInstance().player).map(IntComponent::getValue).orElse(0);
        int percent4 = DIET4.maybeGet(Minecraft.getInstance().player).map(IntComponent::getValue).orElse(0);
        int percent5 = DIET5.maybeGet(Minecraft.getInstance().player).map(IntComponent::getValue).orElse(0);
//        player.sendSystemMessage(Component.literal(percent+"%"));
        String percentText1 = "" + percent1 + "%";
        String percentText2 = "" + percent2 + "%";
        String percentText3 = "" + percent3 + "%";
        String percentText4 = "" + percent4 + "%";
        String percentText5 = "" + percent5 + "%";
        int xPos = x + 40 + 20 + 102 + 10;
        int yPos = y + 10 + 8;
        Color color = Color.RED;
        this.font.draw(poseStack, percentText1, (float) (xPos + 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText1, (float) (xPos - 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText1, (float) xPos, (float) (yPos + 1), 0);
        this.font.draw(poseStack, percentText1, (float) xPos, (float) (yPos - 1), 0);
        this.font.draw(poseStack, percentText1, (float) xPos, (float) yPos, color.getRGB());
        drawProgressBar(poseStack,x + 40 + 20,yPos,percent1,(float) color.getRed()/255,(float) color.getGreen()/255,(float) color.getBlue()/255,(float) color.getAlpha()/255);
        yPos = yPos + 30;
        color = Color.ORANGE;
        this.font.draw(poseStack, percentText2, (float) (xPos + 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText2, (float) (xPos - 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText2, (float) xPos, (float) (yPos + 1), 0);
        this.font.draw(poseStack, percentText2, (float) xPos, (float) (yPos - 1), 0);
        this.font.draw(poseStack, percentText2, (float) xPos, (float) yPos, color.getRGB());
        drawProgressBar(poseStack,x + 40 + 20,yPos,percent2,(float) color.getRed()/255,(float) color.getGreen()/255,(float) color.getBlue()/255,(float) color.getAlpha()/255);
        yPos = yPos + 30;
        color = Color.CYAN;
        this.font.draw(poseStack, percentText3, (float) (xPos + 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText3, (float) (xPos - 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText3, (float) xPos, (float) (yPos + 1), 0);
        this.font.draw(poseStack, percentText3, (float) xPos, (float) (yPos - 1), 0);
        this.font.draw(poseStack, percentText3, (float) xPos, (float) yPos, color.getRGB());
        drawProgressBar(poseStack,x + 40 + 20,yPos,percent3,(float) color.getRed()/255,(float) color.getGreen()/255,(float) color.getBlue()/255,(float) color.getAlpha()/255);
        yPos = yPos + 30;
        color = Color.GREEN;
        this.font.draw(poseStack, percentText4, (float) (xPos + 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText4, (float) (xPos - 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText4, (float) xPos, (float) (yPos + 1), 0);
        this.font.draw(poseStack, percentText4, (float) xPos, (float) (yPos - 1), 0);
        this.font.draw(poseStack, percentText4, (float) xPos, (float) yPos, color.getRGB());
        drawProgressBar(poseStack,x + 40 + 20,yPos,percent4,(float) color.getRed()/255,(float) color.getGreen()/255,(float) color.getBlue()/255,(float) color.getAlpha()/255);
        yPos = yPos + 30;
        color = Color.PINK;
        this.font.draw(poseStack, percentText5, (float) (xPos + 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText5, (float) (xPos - 1), (float) yPos, 0);
        this.font.draw(poseStack, percentText5, (float) xPos, (float) (yPos + 1), 0);
        this.font.draw(poseStack, percentText5, (float) xPos, (float) (yPos - 1), 0);
        this.font.draw(poseStack, percentText5, (float) xPos, (float) yPos, color.getRGB());
        drawProgressBar(poseStack,x + 40 + 20,yPos,percent5,(float) color.getRed()/255,(float) color.getGreen()/255,(float) color.getBlue()/255,(float) color.getAlpha()/255);
        super.render(poseStack, i, j, f);
    }

    public void drawProgressBar(PoseStack poseStack, int x, int y, int percent,float red,float green,float blue,float alpha) {

        // 计算前景图像的大小和位置
        int width = 102;
        int height = 5;
        int uOffset = 0;
        int vOffset = 0;
        int width_2 = (int) ((int)102*(percent/100.0));
        RenderSystem.setShaderColor(red, green, blue, alpha);
        // 绘制前景图像
        RenderSystem.setShaderTexture(0,EMPTY);
        this.blit(poseStack, x, y, uOffset, vOffset, width, height, 102, 5);
        // 绘制填充部分（已完成进度）
        RenderSystem.setShaderTexture(0,FULL);
        this.blit(poseStack, x, y, uOffset, vOffset, width_2, height, 102, 5);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
