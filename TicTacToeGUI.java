import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {

    private JButton[][] buttons;
    private char currentPlayer;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        initializeButtons();
        initializeBoard();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j] = button;

                final int row = i;
                final int col = j;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onButtonClick(row, col);
                    }
                });
            }
        }
    }

    private void initializeBoard() {
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                add(buttons[i][j]);
            }
        }
    }

    private void onButtonClick(int row, int col) {
        if (buttons[row][col].getText().equals("")) {
            buttons[row][col].setText(String.valueOf(currentPlayer));

            if (checkForWinner(row, col)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkForWinner(int row, int col) {
        // Check row
        if (buttons[row][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        // Check column
        if (buttons[0][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][col].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        // Check diagonals
        if (row == col && buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        if (row + col == 2 && buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGUI::new);
    }
}
