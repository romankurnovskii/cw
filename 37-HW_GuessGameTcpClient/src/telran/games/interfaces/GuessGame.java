package telran.games.interfaces;

public interface GuessGame {
String startGame();
String prompt();   // сервер задет этим вопрос (любой - пользователю
String move(String userInput);
boolean isFinished();
}
