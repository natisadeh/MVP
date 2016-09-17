package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import model.MyModel;
import presenter.Presenter;
import view.MyView;

public class Run {

	public static void main(String[] args) {
	
		MyModel model = new MyModel();
		MyView view = new MyView(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out));
		Presenter p = new Presenter(view, model);
		model.addObserver(p);
		view.addObserver(p);
		view.start();
	}

}