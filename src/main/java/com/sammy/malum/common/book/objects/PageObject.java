package com.sammy.malum.common.book.objects;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sammy.malum.ClientHelper;
import com.sammy.malum.common.book.BookScreen;
import com.sammy.malum.common.book.categories.BookCategory;
import com.sammy.malum.common.book.pages.BookPage;
import net.minecraft.client.Minecraft;

import java.util.function.Predicate;

import static com.sammy.malum.common.book.BookScreen.screen;
import static net.minecraft.client.gui.AbstractGui.blit;

public class PageObject extends BookObject
{
    public final BookPage page;
    
    public PageObject(BookPage page, int posX, int posY, int width, int height, Predicate<BookScreen> showPredicate)
    {
        super(posX, posY, width, height, showPredicate);
        this.page = page;
    }
    
    @Override
    public void draw(Minecraft minecraft, MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, boolean isHovering)
    {
        minecraft.getTextureManager().bindTexture(screen.texture());
        blit(matrixStack, posX, posY, 1, 193, width, height, 512, 512);
        Minecraft.getInstance().getItemRenderer().renderItemAndEffectIntoGUI(page.iconStack, posX + 2, posY);
        screen.renderPurpleText(matrixStack, ClientHelper.simpleTranslatableComponent(page.translationKey), posX + 20, posY + 3);
    }
    
    @Override
    public void interact(BookScreen screen)
    {
        screen.currentPage = page;
    }
}