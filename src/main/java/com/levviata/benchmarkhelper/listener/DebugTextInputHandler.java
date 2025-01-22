package com.levviata.benchmarkhelper.listener;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import static com.levviata.benchmarkhelper.listener.BenchmarkButtonHandler.BENCHMARK_BUTTON_ID;

public class DebugTextInputHandler {

    private GuiTextField textFieldX; // Text input field
    private GuiTextField textFieldY; // Text input field
    private static final int SUBMIT_BUTTON_ID = 993;
    public static int newX = 0; // Variable to store the input text
    public static int newY = 0; // Variable to store the input text

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        // Check if the current GUI is the world selection screen
        if (event.getGui() instanceof GuiWorldSelection) {
            GuiScreen gui = event.getGui();
            int width = gui.width;
            int height = gui.height;
            /*
            // Create a text field
            textFieldX = new GuiTextField(991, gui.mc.fontRenderer, width / 2 - 100, height - 200, 200, 20);
            textFieldX.setMaxStringLength(100); // Max characters
            textFieldX.setFocused(false);       // Focus the field by default

            // Create a text field
            textFieldY = new GuiTextField(992, gui.mc.fontRenderer, width / 2 - 100, height - 250, 200, 20);
            textFieldY.setMaxStringLength(100); // Max characters
            textFieldY.setFocused(false);       // Focus the field by default

            // Add a button to submit the text
            event.getButtonList().add(new GuiButton(
                    SUBMIT_BUTTON_ID,
                    width / 2 - 50,
                    height - 175,
                    100,
                    20,
                    "Submit"
            ));*/
        }
    }

    @SubscribeEvent
    public void onGuiDraw(GuiScreenEvent.DrawScreenEvent.Post event) {
        // Check if the current GUI is the world selection screen
        if (event.getGui() instanceof GuiWorldSelection && textFieldX != null) {
            textFieldX.drawTextBox(); // Draw the text field
            textFieldY.drawTextBox(); // Draw the text field
        }
    }

    @SubscribeEvent
    public void onKeyTyped(GuiScreenEvent.KeyboardInputEvent.Post event) {
        // Check if the current GUI is the world selection screen
        if (event.getGui() instanceof GuiWorldSelection && textFieldX != null) {
            // Forward the typed key to the text field
            char typedChar = Keyboard.getEventCharacter(); // Get the typed character
            int keyCode = Keyboard.getEventKey(); // Get the key code

            if (Keyboard.getEventKey() == Keyboard.KEY_RETURN && Keyboard.getEventKeyState()) {
                // Perform the action when Enter is pressed
                System.out.println("Enter key was pressed!");

                // If you have text fields, you can handle the input here
                if (textFieldX != null && textFieldX.isFocused()) {
                    if (!textFieldX.getText().isEmpty())
                        newX = Integer.parseInt(textFieldX.getText()); // Save the text to a variable
                }

                if (textFieldY != null && textFieldY.isFocused()) {
                    if (!textFieldY.getText().isEmpty())
                        newY = Integer.parseInt(textFieldY.getText());
                }
            }

            textFieldX.textboxKeyTyped(typedChar, keyCode);
            textFieldY.textboxKeyTyped(typedChar, keyCode);
        }
    }

    @SubscribeEvent
    public void onMouseClicked(GuiScreenEvent.MouseInputEvent.Pre event) {
        // Handle mouse clicks for the text field
        if (event.getGui() instanceof GuiWorldSelection && textFieldX != null) {
            int mouseX = org.lwjgl.input.Mouse.getEventX() * event.getGui().width / event.getGui().mc.displayWidth;
            int mouseY = event.getGui().height - org.lwjgl.input.Mouse.getEventY() * event.getGui().height / event.getGui().mc.displayHeight - 1;
            int mouseButton = org.lwjgl.input.Mouse.getEventButton();

            textFieldX.mouseClicked(mouseX, mouseY, mouseButton);
            textFieldY.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @SubscribeEvent
    public void onButtonClick(GuiScreenEvent.ActionPerformedEvent.Post event) {
        // Check if the clicked button is the submit button
        if (event.getButton().id == SUBMIT_BUTTON_ID && textFieldX != null) {
            if (!textFieldX.getText().isEmpty())
                newX = Integer.parseInt(textFieldX.getText()); // Save the text to a variable
            if (!textFieldY.getText().isEmpty())
                newY = Integer.parseInt(textFieldY.getText());

            // Find and update the button
            GuiButton button = event.getButtonList().stream()
                    .filter(b -> b.id == BENCHMARK_BUTTON_ID)
                    .findFirst()
                    .orElse(null);

            if (button != null) {
                button.x = newX;
                button.y = newY;
            }
        }
    }
}
