package fr.zeamateis.mineide.editor;

import fr.zeamateis.mineide.reader.HtmlReader;
import fr.zeamateis.mineide.ui.Gui;

import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

public class CodeEditor extends StackPane
{
    /** a webview used to encapsulate the CodeMirror JavaScript. */
    final WebView webview = new WebView();
    
    /** a snapshot of the code to be edited kept for easy initilization and reversion of editable code. */
    private String editingCode;
    
    /**
     * a template for editing code - this can be changed to any template derived from the supported modes at http://codemirror.net to allow syntax highlighted editing of a wide variety of languages.
     */
    HtmlReader editorHtml = new HtmlReader();
    
    private final String editingTemplate;
    
    /** applies the editing template to the editing code to create the html+javascript source for a code editor. */
    private String applyEditingTemplate()
    {
        return editingTemplate.replace("${code}", editingCode);
    }
    
    /** sets the current code in the editor and creates an editing snapshot of the code which can be reverted to. */
    public void setCode(String newCode)
    {
        this.editingCode = newCode;
        webview.getEngine().loadContent(applyEditingTemplate());
    }
    
    /** returns the current code in the editor and updates an editing snapshot of the code which can be reverted to. */
    public String getCodeAndSnapshot()
    {
        this.editingCode = (String)webview.getEngine().executeScript("editor.getValue();");
        return editingCode;
    }
    
    /** revert edits of the code to the last edit snapshot taken. */
    public void revertEdits()
    {
        setCode(editingCode);
    }
    
    /**
     * Create a new code editor.
     * 
     * @param editingCode
     *            the initial code to be edited in the code editor.
     */
    public CodeEditor(String editingCode)
    {
        editorHtml.initReading("/files/html/editor.html");
        this.editingTemplate = editorHtml.getOutputContent();
        
        this.editingCode = editingCode;
        
        webview.setPrefSize(Gui.width, Gui.height);
        webview.setMinSize(Gui.width, Gui.height);
        webview.getEngine().loadContent(applyEditingTemplate());
        
        this.getChildren().add(webview);
    }
}