import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static JFrame frame;
    public static JPanel panel;

    public static void main(String[] args) {
        frame = new JFrame("Todo list");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,40,40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel todo = new JPanel();
        JEditorPane todoText = new JEditorPane();
        JButton add = new JButton("+");
        add.addActionListener(e -> {
            Main.addTodo(todoText.getText());
            todoText.setText(null
            );
        });
        todo.add(todoText);
        todo.add(add);
        frame.add(panel);
        panel.add(todo);
        frame.pack();
        frame.setVisible(true);
    }

    public static void addTodo(String text){
        JPanel todo = new JPanel();
        JCheckBox isDone = new JCheckBox();
        JEditorPane todoText = new JEditorPane();
        todoText.setText(text);
        JButton remove = new JButton("-");
        isDone.addChangeListener(e -> {
            Font font = todoText.getFont();
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.STRIKETHROUGH, isDone.isSelected() ? TextAttribute.STRIKETHROUGH_ON : false);
            todoText.setFont(font.deriveFont(attributes));
            todoText.setEditable(!todoText.isEditable());
        });
        remove.addActionListener(e -> {
            panel.remove(todo);
            frame.pack();
        });
        todo.add(isDone);
        todo.add(todoText);
        todo.add(remove);
        panel.add(todo);
        frame.pack();
    }
}
