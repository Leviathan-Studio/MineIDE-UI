package com.leviathanstudio.mineide.ui.controls;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXRippler.RipplerMask;
import com.jfoenix.controls.JFXRippler.RipplerPos;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.CachedTransition;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import com.sun.javafx.scene.control.skin.LabeledText;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MDRoundButtonSkin extends ButtonSkin
{
    private final StackPane   buttonContainer = new StackPane();
    private JFXRippler        buttonRippler;
    private Transition        clickedAnimation;
    private final CornerRadii defaultRadii    = new CornerRadii(3);

    private boolean           invalid         = true;

    public MDRoundButtonSkin(JFXButton button)
    {
        super(button);

        buttonRippler = new JFXRippler(new StackPane(), RipplerMask.CIRCLE, RipplerPos.FRONT)
        {
            @Override
            protected void initListeners()
            {
                ripplerPane.setOnMousePressed((event) ->
                {
                    createRipple(event.getX(), event.getY());
                });
            }
        };
        buttonContainer.getChildren().add(buttonRippler);

        button.buttonTypeProperty().addListener((o, oldVal, newVal) -> updateButtonType(newVal));
        button.setOnMousePressed((e) ->
        {
            if (clickedAnimation != null)
            {
                clickedAnimation.setRate(1);
                clickedAnimation.play();
            }
        });
        button.setOnMouseReleased((e) ->
        {
            if (clickedAnimation != null)
            {
                clickedAnimation.setRate(-1);
                clickedAnimation.play();
            }
        });

        button.setPickOnBounds(false);
        buttonContainer.setPickOnBounds(false);

        buttonContainer.borderProperty().bind(getSkinnable().borderProperty());
        buttonContainer.backgroundProperty().bind(Bindings.createObjectBinding(() ->
        {
            if (button.getBackground() == null || isJavaDefaultBackground(button.getBackground())
                    || isJavaDefaultClickedBackground(button.getBackground()))
                button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, defaultRadii, null)));

            if (getSkinnable().getBackground() != null && getSkinnable().getBackground().getFills().get(0).getInsets()
                    .equals(new Insets(-0.2, -0.2, -0.2, -0.2)))
            {
                return new Background(new BackgroundFill(
                        getSkinnable().getBackground() != null
                                ? getSkinnable().getBackground().getFills().get(0).getFill() : Color.TRANSPARENT,
                        getSkinnable().backgroundProperty().get() != null
                                ? getSkinnable().getBackground().getFills().get(0).getRadii() : defaultRadii,
                        Insets.EMPTY));
            }
            else
            {
                return new Background(new BackgroundFill(
                        getSkinnable().getBackground() != null
                                ? getSkinnable().getBackground().getFills().get(0).getFill() : Color.TRANSPARENT,
                        getSkinnable().getBackground() != null
                                ? getSkinnable().getBackground().getFills().get(0).getRadii() : defaultRadii,
                        Insets.EMPTY));
            }
        }, getSkinnable().backgroundProperty()));

        button.ripplerFillProperty().addListener((o, oldVal, newVal) -> buttonRippler.setRipplerFill(newVal));

        if (button.getBackground() == null || isJavaDefaultBackground(button.getBackground()))
            button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, defaultRadii, null)));

        updateButtonType(button.getButtonType());
        updateChildren();
    }

    @Override
    protected void updateChildren()
    {
        super.updateChildren();
        if (buttonContainer != null)
            getChildren().add(0, buttonContainer);
        for (int i = 1; i < getChildren().size(); i++)
            getChildren().get(i).setMouseTransparent(true);
    }

    @Override
    protected void layoutChildren(final double x, final double y, final double w, final double h)
    {
        if (invalid)
        {
            if (((JFXButton) getSkinnable()).getRipplerFill() == null)
            {
                for (int i = getChildren().size() - 1; i >= 1; i--)
                {
                    if (getChildren().get(i) instanceof LabeledText)
                    {
                        buttonRippler.setRipplerFill(((LabeledText) getChildren().get(i)).getFill());
                        ((LabeledText) getChildren().get(i)).fillProperty()
                                .addListener((o, oldVal, newVal) -> buttonRippler.setRipplerFill(newVal));
                        break;
                    }
                    else if (getChildren().get(i) instanceof Label)
                    {
                        buttonRippler.setRipplerFill(((Label) getChildren().get(i)).getTextFill());
                        ((Label) getChildren().get(i)).textFillProperty()
                                .addListener((o, oldVal, newVal) -> buttonRippler.setRipplerFill(newVal));
                        break;
                    }
                }
            }
            else
            {
                buttonRippler.setRipplerFill(((JFXButton) getSkinnable()).getRipplerFill());
            }
            invalid = false;
        }
        double shift = 1;
        buttonContainer.resizeRelocate(getSkinnable().getLayoutBounds().getMinX() - shift,
                getSkinnable().getLayoutBounds().getMinY() - shift, getSkinnable().getWidth() + (2 * shift),
                getSkinnable().getHeight() + (2 * shift));
        layoutLabelInArea(x, y, w, h);
    }

    private boolean isJavaDefaultBackground(Background background)
    {
        return background.getFills().get(0).getFill().toString().equals("0xffffffba");
    }

    private boolean isJavaDefaultClickedBackground(Background background)
    {
        return background.getFills().get(0).getFill().toString().equals("0x039ed3ff");
    }

    private void updateButtonType(ButtonType type)
    {
        switch (type)
        {
            case RAISED:
                JFXDepthManager.setDepth(buttonContainer, 2);
                clickedAnimation = new ButtonClickTransition();
                break;
            default:
                buttonContainer.setEffect(null);
                break;
        }
    }

    private class ButtonClickTransition extends CachedTransition
    {

        public ButtonClickTransition()
        {
            super(buttonContainer,
                    new Timeline(
                            new KeyFrame(Duration.ZERO, new KeyValue(
                                    ((DropShadow) buttonContainer.getEffect()).radiusProperty(),
                                    JFXDepthManager.getShadowAt(2).radiusProperty().get(), Interpolator.EASE_BOTH),
                                    new KeyValue(((DropShadow) buttonContainer.getEffect()).spreadProperty(),
                                            JFXDepthManager.getShadowAt(2).spreadProperty().get(),
                                            Interpolator.EASE_BOTH),
                                    new KeyValue(
                                            ((DropShadow) buttonContainer.getEffect()).offsetXProperty(),
                                            JFXDepthManager.getShadowAt(2).offsetXProperty().get(),
                                            Interpolator.EASE_BOTH),
                                    new KeyValue(
                                            ((DropShadow) buttonContainer.getEffect()).offsetYProperty(),
                                            JFXDepthManager.getShadowAt(2).offsetYProperty().get(),
                                            Interpolator.EASE_BOTH)),
                            new KeyFrame(
                                    Duration.millis(1000),
                                    new KeyValue(
                                            ((DropShadow) buttonContainer.getEffect()).radiusProperty(),
                                            JFXDepthManager.getShadowAt(5).radiusProperty().get(),
                                            Interpolator.EASE_BOTH),
                                    new KeyValue(
                                            ((DropShadow) buttonContainer.getEffect()).spreadProperty(),
                                            JFXDepthManager.getShadowAt(5).spreadProperty().get(),
                                            Interpolator.EASE_BOTH),
                                    new KeyValue(((DropShadow) buttonContainer.getEffect()).offsetXProperty(),
                                            JFXDepthManager.getShadowAt(5).offsetXProperty().get(),
                                            Interpolator.EASE_BOTH),
                                    new KeyValue(((DropShadow) buttonContainer.getEffect()).offsetYProperty(),
                                            JFXDepthManager.getShadowAt(5).offsetYProperty().get(),
                                            Interpolator.EASE_BOTH))));
            setCycleDuration(Duration.seconds(0.2));
            setDelay(Duration.seconds(0));
        }
    }
}