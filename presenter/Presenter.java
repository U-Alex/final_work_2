package presenter;

import model.Model;
import view.View;

public class Presenter {
    
    private final View view;
    private final Model model;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setPresenter(this);
    }
    
    public void load() {
        this.model.load();
    }

    public void takeToy() {
        this.view.printText(this.model.takeToy());
    }

    public void printResult(String text) {
        this.view.printText(text);
    }

    public void printList() {
        this.view.printText(this.model.toString());
    }
    
}
