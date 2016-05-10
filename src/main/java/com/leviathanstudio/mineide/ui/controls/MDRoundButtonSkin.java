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
    private final StackPane buttonContainer = new StackPane();
    private JFXRippler buttonRippler;
    private Transition clickedAnimation;
    private final CornerRadii defaultRadii = new CornerRadii(3);
    
    private boolean invalid = true;
    
    public MDRoundButtonSkin(JFXButton button)
    {
        super(button);
        
        this.buttonRippler = new JFXRippler(new StackPane(), RipplerMask.CIRCLE, RipplerPos.FRONT)
        {
            @Override
            protected void initListeners()
            {
                this.ripplerPane.setOnMousePressed((event) -> {
                    this.createRipple(event.getX(), event.getY());
                });
            }
        };
        this.buttonContainer.getChildren().add(this.buttonRippler);
        
        button.buttonTypeProperty().addListener((o, oldVal, newVal) -> this.updateButtonType(newVal));
        button.setOnMousePressed((e) -> {
            if(this.clickedAnimation != null)
            {
                this.clickedAnimation.setRate(1);
                this.clickedAnimation.play();
            }
        });
        button.setOnMouseReleased((e) -> {
            if(this.clickedAnimation != null)
            {
                this.clickedAnimation.setRate(-1);
                this.clickedAnimation.play();
            }
        });
        
        button.setPickOnBounds(false);
        this.buttonContainer.setPickOnBounds(false);
        
        this.buttonContainer.borderProperty().bind(this.getSkinnable().borderProperty());
        this.buttonContainer.backgroundProperty().bind(Bindings.createObjectBinding(() -> {
            if(button.getBackground() == null || this.isJavaDefaultBackground(button.getBackground()) || this.isJavaDefaultClickedBackground(button.getBackground()))
                button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, this.defaultRadii, null)));
            
            if(this.getSkinnable().getBackground() != null && this.getSkinnable().getBackground().getFills().get(0).getInsets().equals(new Insets(-0.2, -0.2, -0.2, -0.2)))
                return new Background(new BackgroundFill(this.getSkinnable().getBackground() != null ? this.getSkinnable().getBackground().getFills().get(0).getFill() : Color.TRANSPARENT, this.getSkinnable().backgroundProperty().get() != null ? this.getSkinnable().getBackground().getFills().get(0).getRadii() : this.defaultRadii, Insets.EMPTY));
            else
                return new Background(new BackgroundFill(this.getSkinnable().getBackground() != null ? this.getSkinnable().getBackground().getFills().get(0).getFill() : Color.TRANSPARENT, this.getSkinnable().getBackground() != null ? this.getSkinnable().getBackground().getFills().get(0).getRadii() : this.defaultRadii, Insets.EMPTY));
        }, this.getSkinnable().backgroundProperty()));
        
        button.ripplerFillProperty().addListener((o, oldVal, newVal) -> this.buttonRippler.setRipplerFill(newVal));
        
        if(button.getBackground() == null || this.isJavaDefaultBackground(button.getBackground()))
            button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, this.defaultRadii, null)));
        
        this.updateButtonType(button.getButtonType());
        this.updateChildren();
    }
    
    @Override
    protected void updateChildren()
    {
        super.updateChildren();
        if(this.buttonContainer != null)
            this.getChildren().add(0, this.buttonContainer);
        for(int i = 1; i < this.getChildren().size(); i++)
            this.getChildren().get(i).setMouseTransparent(true);
    }
    
    @Override
    protected void layoutChildren(final double x, final double y, final double w, final double h)
    {
        if(this.invalid)
        {
            if(((JFXButton)this.getSkinnable()).getRipplerFill() == null)
            {
                for(int i = this.getChildren().size() - 1; i >= 1; i--)
                    if(this.getChildren().get(i) instanceof LabeledText)
                    {
                        this.buttonRippler.setRipplerFill(((LabeledText)this.getChildren().get(i)).getFill());
                        ((LabeledText)this.getChildren().get(i)).fillProperty().addListener((o, oldVal, newVal) -> this.buttonRippler.setRipplerFill(newVal));
                        break;
                    }
                    else if(this.getChildren().get(i) instanceof Label)
                    {
                        this.buttonRippler.setRipplerFill(((Label)this.getChildren().get(i)).getTextFill());
                        ((Label)this.getChildren().get(i)).textFillProperty().addListener((o, oldVal, newVal) -> this.buttonRippler.setRipplerFill(newVal));
                        break;
                    }
            }
            else
                this.buttonRippler.setRipplerFill(((JFXButton)this.getSkinnable()).getRipplerFill());
            this.invalid = false;
        }
        double shift = 1;
        this.buttonContainer.resizeRelocate(this.getSkinnable().getLayoutBounds().getMinX() - shift, this.getSkinnable().getLayoutBounds().getMinY() - shift, this.getSkinnable().getWidth() + 2 * shift, this.getSkinnable().getHeight() + 2 * shift);
        this.layoutLabelInArea(x, y, w, h);
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
        switch(type)
        {
            case RAISED:
                JFXDepthManager.setDepth(this.buttonContainer, 2);
                this.clickedAnimation = new ButtonClickTransition();
                break;
            default:
                this.buttonContainer.setEffect(null);
                break;
        }
    }
    
    private class ButtonClickTransition extends CachedTransition
    {
        
        public ButtonClickTransition()
        {
            super(MDRoundButtonSkin.this.buttonContainer, new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(((DropShadow)MDRoundButtonSkin.this.buttonContainer.getEffect()).radiusProperty(), JFXDepthManager.getShadowAt(2).radiusProperty().get(), Interpolator.EASE_BOTH), new KeyValue(((DropShadow)MDRoundButtonSkin.this.buttonContainer.getEffect()).spreadProperty(), JFXDepthManager.getShadowAt(2).spreadProperty().get(), Interpolator.EASE_BOTH), new KeyValue(((DropShadow)MDRoundButtonSkin.this.buttonContainer.getEffect()).offsetXProperty(), JFXDepthManager.getShadowAt(2).offsetXProperty().get(), Interpolator.EASE_BOTH), new KeyValue(((DropShadow)MDRoundButtonSkin.this.buttonContainer.getEffect()).offsetYProperty(), JFXDepthManager.getShadowAt(2).offsetYProperty().get(), Interpolator.EASE_BOTH)), new KeyFrame(Duration.millis(1000), new KeyValue(((DropShadow)MDRoundButtonSkin.this.buttonContainer.getEffect()).radiusProperty(), JFXDepthManager.getShadowAt(5).radiusProperty().get(), Interpolator.EASE_BOTH), new KeyValue(((DropShadow)MDRoundButtonSkin.this.buttonContainer.getEffect()).spreadProperty(), JFXDepthManager.getShadowAt(5).spreadProperty().get(), Interpolator.EASE_BOTH), new KeyValue(((DropShadow)MDRoundButtonSkin.this.buttonContainer.getEffect()).offsetXProperty(), JFXDepthManager.getShadowAt(5).offsetXProperty().get(), Interpolator.EASE_BOTH), new KeyValue(((DropShadow)MDRoundButtonSkin.this.buttonContainer.getEffect()).offsetYProperty(), JFXDepthManager.getShadowAt(5).offsetYProperty().get(), Interpolator.EASE_BOTH))));
            this.setCycleDuration(Duration.seconds(0.2));
            this.setDelay(Duration.seconds(0));
        }
    }
}