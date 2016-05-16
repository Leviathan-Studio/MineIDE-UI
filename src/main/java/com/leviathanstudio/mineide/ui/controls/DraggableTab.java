package com.leviathanstudio.mineide.ui.controls;

import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.ui.tab.TabManagement;
import com.leviathanstudio.mineide.utils.Utils;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class DraggableTab extends Tab
{
    private Label nameLabel;
    private ImageView icon;
    private Text dragText;
    private static final Stage markerStage;
    private Stage dragStage;
    private boolean detachable;
    
    private final int ICON_SIZE = 16;
    
    static
    {
        markerStage = new Stage();
        DraggableTab.markerStage.initStyle(StageStyle.UNDECORATED);
        Rectangle dummy = new Rectangle(3, 22, Color.web("#555555"));
        StackPane markerStack = new StackPane();
        markerStack.getChildren().add(dummy);
        DraggableTab.markerStage.setScene(new Scene(markerStack));
    }
    
    /**
     * Create a new draggable tab. This can be added to any normal TabPane, however a TabPane with draggable tabs must *only* have DraggableTabs, normal tabs and DrragableTabs mixed will cause issues!
     * <p>
     *
     * @param text
     *            the text to appear on the tag label.
     */
    public DraggableTab(String text, String iconPath)
    {
        // container for the icon and the text
        HBox hBox = new HBox();
        
        this.icon = new ImageView(DraggableTab.class.getResource(iconPath).toString());
        this.icon.setFitWidth(this.ICON_SIZE);
        this.icon.setFitHeight(this.ICON_SIZE);
        this.nameLabel = new Label(text);
        hBox.getChildren().addAll(this.icon, this.nameLabel);
        
        this.setGraphic(hBox);
        
        this.detachable = true;
        this.dragStage = new Stage();
        this.dragStage.initStyle(StageStyle.UNDECORATED);
        StackPane dragStagePane = new StackPane();
        dragStagePane.setStyle("-fx-background-color:#DDDDDD;");
        this.dragText = new Text(text);
        StackPane.setAlignment(this.dragText, Pos.CENTER);
        dragStagePane.getChildren().add(this.dragText);
        this.dragStage.setScene(new Scene(dragStagePane));
        
        hBox.setOnMouseDragged((MouseEvent t) -> {
            // the moving tab
            this.dragStage.setWidth(hBox.getWidth() + 10);
            this.dragStage.setHeight(hBox.getHeight() + 10);
            this.dragStage.setX(t.getScreenX());
            this.dragStage.setY(t.getScreenY());
            this.dragStage.show();
            Point2D screenPoint = new Point2D(t.getScreenX(), t.getScreenY());
            TabManagement.tabPanes.add(this.getTabPane());
            InsertData data = this.getInsertData(screenPoint);
            // Not ready to insert into a tab pane, hide the marker
            if(data == null || data.getInsertPane().getTabs().isEmpty())
                DraggableTab.markerStage.hide();
            else
            {
                int index = data.getIndex();
                boolean end = false;
                double x;
                // Insert at the end of the tab pane
                if(index == data.getInsertPane().getTabs().size())
                {
                    end = true;
                    index--;
                }
                Tab tab = data.getInsertPane().getTabs().get(index);
                
                Rectangle2D rect = this.getAbsoluteRect(tab);
                // Position the marker after the tab
                if(end)
                    x = rect.getMaxX() + 13;
                else
                    x = rect.getMinX() - this.ICON_SIZE; // width of the icon
                // Move the marker if the tab is selected
                if(tab.isSelected())
                {
                    Rectangle2D rectS = this.getAbsoluteRect(tab);
                    double middle = (rectS.getMinX() + rectS.getMaxX()) / 2;
                    
                    if(middle < x)
                        x += 13;
                }
                // Set position of the marker and show it
                DraggableTab.markerStage.setX(x);
                DraggableTab.markerStage.setY(rect.getMaxY() + 12);
                DraggableTab.markerStage.show();
            }
        });
        // Put the tab into the selected tab pane
        hBox.setOnMouseReleased((MouseEvent t) -> {
            // hide moving elements
            DraggableTab.markerStage.hide();
            this.dragStage.hide();
            if(!t.isStillSincePress())
            {
                Point2D screenPoint = new Point2D(t.getScreenX(), t.getScreenY());
                TabPane oldTabPane = this.getTabPane();
                int oldIndex = oldTabPane.getTabs().indexOf(DraggableTab.this);
                TabManagement.tabPanes.add(oldTabPane);
                InsertData insertData = this.getInsertData(screenPoint);
                if(insertData != null)
                {
                    int addIndex = insertData.getIndex();
                    if(oldTabPane == insertData.getInsertPane() && oldTabPane.getTabs().size() == 1)
                        return;
                    oldTabPane.getTabs().remove(DraggableTab.this);
                    if(oldIndex < addIndex && oldTabPane == insertData.getInsertPane())
                        addIndex--;
                    if(addIndex > insertData.getInsertPane().getTabs().size())
                        addIndex = insertData.getInsertPane().getTabs().size();
                    insertData.getInsertPane().getTabs().add(addIndex, DraggableTab.this);
                    insertData.getInsertPane().selectionModelProperty().get().select(addIndex);
                    return;
                }
                if(!this.detachable)
                    return;
                final Stage newStage = new Stage();
                final CloseableTabPane pane = new CloseableTabPane(newStage);
                TabManagement.tabPanes.add(pane);
                
                // remove tab pane from tab management when hide
                newStage.setOnHiding(new EventHandler<WindowEvent>()
                {
                    @Override
                    public void handle(WindowEvent t)
                    {
                        TabManagement.tabPanes.remove(pane);
                    }
                });
                
                // remove tab from old tab pane
                this.getTabPane().getTabs().remove(DraggableTab.this);
                // add tab to the new tab pane
                pane.getTabs().add(DraggableTab.this);
                
                // hide the panel when tab pane is empty
                pane.getTabs().addListener(new ListChangeListener<Tab>()
                {
                    @Override
                    public void onChanged(ListChangeListener.Change<? extends Tab> change)
                    {
                        if(pane.getTabs().isEmpty())
                            newStage.hide();
                    }
                });
                // Initialize the new windows
                newStage.setTitle(Gui.mineIdeInfo.getAppName() + " v" + Gui.mineIdeInfo.getAppVersion() + " " + "Forge " + Gui.mineIdeInfo.getForgeVersion());
                newStage.getIcons().add(new Image(Utils.IMG_DIR + "icon.png"));
                newStage.setScene(new Scene(pane));
                newStage.initStyle(StageStyle.UTILITY);
                newStage.setX(t.getScreenX());
                newStage.setY(t.getScreenY());
                newStage.show();
                
                // Focus on the new tab
                pane.requestLayout();
                pane.requestFocus();
                
            }
            
        });
    }
    
    /**
     * Set whether it's possible to detach the tab from its pane and move it to another pane or another window. Defaults to true.
     * <p>
     *
     * @param detachable
     *            true if the tab should be detachable, false otherwise.
     */
    public void setDetachable(boolean detachable)
    {
        this.detachable = detachable;
    }
    
    /**
     * Set the label text on this draggable tab. This must be used instead of setText() to set the label, otherwise weird side effects will result!
     * <p>
     *
     * @param text
     *            the label text for this tab.
     */
    public void setLabelText(String text)
    {
        this.nameLabel.setText(text);
        this.dragText.setText(text);
    }
    
    private InsertData getInsertData(Point2D screenPoint)
    {
        for(TabPane tabPane : TabManagement.tabPanes)
        {
            Rectangle2D tabAbsolute = this.getAbsoluteRect(tabPane);
            if(tabAbsolute.contains(screenPoint))
            {
                int tabInsertIndex = 0;
                if(!tabPane.getTabs().isEmpty())
                {
                    Rectangle2D firstTabRect = this.getAbsoluteRect(tabPane.getTabs().get(0));
                    if(firstTabRect.getMaxY() + 60 < screenPoint.getY() || firstTabRect.getMinY() > screenPoint.getY())
                        return null;
                    Rectangle2D lastTabRect = this.getAbsoluteRect(tabPane.getTabs().get(tabPane.getTabs().size() - 1));
                    if(screenPoint.getX() < firstTabRect.getMinX() + firstTabRect.getWidth() / 2)
                        tabInsertIndex = 0;
                    else if(screenPoint.getX() > lastTabRect.getMaxX() - lastTabRect.getWidth() / 2)
                        tabInsertIndex = tabPane.getTabs().size();
                    else
                        for(int i = 0; i < tabPane.getTabs().size() - 1; i++)
                        {
                            Tab leftTab = tabPane.getTabs().get(i);
                            Tab rightTab = tabPane.getTabs().get(i + 1);
                            if(leftTab instanceof DraggableTab && rightTab instanceof DraggableTab)
                            {
                                Rectangle2D leftTabRect = this.getAbsoluteRect(leftTab);
                                Rectangle2D rightTabRect = this.getAbsoluteRect(rightTab);
                                if(this.betweenX(leftTabRect, rightTabRect, screenPoint.getX()))
                                {
                                    tabInsertIndex = i + 1;
                                    break;
                                }
                            }
                        }
                }
                return new InsertData(tabInsertIndex, tabPane);
            }
        }
        return null;
    }
    
    private Rectangle2D getAbsoluteRect(Control node)
    {
        return new Rectangle2D(node.localToScene(node.getLayoutBounds().getMinX(), node.getLayoutBounds().getMinY()).getX() + node.getScene().getWindow().getX(), node.localToScene(node.getLayoutBounds().getMinX(), node.getLayoutBounds().getMinY()).getY() + node.getScene().getWindow().getY(), node.getWidth(), node.getHeight());
    }
    
    private Rectangle2D getAbsoluteRect(Tab tab)
    {
        Control node = ((DraggableTab)tab).getLabel();
        return this.getAbsoluteRect(node);
    }
    
    private Label getLabel()
    {
        return this.nameLabel;
    }
    
    private boolean betweenX(Rectangle2D r1, Rectangle2D r2, double xPoint)
    {
        double lowerBound = r1.getMinX() + r1.getWidth() / 2;
        double upperBound = r2.getMaxX() - r2.getWidth() / 2;
        return xPoint >= lowerBound && xPoint <= upperBound;
    }
    
    private static class InsertData
    {
        
        private final int index;
        private final TabPane insertPane;
        
        public InsertData(int index, TabPane insertPane)
        {
            this.index = index;
            this.insertPane = insertPane;
        }
        
        public int getIndex()
        {
            return this.index;
        }
        
        public TabPane getInsertPane()
        {
            return this.insertPane;
        }
        
    }
}
