import model.Model;
import presenter.Presenter;
import view.ConsoleUI;
import view.View;


public class Main {
    public static void main(String[] args) {

        Model model = new Model("final_work_2/files/");
        View view = new ConsoleUI();
        new Presenter(view, model);
        view.start();
    }
}