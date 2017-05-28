package reconheceGrafo;

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
	private Button btnValorado;
	private Button btnDirigido;
	static Label lbResultado1;
	static Label lbResultado2;
	static Label lbResultado3;
	static Label lbResultado4;

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
		
		btnValorado = new Button(this, SWT.CHECK);
		btnValorado.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		btnValorado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (btnValorado.getSelection()){
					lbValores.setEnabled(true);
				}else{
					lbValores.setEnabled(false);
				}
			}
		});
		btnValorado.setBounds(105, 86, 98, 21);
		btnValorado.setText("Valorado");
		
		btnDirigido = new Button(this, SWT.CHECK);
		btnDirigido.setText("Dirigido");
		btnDirigido.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		btnDirigido.setBounds(298, 86, 98, 21);
		
		lbVertices = new Text(this, SWT.BORDER);
		lbVertices.setBounds(209, 10, 390, 21);
		
		lbArestas = new Text(this, SWT.BORDER);
		lbArestas.setBounds(209, 48, 390, 21);
		
		Label lblValoresEx = new Label(this, SWT.NONE);
		lblValoresEx.setText("Valores [ Ex: A, B, C ] :");
		lblValoresEx.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblValoresEx.setBounds(36, 113, 170, 32);
		
		lbValores = new Text(this, SWT.BORDER);
		lbValores.setBounds(209, 113, 390, 21);
		lbValores.setEnabled(false);
		
		Button btnCalcular = new Button(this, SWT.NONE);
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new RealizaCalculo(lbVertices.getText(), lbArestas.getText(), lbValores.getText(),btnValorado.getSelection() , btnDirigido.getSelection());						
			}
		});
		btnCalcular.setImage(SWTResourceManager.getImage(Tela.class, "/javax/swing/plaf/metal/icons/ocean/hardDrive.gif"));
		btnCalcular.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.ITALIC));
		btnCalcular.setBounds(242, 140, 110, 32);
		btnCalcular.setText("Calcular");
		
		lbResultado1 = new Label(this, SWT.NONE);
		lbResultado1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lbResultado1.setAlignment(SWT.CENTER);
		lbResultado1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lbResultado1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lbResultado1.setBounds(10, 176, 300, 125);
		
		lbResultado2 = new Label(this, SWT.NONE);
		lbResultado2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lbResultado2.setAlignment(SWT.CENTER);
		lbResultado2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lbResultado2.setBounds(309, 176, 290, 125);
		
		lbResultado3 = new Label(this, SWT.NONE);
		lbResultado3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lbResultado3.setAlignment(SWT.CENTER);
		lbResultado3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lbResultado3.setBounds(10, 297, 300, 149);
		
		lbResultado4 = new Label(this, SWT.NONE);
		lbResultado4.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lbResultado4.setAlignment(SWT.CENTER);
		lbResultado4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lbResultado4.setBounds(309, 297, 290, 149);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Reconhecendo Grafos");
		setSize(625, 491);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
