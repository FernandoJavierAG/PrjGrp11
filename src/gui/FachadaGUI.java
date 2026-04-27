package gui;

public class FachadaGUI {
	aplicacion.FachadaAplicacion fa;
	
	VPrincipal vp;
	
	public FachadaGUI(aplicacion.FachadaAplicacion fa) {
		this.fa = fa;
		
		this.vp = new VPrincipal(fa);
		vp.setVisible(true);
	}
	
	public static void main(String args[]) {
		new FachadaGUI(null);
	}
}
