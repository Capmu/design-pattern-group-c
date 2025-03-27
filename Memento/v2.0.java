java
import java.util.ArrayList;
import java.util.List;

// Originator
class Editor {
    private String content;

    public Editor(String content) {
        this.content = content;
    }

    public void write(String text) {
        this.content += text;
    }

    public String getContent() {
        return this.content;
    }

    public EditorMemento createMemento() {
        return new EditorMemento(this.content);
    }

    public void restoreFromMemento(EditorMemento memento) {
        this.content = memento.getSavedContent();
    }
}

// Memento
class EditorMemento {
    private String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getSavedContent() {
        return this.content;
    }
}

// Caretaker
class History {
    private List<EditorMemento> mementos;

    public History() {
        this.mementos = new ArrayList<>();
    }

    public void addMemento(EditorMemento memento) {
        this.mementos.add(memento);
    }

    public EditorMemento getMemento(int index) {
        return this.mementos.get(index);
    }
}

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor("Initial code\n");
        History history = new History();

        // Write some content
        editor.write("Additional code\n");
        history.addMemento(editor.createMemento());

        // Write more content
        editor.write("More content\n");
        history.addMemento(editor.createMemento());

        // Restore to previous state
        editor.restoreFromMemento(history.getMemento(1));

        // Print editor content
        System.out.println(editor.getContent());
    }
}