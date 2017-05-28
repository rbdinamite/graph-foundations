package aplicaKruskal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class Tela extends Shell {
	static Label txtResultado;
	static Label lblDistancia;
	static Label lblNewLabel;

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
		super(display, SWT.SHELL_TRIM);
		setBackground(SWTResourceManager.getColor(255, 255, 0));
		setImage(SWTResourceManager.getImage(Tela.class, "/javax/swing/plaf/metal/icons/ocean/floppy.gif"));
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage("img\\grafo_inicial.png"));
		lblNewLabel.setBackground(SWTResourceManager.getColor(255, 255, 153));
		lblNewLabel.setBounds(10, 10, 500, 321);
		
		Button btnCalcularMelhorCaminho = new Button(this, SWT.NONE);
		btnCalcularMelhorCaminho.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new RealizaCalculo("A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q",
						"(A,B),(A,F),(B,C),(B,E),(C,E),(C,D),(D,O),(D,P),(E,F),(E,O),(E,L),(F,G),(G,I),(G,H),"
						+ "(H,J),(I,L),(I,K),(J,K),(K,M),(L,M),(L,N),(M,Q),(N,P),(N,Q),(P,Q)",
						"8,12,6,8,5,13,9,11,4,4,8,9,3,5,6,10,4,11,5,13,6,7,7,5,14");
				lblNewLabel.setImage(SWTResourceManager.
						getImage("img\\grafo_inicial_pronto.png"));
			}
		});
		btnCalcularMelhorCaminho.setImage(SWTResourceManager.getImage(Tela.class, "/com/sun/java/swing/plaf/windows/icons/UpFolder.gif"));
		btnCalcularMelhorCaminho.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		btnCalcularMelhorCaminho.setBounds(210, 337, 132, 33);
		btnCalcularMelhorCaminho.setText("Calcular");
		
		Label lblVrtices = new Label(this, SWT.NONE);
		lblVrtices.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblVrtices.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblVrtices.setBounds(516, 10, 68, 21);
		lblVrtices.setText("V\u00E9rtices: ");
		
		txtResultado = new Label(this, SWT.NONE);
		txtResultado.setAlignment(SWT.CENTER);
		txtResultado.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtResultado.setBounds(526, 37, 41, 296);
		
		lblDistancia = new Label(this, SWT.NONE);
		lblDistancia.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblDistancia.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblDistancia.setBounds(358, 344, 226, 26);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Representante - Algoritmo de Kruskal");
		setSize(607, 417);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
