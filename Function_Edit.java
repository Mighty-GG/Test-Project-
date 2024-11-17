
public class Function_Edit
{
    GUI2 gui;

    public Function_Edit(GUI2 gui)
    {
        this.gui = gui;
    }

    public void undo()
    {
        gui.um.undo();
    }

    public void redo()
    {
        gui.um.redo();
    }
}
