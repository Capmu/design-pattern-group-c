import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Initial editor content
        String content = "Initial code\n";
        
        // Stack to track editor states
        Stack<String> contentHistory = new Stack<>();
        Stack<Long> timestampHistory = new Stack<>();
        
        // Save the initial state and timestamp
        contentHistory.push(content);
        timestampHistory.push(System.currentTimeMillis());

        // Write some content and save state and timestamp
        content += "Additional code\n";
        contentHistory.push(content);
        timestampHistory.push(System.currentTimeMillis());

        // Write more content and save state and timestamp
        content += "More content\n";
        contentHistory.push(content);
        timestampHistory.push(System.currentTimeMillis());

        // Undo the last change (one step back)
        if (!contentHistory.isEmpty() && !timestampHistory.isEmpty()) {
            content = contentHistory.pop();
            long timestamp = timestampHistory.pop();
            System.out.println("Content: " + content);
            System.out.println("Timestamp: " + timestamp);
        }
    }
}
