package calculaAreaCobertura;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class Tela extends Shell {
	private Text lbVertices;
	private Text lbArestas;
	private Text lbValores;
	static Label lbResultado1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Tela shell = new Tela(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public Tela(Display display) {
		super(display, SWT.SHELL_TRIM | SWT.BORDER);
		setImage(SWTResourceManager.getImage(Tela.class, "/javax/swing/plaf/metal/icons/Warn.gif"));
		
		Label lblVrticesEx = new Label(this, SWT.NONE);
		lblVrticesEx.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblVrticesEx.setBounds(33, 10, 170, 32);
		lblVrticesEx.setText("V\u00E9rtices [ Ex: A, B, C ] :");
		
		Label lblArestasExabac = new Label(this, SWT.NONE);
		lblArestasExabac.setText("Arestas [ Ex:(A,B),(A,C) ] :");
		lblArestasExabac.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblArestasExabac.setBounds(10, 48, 193, 32);
		
		lbVertices = new Text(this, SWT.BORDER);
		lbVertices.setBounds(209, 10, 390, 21);
		
		lbArestas = new Text(this, SWT.BORDER);
		lbArestas.setBounds(209, 48, 390, 21);
		
		Label lblValoresEx = new Label(this, SWT.NONE);
		lblValoresEx.setText("Valores [ Ex: A, B, C ] :");
		lblValoresEx.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblValoresEx.setBounds(33, 86, 170, 32);
		
		lbValores = new Text(this, SWT.BORDER);
		lbValores.setBounds(209, 88, 390, 21);
		
		Button btnCalcular = new Button(this, SWT.NONE);
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//new RealizaCalculo("1,2,3,4,5,6,7,8","(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(2,3),"
					//	+ "(2,4),(2,5),(2,6),(2,7),(2,8),(3,4),(3,5),(3,6),(3,7),(3,8),(4,5),(4,6),(4,7),"
						//+ "(4,8),(5,6),(5,7),(5,8),(6,7),(6,8),(7,8)","240,210,340,280,200,345,120,265,175,"
							//	+ "215,180,185,155,260,115,350,435,195,160,330,295,230,360,400,170,175,205,305");
				new RealizaCalculo(lbVertices.getText(), lbArestas.getText(), lbValores.getText());						
			}
		});
		btnCalcular.setImage(SWTResourceManager.getImage(Tela.class, "/javax/swing/plaf/metal/icons/ocean/hardDrive.gif"));
		btnCalcular.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.ITALIC));
		btnCalcular.setBounds(241, 115, 110, 32);
		btnCalcular.setText("Calcular");
		
		lbResultado1 = new Label(this, SWT.NONE);
		lbResultado1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lbResultado1.setAlignment(SWT.CENTER);
		lbResultado1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lbResultado1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lbResultado1.setBounds(10, 153, 589, 270);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Calculando \u00C1rea de Cobertura");
		setSize(625, 467);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
