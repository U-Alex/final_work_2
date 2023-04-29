package view;

import java.util.Scanner;

import presenter.Presenter;

public class ConsoleUI implements View {
    private Presenter presenter;
    private final Scanner scanner;
    private boolean working;
    
    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.working = true;
    }
    
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void start() {
        presenter.load();
        while (this.working) {
            this.printText("1 - список всех призов | 2 - попытка розыгрыша | x - выход");
            String key = inputParam();
            if (key.equalsIgnoreCase("x")) { 
                this.working = false; 
                continue;
            }
            if (key.equalsIgnoreCase("1")) { 
                this.presenter.printList(); 
            }
            if (key.equalsIgnoreCase("2")) { 
                this.printText("получено: ");
                this.presenter.takeToy(); 
            }
        }
    }
    
    @Override
    public void printText(String text) {
        System.out.println(text);
    }
    
    private String inputParam() {
        while (!scanner.hasNext()) scanner.next();
        return scanner.nextLine();
    }
}
