package telran.games.interfaces;

public interface GuessGame {
String startGame();
String prompt();
String move(String userInput);
boolean isFinished();
}
