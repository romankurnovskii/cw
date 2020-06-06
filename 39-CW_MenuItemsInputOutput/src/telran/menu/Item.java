package telran.menu;

public interface Item {
String displayName();
void perform();
default boolean isExit() {
	return false;
}
}
