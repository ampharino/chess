package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame gameFrame;
    private JPanel body;
    private JToolBar options;
    private Chessboard gameBoard;
    private int size;
    public int[] scores = new int[2];
    public String[] messages = new String[2];
    private JPanel sideBar;
    private JLabel blackScore;
    private JLabel whiteScore;
    private JLabel turn;
    private JLabel event;
    private JLabel checkmate;
    private JButton newGameButton;
    private JButton forfeitButton;
    private Font labelFont = new Font("Arial Unicode MS", Font.BOLD, 15);

    //Create gui frame
    public Main(int size){
        scores[0] = 0;
        scores[1] = 0;
        messages[0] = "WHITE";
        messages[1] = "";

        gameFrame = new JFrame("Assignment1");
        gameFrame.setSize(size+100,size);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        body = new JPanel(new BorderLayout(3,3));
        initOptions();
        gameFrame.add(body);
        initSidebar();
        gameFrame.setVisible(true);
    }


    //Add toolbar to GUI
    public void initOptions(){
        options = new JToolBar();
        newGameButton = new JButton("New");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        forfeitButton = new JButton("Forfeit");
        forfeitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameBoard.game.gameOver){
                    return;
                }
                if(gameBoard.game.blackTurn){
                    scores[1]++;
                    messages[1] = "BLACK FORFEITS.";
                    gameBoard.game.gameOver = true;

                }
                else{
                    scores[0]++;
                    messages[1] = "WHITE FORFEITS.";
                    gameBoard.game.gameOver = true;
                }
                updateScores();
                updateMessages();

            }
        });
        forfeitButton.setVisible(false);
        options.add(newGameButton);
        options.add(forfeitButton);
        options.setFloatable(false);
        body.add(options, BorderLayout.PAGE_START);

    }

    public void initSidebar(){
        sideBar = new JPanel(new GridLayout(20,1));
        sideBar.setPreferredSize(new Dimension(200,800));
        blackScore = new JLabel("BLACK: " +String.valueOf(scores[0]));
        whiteScore = new JLabel("WHITE: " +String.valueOf(scores[1]));
        turn = new JLabel(messages[0] + "'s turn");
        event = new JLabel(messages[1]);
        checkmate = new JLabel("CHECKMATE!");
        checkmate.setVisible(false);
        blackScore.setFont(labelFont);
        whiteScore.setFont(labelFont);
        turn.setFont(labelFont);
        event.setFont(labelFont);
        checkmate.setFont(labelFont);
        sideBar.add(blackScore);
        sideBar.add(whiteScore);
        sideBar.add(turn);
        sideBar.add(event);
        sideBar.add(checkmate);
        sideBar.setVisible(false);
        body.add(sideBar, BorderLayout.EAST);

    }


    //Create new game instance
    //Draw chessboard
    public void newGame(){
        checkmate.setVisible(false);
        forfeitButton.setVisible(true);
        messages[0] = "WHITE";
        messages[1] = "";
        sideBar.setVisible(true);
        if(gameBoard != null){
            body.remove(gameBoard);
        }
        gameBoard = new Chessboard(scores, messages, this);
        gameBoard.setBorder(new LineBorder(Color.black));
        body.add(gameBoard, BorderLayout.CENTER);
        updateMessages();
        body.revalidate();
        body.repaint();

    }

    public void updateScores(){
        blackScore.setText("BLACK: " +String.valueOf(scores[0]));
        whiteScore.setText("WHITE: " +String.valueOf(scores[1]));

    }

    public void updateMessages(){
        turn.setText(messages[0] + "'s turn");
        event.setText(messages[1]);
    }

    public void showCheckmateLabel(){
        checkmate.setVisible(true);
    }

    public static void main(String[] args) {
        Main session = new Main(800);
    }
}
