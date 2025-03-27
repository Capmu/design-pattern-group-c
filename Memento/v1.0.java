import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Initial editor content
        String content = "Initial code\n";
        
        // Stack to track editor states
        Stack<String> history = new Stack<>();
        
        // Save the initial state
        history.push(content);
        
        // Write some content and save state
        content += "Additional code\n";
        history.push(content);

        // Write more content and save state
        content += "More content\n";
        history.push(content);

        // Undo the last change (one step back)
        if (!history.isEmpty()) {
            content = history.pop();
        }

        // Print editor content after undo
        System.out.println(content);
    }
}
