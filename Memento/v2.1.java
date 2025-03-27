import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

// Originator
@Getter
@Setter
class Editor {
    private String content;
    private long timestamp;

    public Editor(String content) {
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public void write(String text) {
        this.content += text;
        this.timestamp = System.currentTimeMillis(); // Update timestamp on modification
    }

    public EditorMemento createMemento() {
        return new EditorMemento(this.content, this.timestamp);
    }

    public void restoreFromMemento(EditorMemento memento) {
        this.content = memento.getContent();
        this.timestamp = memento.getTimestamp();
    }
}

// Memento
@Getter
class EditorMemento {
    private final String content;
    private final long timestamp;

    public EditorMemento(String content, long timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }
}

// Caretaker
class History {
    private final List<EditorMemento> mementos = new ArrayList<>();

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

        // Restore to previous state (one step back)
        editor.restoreFromMemento(history.getMemento(1));

        // Print editor content and its timestamp after restoring
        System.out.println("Content: " + editor.getContent());
        System.out.println("Timestamp: " + editor.getTimestamp());
    }
}
