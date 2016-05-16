package com.leviathanstudio.mineide.ui.wizard.events;

import java.util.List;

import com.leviathanstudio.mineide.ui.wizard.WizardStep;

import javafx.event.Event;
import javafx.event.EventType;

public class WizardEvent extends Event
{
    private static final long serialVersionUID = 9120992468786110967L;
    private int               currentStep;
    private List<WizardStep>  steps;

    public WizardEvent(int currentStep, List<WizardStep> steps, EventType<? extends Event> eventType)
    {
        super(eventType);
        this.currentStep = currentStep;
        this.steps = steps;
    }

    public static final EventType<WizardEvent> COMPLETED          = new EventType<WizardEvent>(Event.ANY,
            "WIZARD_COMPLETED");
    public static final EventType<WizardEvent> CANCELLED          = new EventType<WizardEvent>(Event.ANY,
            "WIZARD_CANCELLED");
    public static final EventType<WizardEvent> STEP_CHANGE_BEFORE = new EventType<WizardEvent>(Event.ANY,
            "WIZARD_STEP_CHANGE_BEFORE");
    public static final EventType<WizardEvent> STEP_CHANGE_AFTER  = new EventType<WizardEvent>(Event.ANY,
            "WIZARD_STEP_CHANGE_AFTER");

    public int getCurrentStep()
    {
        return this.currentStep;
    }

    public void setCurrentStep(int currentStep)
    {
        this.currentStep = currentStep;
    }

    public List<WizardStep> getSteps()
    {
        return this.steps;
    }

    public void setSteps(List<WizardStep> steps)
    {
        this.steps = steps;
    }
}