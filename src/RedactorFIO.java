import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class RedactorFIO {


    private JPanel MainPanel;
    private JPanel ButtonPanel;
    private JButton collapseButton;
    private JPanel TextPanel;
    private  JTextField textSurname;
    private  JTextField textName;
    private  JTextField textPatronymic;
    private JPanel SplitPanel;
    private JTextField textFIO;
    private JButton splitButton;
    private JLabel Label;
    private static String[] words;
    private static String message = "Возможные варианты: " +
            "Иванов Иван Иванович, Петрова Анна-Мария, Иванова-Петрова Ирина Сергеевна";

    public JPanel getMainPanel () {
        return MainPanel;
    }

    public RedactorFIO() {
        collapseButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

              if (textSurname.getText() == null || !checkText(textSurname.getText())
                        || textName.getText()== null || !checkText(textName.getText())
                        || !checkText(textPatronymic.getText())) {

                    JOptionPane.showMessageDialog(MainPanel, message,
                            "Данные введены неверно!", JOptionPane.PLAIN_MESSAGE);
                } else {
                  TextPanel.setVisible(false);
                  ButtonPanel.setVisible(false);
                  SplitPanel.setVisible(true);
                  textFIO.setText(fio(textSurname.getText(), textName.getText(),textPatronymic.getText()));
                   }
            }
        });

        splitButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFIO.getText() == null) {
                    JOptionPane.showMessageDialog(SplitPanel, message,
                            "Строка пуста! Введите ФИО", JOptionPane.PLAIN_MESSAGE);
                } else {
                    words = textFIO.getText().trim().split("[ ]+");
                    String regex = "[A-Za-zА-Яа-я\\- ]+";
                    if ((words.length < 2 || words.length > 3)
                            || !textFIO.getText().matches(regex)) {
                            JOptionPane.showMessageDialog(SplitPanel, message,
                                    "Введены неверные данные", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            SplitPanel.setVisible(false);
                            TextPanel.setVisible(true);
                            ButtonPanel.setVisible(true);
                            textSurname.setText(words[0].trim());
                            textName.setText(words[1].trim());
                            textPatronymic.setText(words[2].trim());
                    }
                }
            }
        });
    }

    private static boolean checkText(String text) {
        boolean result = false;
        String regex = "[A-Za-zА-Яа-я\\-]+";
        if(text.trim().length() == 0 || text.trim().matches(regex)) {
            result = true;
        }
        return result;
    }

    public static String fio (String surname, String name, String patronymic) {
        return surname.trim() + " " + name.trim() + " " + patronymic.trim();
        }


}
