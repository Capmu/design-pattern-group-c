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
        return new EditorMemento(this.content, System.currentTimeMillis());
    }

    public void restoreFromMemento(EditorMemento memento) {
        this.content = memento.getSavedContent();
    }
}

// Memento
class EditorMemento {
    private String content;
    private long timestamp;

    public EditorMemento(String content, long timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getSavedContent() {
        return this.content;
    }

    public long getTimestamp() {
        return this.timestamp;
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

        // Write some content and save the state with timestamp
        editor.write("Additional code\n");
        history.addMemento(editor.createMemento());

        // Write more content and save the state with timestamp
        editor.write("More content\n");
        history.addMemento(editor.createMemento());

        // Restore to previous state (one step back)
        editor.restoreFromMemento(history.getMemento(1));

        // Print editor content and its timestamp after restoring
        EditorMemento restoredMemento = history.getMemento(1);
        System.out.println("Content: " + editor.getContent());
        System.out.println("Timestamp: " + restoredMemento.getTimestamp());
    }
}
